package com.example.duan_oder_doan.model;

public class User {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private int image;
    private String gender;
    private String date_of_birth;

    public User() {
    }

    public User(String fullName, String email, String phone, String password, int image, String gender, String date_of_birth) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
