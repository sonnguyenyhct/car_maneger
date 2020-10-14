package model;

public class Direction extends Person {

    private static Direction instance;
    private String userName;
    private String passWord;

    private Direction(){}

    private Direction(int ID, String name, int age, String birthDay, String userName, String passWord) {
        super(ID, name, age, birthDay);
        this.userName = userName;
        this.passWord = passWord;
    }

    public static Direction getInstance(int ID, String name, int age, String birthDay, String userName, String passWord){
        if(instance == null){
            instance = new Direction(ID,name,age,birthDay,userName,passWord);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
