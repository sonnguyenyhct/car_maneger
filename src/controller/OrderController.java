package controller;

import model.Agency;
import model.Car;
import model.Order;
import storage.IOFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static model.Order.Status.ORDER;

public class OrderController {
    static ArrayList<Order> oderlist;
    ArrayList<Date> listDate;
    private static final String ORDER_FILE = "order.dat";
    Check check = new Check();
    Scanner scanner = new Scanner(System.in);

    public OrderController(ArrayList<Order> oderlist) {
        OrderController.oderlist = oderlist;
    }

    public void display(ArrayList<Car> arrayList, ArrayList<Agency> agencyList) {
        double total = 0;
        System.out.printf("%s%20s%20s%20s%20s%20s%20s%n","ID","Car Name","Agency Name","Quantity","Total","Status","Date");
        for (Order order : oderlist) {
            String date = order.getDay() + " / " + order.getMonth() + " / " + "2020";
            System.out.printf("%d%20s%20s%20d%20s%20s%20s%n", order.getOderID(), check.carName(arrayList,order.getCarID()), check.agencyName(agencyList,order.getAgencyID()), order.getQuantity(), check.withLargeIntegers(order.getTotal()),order.getStatus(),date);
            total += order.getTotal();
        }
        System.out.println("Doanh thu ước tính: " + check.withLargeIntegers(total));
        System.out.println();
    }

    public void input(ArrayList<Car> arrayList, ArrayList<Agency> agencyList) {
        CarController carController = new CarController(arrayList);
        AgencyController agencyController = new AgencyController(agencyList);
        int oderID;
        if (oderlist.size() == 0){
            oderID = 1;
        }else {
            oderID = oderlist.get(oderlist.size() - 1).getOderID() + 1;
        }
        carController.displayCar();
        System.out.println("Mời nhập id sản phẩm muốn thêm:");
        String stringcarID = scanner.nextLine();
        int carID = check.carid(stringcarID,arrayList);
        agencyController.display();
        System.out.println("Mời nhập id đại lý:");
        String stringagencyID = scanner.nextLine();
        int agencyID = check.agencyid(stringagencyID,agencyList);
        System.out.println("Mời nhập số lượng");
        String quantity = scanner.nextLine();
        int quantityInt = check.quantityCheck(quantity,carID,arrayList);
        System.out.println("Mời nhập ngày đặt hàng ");
        int day = new Scanner(System.in).nextInt();
        System.out.println("Mời nhập tháng đặt hàng ");
        int month = new Scanner(System.in).nextInt();
        double total = 0;
        for (Car car : arrayList) {
            if (carID != car.getCarID()) continue;
            total = Integer.parseInt(quantity) * car.getPrice();
            car.setQuantity(car.getQuantity() - quantityInt);
            System.out.printf("Xe %s còn lại %d chiếc trong kho", car.getCarName(), car.getQuantity());
            System.out.println();
            System.out.println();
            break;
        }
        Order order = new Order(oderID,carID,agencyID,quantityInt,day,month,total, Order.Status.ORDER);
        oderlist.add(order);
    }

    public void writeFileOrder(){
        IOFile.writeFile(ORDER_FILE, oderlist);
    }

    public static ArrayList<Order> readFileOrder(){
        return (ArrayList<Order>) IOFile.readFile(ORDER_FILE);
    }

    public boolean updateStatus(){
        System.out.println("Nhập vào id đơn hàng");
        int id = new Scanner(System.in).nextInt();
        for (Order order : oderlist){
            if (order.getCarID() == id){
                System.out.println("Chọn trạng thái mới (1. Đặt cọc - 2. Thanh toán - 3. Huỷ bỏ)");
                int status = new Scanner(System.in).nextInt();
                switch (status){
                    case 1 :
                        order.setStatus(Order.Status.DEPOSIT);
                        break;
                    case 2 :
                        order.setStatus(Order.Status.PAYMENT);
                        break;
                    case 3 :
                        order.setStatus(Order.Status.REFUSE);
                        break;
                }
                return true;
            }
        }
        return false;
    }

    public void getListTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        listDate = new ArrayList<>();
        for (Order order : oderlist){
            String dateInString = order.getDay() + "/" + order.getMonth() + "/" + "2020";
            try {
                Date date = formatter.parse(dateInString);
                listDate.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public void checkTime(ArrayList<Car> arrayList, ArrayList<Agency> agencyList){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        double estimatedsales = 0;
        double actualsales = 0;
        int check1 = 0;
        System.out.println("Nhập vào ngày bắt đầu");
        int dayStart = new Scanner(System.in).nextInt();
        System.out.println("Nhập vào tháng bắt đầu");
        int monthStart = new Scanner(System.in).nextInt();
        System.out.println("Nhập vào ngày kết thúc");
        int dayEnd = new Scanner(System.in).nextInt();
        System.out.println("Nhập vào tháng kết thúc");
        int monthEnd = new Scanner(System.in).nextInt();
        String start = dayStart + "/" + monthStart + "/" + "2020";
        String end = dayEnd + "/" + monthEnd + "/" + "2020";
        try {
            Date dateStart = formatter.parse(start);
            Date dateEnd = formatter.parse(end);
            System.out.printf("%s%20s%20s%20s%20s%20s%20s%n","ID","Car Name","Agency Name","Quantity","Total","Status","Date");
            for (Date date : listDate){
                if (dateStart.before(date) && dateEnd.after(date)){
                    String dateString = oderlist.get(check1).getDay() + " / " + oderlist.get(check1).getMonth() + " / 2020";
                    System.out.printf("%d%20s%20s%20d%20s%20s%20s%n", oderlist.get(check1).getOderID(), check.carName(arrayList,oderlist.get(check1).getCarID()), check.agencyName(agencyList,oderlist.get(check1).getAgencyID()), oderlist.get(check1).getQuantity(), check.withLargeIntegers(oderlist.get(check1).getTotal()),oderlist.get(check1).getStatus(),dateString);
                    estimatedsales += oderlist.get(check1).getTotal();
                }
                check1++;
            }
            System.out.println();
            System.out.println("Doanh thu ước tính: " + check.withLargeIntegers(estimatedsales) + ".");
            System.out.println();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}