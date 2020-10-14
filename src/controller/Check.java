package controller;

import model.Agency;
import model.Car;
import model.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Check {
    private final double MINPRICE = 500000000;
    private final double MAXPRICE = 12000000000L;
    Scanner scanner = new Scanner(System.in);

    public boolean checkname(ArrayList<Car> arrayList, String name) {
        for (Car car : arrayList) {
            if (car.getCarName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public  String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###" + " VNĐ");
        return df.format(value);
    }

    public boolean checknumber(String value) {
        String regex = "^[0-9]*$";
        return value.matches(regex);
    }

    public int parseQuantity (String value){
        int result = 0;
        if (checknumber(value)) {
            result = Integer.parseInt(value);
        }else {
            boolean checkInput;
            do {
                try {
                    System.out.print("Số lượng phải là số nguyên , vui lòng nhập lại: ");
                    value = scanner.nextLine();
                    checkInput = checknumber(value);
                    if (checkInput){
                        result = Integer.parseInt(value);
                    }
                } catch (Exception e) {
                    checkInput = checknumber(value);
                    scanner.nextLine();
                }
            } while (!checkInput);
        }
        return  result;
    }

    public double parsePrice (String value){
        double result = 0;
        if (checknumber(value)) {
            result = checkMinMaxprice(value);
        }else {
            boolean checkInput;
            do {
                System.out.print("Giá xe phải là kiểu số , vui lòng nhập lại: ");
                value = scanner.nextLine();
                checkInput = checknumber(value);
                if (checkInput){
                    result = checkMinMaxprice(value);
                }
            } while (!checkInput);
        }
        return  result;
    }

    public boolean priceminmax (double value) {
        return !((value < MINPRICE) || (value > MAXPRICE));

    }

    public double checkMinMaxprice(String value) {
        double result = 0;
        double parseValue = Double.parseDouble(value);
        boolean checkmm = priceminmax(parseValue);
        if (checkmm){
            result = parseValue;
        }
        else {
            do{
                System.out.print("Giá của xe phải lớn hơn " + withLargeIntegers(MINPRICE) + " và nhỏ hơn " + withLargeIntegers(MAXPRICE) + " vui lòng nhập lại: ");
                parseValue = Double.parseDouble(scanner.nextLine());
                checkmm = priceminmax(parseValue);
                if (checkmm){
                    result = parseValue;
                }
            }while (!checkmm);
        }
        return result;
    }

    public boolean checkCarid(String id, ArrayList<Car> carlist){
        for (Car car : carlist){
            if (Integer.parseInt(id) == car.getCarID()){
                return true;
            }
        }
        return false;
    }

    public int carid(String stringID,ArrayList<Car> carlist){
        int id = 0;
        boolean checkcarid = checkCarid(stringID,carlist);
        if (checkcarid){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Id xe không tồn tại, vui lòng nhập lại:");
                stringID = scanner.nextLine();
                checkcarid = checkCarid(stringID, carlist);
                if (checkcarid){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkcarid);
        }
        return id;
    }
    public boolean checkAgencyId(String stringID, ArrayList<Agency> list){
        for (Agency agency :  list){
            if (Integer.parseInt(stringID) == agency.getAgencyID()){
                return true;
            }
        }
        return false;
    }

    public int agencyid(String stringID, ArrayList<Agency> list){
        int id = 0;
        boolean checkcarid = checkAgencyId(stringID,list);
        if (checkcarid){
            id = Integer.parseInt(stringID);
        }else {
            do {
                System.out.println("Đại lý ko tồn tại, vui lòng nhập lại");
                stringID = scanner.nextLine();
                checkcarid = checkAgencyId(stringID, list);
                if (checkcarid){
                    id = Integer.parseInt(stringID);
                }
            }while (!checkcarid);
        }
        return id;
    }

    public boolean checkQuantity(String stringQuantity, int id, ArrayList<Car> cars){
        for (Car car : cars) {
            if (id == car.getCarID()) {
                if (Integer.parseInt(stringQuantity) >= car.getQuantity()) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public int quantityCheck(String stringQuantity, int id, ArrayList<Car> cars){
        int quantity = 0;
        boolean check = checkQuantity(stringQuantity, id, cars);
        if (check){
            quantity = parseQuantity(stringQuantity);
        }else {
            do {
                for (Car car : cars){
                    if (id == car.getCarID()){
                        System.out.println("Số lượng xe được đặt phải nhỏ hơn hoặc bằng " + car.getQuantity());
                        stringQuantity = scanner.nextLine();
                        check = checkQuantity(stringQuantity, id, cars);
                        if(check){
                            quantity = parseQuantity(stringQuantity);
                        }
                    }
                }
            }while (!check);
        }
        return quantity;
    }

    public String carName(ArrayList<Car> cars, int id) {
        String carName = null;
        for (Car car : cars) {
            if (id == car.getCarID()) {
                carName = car.getCarName();
            }
        }
        return carName;
    }

    public String agencyName(ArrayList<Agency> agens, int id) {
        String agencyName = null;
        for (Agency agency : agens) {
            if (id == agency.getAgencyID()) {
                agencyName = agency.getAgencyName();
            }
        }
        return agencyName;
    }

}
