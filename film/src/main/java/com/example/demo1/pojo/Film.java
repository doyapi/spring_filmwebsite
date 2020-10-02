package com.example.demo1.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
public class Film {
    private int filmId;
    private String filmName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date filmReleaseTime;
    private String filmImageMain;
    private String filmImageOther;
    private String filmVideoUrl;
    private String filmDirctor;
    private String filmPerformer;
    private int filmState;
    private int filmLevel;
    private int filmHot;
    private String filmDescription;

    public Film() {
    }

    public Film(int filmId, String filmName, Date filmReleaseTime, String filmImageMain, String filmImageOther, String filmVideoUrl, String filmDirctor, String filmPerformer, int filmState, int filmLevel, int filmHot, String filmDescription) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmReleaseTime = filmReleaseTime;
        this.filmImageMain = filmImageMain;
        this.filmImageOther = filmImageOther;
        this.filmVideoUrl = filmVideoUrl;
        this.filmDirctor = filmDirctor;
        this.filmPerformer = filmPerformer;
        this.filmState = filmState;
        this.filmLevel = filmLevel;
        this.filmHot = filmHot;
        this.filmDescription = filmDescription;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Date getFilmReleaseTime() {
        return filmReleaseTime;
    }

    public void setFilmReleaseTime(Date filmReleaseTime) {
        this.filmReleaseTime = filmReleaseTime;
    }

    public String getFilmImageMain() {
        return filmImageMain;
    }

    public void setFilmImageMain(String filmImageMain) {
        this.filmImageMain = filmImageMain;
    }

    public String getFilmImageOther() {
        return filmImageOther;
    }

    public void setFilmImageOther(String filmImageOther) {
        this.filmImageOther = filmImageOther;
    }

    public String getFilmVideoUrl() {
        return filmVideoUrl;
    }

    public void setFilmVideoUrl(String filmVideoUrl) {
        this.filmVideoUrl = filmVideoUrl;
    }

    public String getFilmDirctor() {
        return filmDirctor;
    }

    public void setFilmDirctor(String filmDirctor) {
        this.filmDirctor = filmDirctor;
    }

    public String getFilmPerformer() {
        return filmPerformer;
    }

    public void setFilmPerformer(String filmPerformer) {
        this.filmPerformer = filmPerformer;
    }

    public int getFilmState() {
        return filmState;
    }

    public void setFilmState(int filmState) {
        this.filmState = filmState;
    }

    public int getFilmLevel() {
        return filmLevel;
    }

    public void setFilmLevel(int filmLevel) {
        this.filmLevel = filmLevel;
    }

    public int getFilmHot() {
        return filmHot;
    }

    public void setFilmHot(int filmHot) {
        this.filmHot = filmHot;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", filmReleaseTime=" + filmReleaseTime +
                ", filmImageMain='" + filmImageMain + '\'' +
                ", filmImageOther='" + filmImageOther + '\'' +
                ", filmVideoUrl='" + filmVideoUrl + '\'' +
                ", filmDirctor='" + filmDirctor + '\'' +
                ", filmPerformer='" + filmPerformer + '\'' +
                ", filmState=" + filmState +
                ", filmLevel=" + filmLevel +
                ", filmHot=" + filmHot +
                ", filmDescription='" + filmDescription + '\'' +
                '}';
    }
}
