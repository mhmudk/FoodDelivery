package com.example.fooddelivery.pojo;

public class User {
    String name, phone, email, password, usertype, location,id;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String email ,String phone, String password, String usertype) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }
public User(String name,String email,String usertype){
    this.name = name;
    this.email = email;

    this.usertype = usertype;

}



    public User(String name, String email, String phone, String password, String usertype, String location) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getLocation() {
        return location;
    }
}