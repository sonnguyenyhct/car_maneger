package controller;

import action.CRUDaction;
import model.Direction;
import model.Manage;
import storage.IOFile;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerController implements CRUDaction {


    ArrayList<Manage> manages;
    String userNameInput = null;
    String passWordInput = null;
    private static final String MANAGER_FILE = "manager.dat";
    Direction direction = Direction.getInstance(1,"son",24,"13/12/1996","son","123");

    public ManagerController(ArrayList<Manage> manages) {
        this.manages = manages;
    }

    public void writeFileManager(){
        IOFile.writeFile(MANAGER_FILE, manages);
        System.out.println("Ghi thành công.");
    }
    public static ArrayList<Manage> readFileManager(){
        final ArrayList<Manage> manages = IOFile.readFile(MANAGER_FILE);
        return manages;
    }

    public int checkLogin(){
        System.out.println("Nhập vào tài khoản");
        userNameInput = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào mật khẩu");
        passWordInput = new Scanner(System.in).nextLine();
        for (Manage manage : manages){
            if (manage.getUserName().equals(userNameInput) && manage.getPassWord().equals(passWordInput)){
                return 1;
            }
        }
        if (direction.getUserName().equals(userNameInput) && direction.getPassWord().equals(passWordInput)){
            return 2;
        }
        return 3;
    }

    public boolean checkDirection(){
        return direction.getUserName().equals(userNameInput) && direction.getPassWord().equals(passWordInput);
    }

    @Override
    public void display() {
        manages = readFileManager();
        System.out.printf("%s%20s%20s%20s%20s%20s%n","ID","Name","Age","BirthDay","UserName","Password");
        for (Manage manage : manages) {
            System.out.printf("%d%20s%20d%20s%17s%20s%n",manage.getID(),manage.getName(),manage.getAge(),manage.getBirthDay(),manage.getUserName(),manage.getPassWord());
        }
    }

    @Override
    public void input() {
        int id;
        if (manages.size() == 0){
            id = 1;
        }else {
            id = (manages.get(manages.size() - 1)).getID() + 1;
        }
        System.out.println("Nhập vào tên quản lý");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào ngày sinh quản lý");
        String birthDay = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào tuổi quản lý");
        int age = new Scanner(System.in).nextInt();
        System.out.println("Nhập vào tên đăng nhập");
        String userName = new Scanner(System.in).nextLine();
        System.out.println("Nhập vào mật khẩu");
        String passWord = new Scanner(System.in).nextLine();
        manages.add(new Manage(id,name,age,birthDay,userName,passWord));
    }

    @Override
    public boolean update() {
        System.out.println("Nhập id quản lý muốn sửa");
        int idManager = new Scanner(System.in).nextInt();
        for (Manage manage : manages) {
            if (manage.getID() == idManager) {
                System.out.println("Nhập vào tên quản lý mới");
                String name = new Scanner(System.in).nextLine();
                System.out.println("Nhập vào ngày sinh quản lý mới");
                String birthDay = new Scanner(System.in).nextLine();
                System.out.println("Nhập vào tuổi quản lý mới");
                int age = new Scanner(System.in).nextInt();
                System.out.println("Nhập vào tên đăng nhập mới");
                String userName = new Scanner(System.in).nextLine();
                System.out.println("Nhập vào mật khẩu mới");
                String passWord = new Scanner(System.in).nextLine();
                manage.setName(name);
                manage.setAge(age);
                manage.setBirthDay(birthDay);
                manage.setUserName(userName);
                manage.setPassWord(passWord);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete() {
        System.out.println("Nhập vào id quản lý muốn xoá");
        int idManager = new Scanner(System.in).nextInt();
        for (int i = 0; i < manages.size(); i++){
            if (manages.get(i).getID() == idManager){
                manages.remove(i);
                return true;
            }
        }
        return false;
    }
}
