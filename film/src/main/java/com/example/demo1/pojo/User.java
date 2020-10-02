package com.example.demo1.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private int userId;
    private String userName;
    private String pwd;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private int role;
    private int state;
    private double balance;
    private String userImage;
    private int score;
    private String phone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", role=" + role +
                ", state=" + state +
                ", balance=" + balance +
                ", userImage='" + userImage + '\'' +
                ", score=" + score +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(int userId, String userName, String pwd, String sex, Date birth, int role, int state, double balance, String userImage, int score, String phone) {
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.sex = sex;
        this.birth = birth;
        this.role = role;
        this.state = state;
        this.balance = balance;
        this.userImage = userImage;
        this.score = score;
        this.phone = phone;

    }

    public User() {

    }
}