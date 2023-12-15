package com.hw.entity;

public class User {
    private int id;
    private String name;
    private int age;
    private String status;
    private String gender;
    public User(){}

    public User(String name, int age, String status, String gender) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.gender = gender;
    }

    public User(int id, String name, int age, String status, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", status='" + status + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getId() {
        return this.id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
