package model;

import java.io.Serializable;

public class Manage extends Person implements Serializable{
    private static final long serialVersionUID = 5649453566425520120L;
    private String userName;
    private String passWord;

    public Manage() {
    }

    public Manage(int ID, String name, int age, String birthDay, String userName, String passWord) {
        super(ID, name, age, birthDay);
        this.userName = userName;
        this.passWord = passWord;
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
