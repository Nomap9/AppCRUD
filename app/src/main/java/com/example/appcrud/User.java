package com.example.appcrud;

public class User {

    private int img;
    private int id;

    private String name;
    private  String hireDate;
    private  double salary;


    public User(int img, int id, String name, String hireDate, double salary) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
