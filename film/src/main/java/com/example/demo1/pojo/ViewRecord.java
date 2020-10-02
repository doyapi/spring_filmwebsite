package com.example.demo1.pojo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ViewRecord {
    private  int recordId;
    private  int userId;
    private  int filmId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date viewTime;

    public ViewRecord(){

    }

    public ViewRecord(int recordId, int userId, int filmId, Date viewTime) {
        this.recordId = recordId;
        this.userId = userId;
        this.filmId = filmId;
        this.viewTime = viewTime;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public String toString() {
        return "ViewRecord{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", filmId=" + filmId +
                ", viewTime=" + viewTime +
                '}';
    }
}
