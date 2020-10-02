package com.example.demo1.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ViewRecordVO {

    private int userId;
    private String userName;
    private String fileName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date viewTime;

    @Override
    public String toString() {
        return "ViewRecordVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", viewTime=" + viewTime +
                '}';
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    public ViewRecordVO(int userId, String userName, String fileName, Date viewTime) {
        this.userId = userId;
        this.userName = userName;
        this.fileName = fileName;

        this.viewTime = viewTime;
    }

    public ViewRecordVO() {

    }
}
