package view.main;

import model.Agency;
import controller.AgencyController;
import model.Car;
import controller.CarController;
import model.Order;
import controller.OrderController;
import model.Manage;
import controller.ManagerController;

import java.util.ArrayList;
import java.util.Scanner;

public class MainManager {

    private static ArrayList<Car> carList;
    private static ArrayList<Order> orderList;
    private static ManagerController managerController;
    private static ArrayList<Manage> list;

    public static void main(String[] args) {
        carList = CarController.readFileCar();
        //carList = new ArrayList<>();
        ArrayList<Agency> agencyList = new ArrayList<>();
        

        //orderList = new ArrayList<>();
        orderList = OrderController.readFileOrder();

        CarController carController = new CarController(carList);
        OrderController orderController = new OrderController(orderList);
        AgencyController agencyController = new AgencyController(agencyList);
        agencyController.add();


        while (true) login(carController, orderController,agencyList,agencyController,orderList);
    }

    public static void login(CarController carController,OrderController orderController,ArrayList<Agency> agencyList,AgencyController agencyController,ArrayList<Order> orderList){
        //list = new ArrayList<>();
        list = ManagerController.readFileManager();
        System.out.println("---- ĐĂNG NHẬP ----");
        managerController = new ManagerController(list);
        int check = managerController.checkLogin();
        if (check == 1 || check == 2){
            while (true){
                MainManager.menu(carController, orderController,agencyList,agencyController,orderList);
            }
        }else {
            System.out.println("Sai tài khoản hoặc mật khẩu, Hãy nhập lại");
        }
    }

    public static void menu (CarController carController, OrderController orderController, ArrayList<Agency> agencyList, AgencyController agencyController, ArrayList<Order> orderList) {

        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ ----");
        System.out.println("Chọn chức năng theo số :");
        System.out.println("1 . Xem danh sách xe trong kho ");
        System.out.println("2 . Thêm mới xe");
        System.out.println("3 . Cập nhật xe");
        System.out.println("4 . Xóa");
        System.out.println("5 . Tìm kiếm theo tên");
        System.out.println("6 . Tìm kiếm theo giá");
        System.out.println("7 . Sắp xếp theo tên");
        System.out.println("8 . Sắp xếp theo giá tăng dần");
        System.out.println("9 . Sắp xếp theo giá giảm dần");
        System.out.println("10 . Lên đơn hàng");
        System.out.println("11 . Danh sách đơn hàng");
        System.out.println("12 . Sửa trạng thái đơn hàng");
        System.out.println("13 . Hiển thị đơn hàng trong khoảng thời gian");
        System.out.println("14 . Đọc từ File");
        System.out.println("15 . Ghi vào File");
        if (managerController.checkDirection()){
            System.out.println("16 . Thêm sửa xoá nhân viên");
        }
        System.out.println("0 . Thoát");
        System.out.println("Chọn chức năng :");

        int key = new Scanner(System.in).nextInt();

        switch (key) {
            case 0 :
                MainManager.login(carController, orderController,agencyList,agencyController,orderList);
                System.exit(0);
                break;
            case 1 :
                carController.display();
                break;
            case 2 :
                carController.input();
                break;
            case 3 :
                if (carController.update()){
                    System.out.println("Sửa thành công");
                }else {
                    System.out.println("Không tìm thấy id xe");
                }
                break;
            case 4 :
                if (carController.delete()){
                    System.out.println("Xoá thành công");
                }else {
                    System.out.println("Không tìm thấy id xe");
                }
                break;
            case 5 :
                carController.findCarByName();
                break;
            case 6 :
                carController.findCarByPrice();
                break;
            case 7 :
                carController.sortCarByName();
                break;
            case 8 :
                carController.sortCarByPriceAscending();
                break;
            case 9 :
                carController.sortCarByPriceDecrease();
                break;
            case 10 :
                orderController.input(carList,agencyList);
                break;
            case 11 :
                orderController.display(carList,agencyList);
                break;
            case 12 :
                if (orderController.updateStatus()){
                    System.out.println("Cập nhật thành công");
                }else {
                    System.out.println("Không tìm thấy id đơn hàng");
                }
                break;
            case 13 :
                orderController.getListTime();
                orderController.checkTime(carList,agencyList);
                break;
            case 14 :
                carList = CarController.readFileCar();
                break;
            case 15:
                carController.writeFileCar();
                orderController.writeFileOrder();
                break;
            case 16 :
                if (managerController.checkDirection()){
                    while (true){
                        menuManager(managerController);
                    }
                }
        }
    }

    public static void menuManager(ManagerController managerController){
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN ----");
        System.out.println("Chọn chức năng theo số :");
        System.out.println("1 . Hiển thị danh sách nhân viên ");
        System.out.println("2 . Thêm nhân viên ");
        System.out.println("3 . Sửa nhân viên");
        System.out.println("4 . Xoá nhân viên");
        System.out.println("5 . Ghi vào file");
        System.out.println("0 . Thoát");
        System.out.println("Chọn chức năng :");

        int key = new Scanner(System.in).nextInt();

        switch (key) {
            case 0 :
                System.out.println("GOODBYE!!!");
                System.exit(0);
                break;
            case 1 :
                managerController.display();
                break;
            case 2 :
                managerController.input();
                break;
            case 3 :
                if (managerController.update()){
                    System.out.println("Sửa thành công");
                }else {
                    System.out.println("Id không tồn tại");
                }
                break;
            case 4 :
                if (managerController.delete()){
                    System.out.println("Xoá thành công");
                }else {
                    System.out.println("Id không tồn tại");
                }
                break;
            case 5 :
                managerController.writeFileManager();
                break;
        }
    }

}
