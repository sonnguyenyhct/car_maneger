package model;

public class Customer extends Person {
    enum Status {
        ORDER, DEPOSIT, FALL, WINTER;
    }
    public Customer(){

    }
    public Customer(int ID, String name, int age, String birthDay) {
        super(ID, name, age, birthDay);
    }


}
