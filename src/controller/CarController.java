package controller;

import action.CRUDaction;
import model.Car;
import storage.IOFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CarController implements CRUDaction {
    private static final String CAR_FILE = "car.dat";
    Check check = new Check();
    static ArrayList<Car> carArrayList;
    Scanner scanner = new Scanner(System.in);

    public CarController(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    @Override

    public void display() {
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(), check.withLargeIntegers(car.getPrice()));
        }
    }

    @Override
    public void input() {
        int id;
        if (carArrayList.size() == 0){
            id = 1;
        }else {
             id = carArrayList.get(carArrayList.size() - 1).getCarID() + 1;
        }
        System.out.println("Vui lòng nhập tên: ");
        String name = scanner.nextLine();
        if (!check.checkname(carArrayList,name)){
            System.out.println("Tên sản phẩm đã tồn tại.Vui lòng nhập lại");
            return;
        }
        System.out.println("Vui lòng nhập số lượng");
        String stringQuantity = scanner.nextLine();
        int quantity = check.parseQuantity(stringQuantity);
        System.out.println("Vui lòng nhập giá xe");
        String stringPrice = scanner.nextLine();
        double price = check.parsePrice(stringPrice);
        carArrayList.add(new Car(id,name,quantity,price));
    }

    @Override
    public boolean update(){
        display();
        System.out.println("Nhập id xe muốn sửa");
        int idCar = new Scanner(System.in).nextInt();
        for (Car car : carArrayList) {
            if (car.getCarID() == idCar) {
                System.out.println("Nhập tên xe mới");
                String nameCar = scanner.nextLine();
                System.out.println("Nhập số lượng mới");
                String stringQuantity = scanner.nextLine();
                int quantity = check.parseQuantity(stringQuantity);
                System.out.println("Nhập giá tiền mới");
                String stringPrice = scanner.nextLine();
                double price = check.parsePrice(stringPrice);
                car.setCarName(nameCar);
                car.setQuantity(quantity);
                car.setPrice(price);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean delete(){
        display();
        System.out.println("Nhập vào id xe muốn xoá");
        int idCar = new Scanner(System.in).nextInt();
        for (int i = 0; i < carArrayList.size(); i++){
            if (carArrayList.get(i).getCarID() == idCar){
                carArrayList.remove(i);
                return true;
            }
        }
        return false;
    }
    public void writeFileCar(){
        IOFile.writeFile(CAR_FILE, carArrayList);
        System.out.println("Ghi thành công.");
    }

    public static ArrayList<Car> readFileCar(){
        final ArrayList<Car> cars = IOFile.readFile(CAR_FILE);
        return cars;
    }

    public void findCarByName(){
        System.out.println("Nhập vào tên xe muốn tìm");
        String name_Car = new Scanner(System.in).nextLine();
        boolean check = false;
        for (Car car : carArrayList) {
            if (car.getCarName().contains(name_Car)) {
                System.out.printf("%d%20s%17d%25f%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getPrice());
                check = true;
            }
        }
        if (!check){
            System.out.println("Không tìm thấy xe");
        }
    }

    public void findCarByPrice(){
        System.out.println("Nhập vào giá xe muốn tìm");
        double price_Car = new Scanner(System.in).nextDouble();
        boolean check = false;
        for (Car car : carArrayList) {
            if (car.getPrice() == price_Car) {
                System.out.printf("%d%20s%17d%25f%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getPrice());
                check = true;
            }
        }
        if (!check){
            System.out.println("Không tìm thấy xe");
        }
    }

    public void sortCarByPriceDecrease(){
        carArrayList.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25f%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getPrice());
        }
    }

    public void sortCarByPriceAscending(){
        carArrayList.sort(Comparator.comparingDouble(Car::getPrice));
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25f%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getPrice());
        }
    }

    public void sortCarByName(){
        carArrayList.sort(Comparator.comparing(Car::getCarName));
        for (Car car : carArrayList) {
            System.out.printf("%d%20s%17d%25f%n",car.getCarID(),car.getCarName(),car.getQuantity(),car.getPrice());
        }
    }

    public void displayCar(){
        System.out.printf("%s%20s%20s%20s%n","ID","Name","Quantity","Price");
        for (Car car : carArrayList){
            if (car.getQuantity() > 0) {
                System.out.printf("%d%20s%17d%25s%n",car.getCarID(),car.getCarName(),car.getQuantity(), check.withLargeIntegers(car.getPrice()));
            }
        }
    }

}
