package model;

public class Person {
    private static final long serialVersionUID = 5649453566425520120L;
    private int ID;
    private String name;
    private int age;
    private String birthDay;

    public Person() {
    }

    public Person(int ID, String name, int age, String birthDay) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
