package com.example.demo1.mapper;

import com.example.demo1.pojo.Film;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;



public interface FilmMapper {
//    private int filmId;
//    private String filmName;
//    private Date filmReleaseTime;
//    private String filmImageMain;
//    private String filmImageOther;
//    private String filmVideoUrl;
//    private String filmDirctor;
//    private String filmPerformer;
//    private int filmState;
//    private int filmLevel;

//  `FILM_ID` INT(11) NOT NULL AUTO_INCREMENT,
//  `FILM_NAME` CHAR(30) default NULL,
//            `FILM_RELEASE_TIME` DATE default NULL,
//            `FILM_IMAGE_MAIN` CHAR(50) default NULL,
//            `FILM_IMAGE_OTHER` CHAR(100) default NULL,
//            `FILM_VIDEO_URL` CHAR(50) default NULL,
//            `FILM_DIRECTOR` CHAR(50) default NULL,
//            `FILM_PERFORMER` CHAR(100) default NULL,
//            `FILM_STATE` INT(1) default NULL,
//            `FILM_LEVEL` INT(1) default NULL,


    @Select("SELECT FILM_ID filmId,FILM_NAME filmName,FILM_RELEASE_TIME filmReleaseTime,"+
            "FILM_IMAGE_MAIN filmImageMain,FILM_IMAGE_OTHER filmImageOther,"+
            "FILM_VIDEO_URL filmVideoUrl,FILM_DIRECTOR filmDirctor,FILM_PERFORMER filmPerformer,"+
            "FILM_STATE filmState,FILM_LEVEL filmLevel,FILM_HOT filmHot,FILM_DESCRIPTION filmDescription FROM film " +
            "WHERE FILM_NAME=#{filmName}")
    Film findFilmByName(String filmName);

    @Select("SELECT FILM_ID filmId,FILM_NAME filmName,FILM_RELEASE_TIME filmReleaseTime,"+
            "FILM_IMAGE_MAIN filmImageMain,FILM_IMAGE_OTHER filmImageOther,"+
            "FILM_VIDEO_URL filmVideoUrl,FILM_DIRECTOR filmDirctor,FILM_PERFORMER filmPerformer,"+
            "FILM_STATE filmState,FILM_LEVEL filmLevel FROM film"+
            " WHERE FILM_NAME=#{value}")
    List<Film> findFilmByState(int state);

    //根据关键字模糊搜索电影
    @Select("SELECT FILM_ID filmId,FILM_NAME filmName,FILM_RELEASE_TIME filmReleaseTime,"+
            "FILM_IMAGE_MAIN filmImageMain,FILM_IMAGE_OTHER filmImageOther,"+
            "FILM_VIDEO_URL filmVideoUrl,FILM_DIRECTOR filmDirctor,FILM_PERFORMER filmPerformer,"+
            "FILM_STATE filmState,FILM_LEVEL filmLevel,FILM_HOT filmHot,FILM_DESCRIPTION filmDescription FROM film "+
            "WHERE FILM_ID LIKE '%${value}%' or " +
            "FILM_STATE=#{value} and " +
            "FILM_NAME LIKE '%${value}%'")
    List<Film> findFilmByKey(String key);

    @Select("SELECT FILM_ID filmId,FILM_NAME filmName,FILM_RELEASE_TIME filmReleaseTime,"+
            "FILM_IMAGE_MAIN filmImageMain,FILM_IMAGE_OTHER filmImageOther,"+
            "FILM_VIDEO_URL filmVideoUrl,FILM_DIRECTOR filmDirctor,FILM_PERFORMER filmPerformer,"+
            "FILM_STATE filmState,FILM_LEVEL filmLevel,FILM_HOT filmHot,FILM_DESCRIPTION filmDescription FROM film " +
            "WHERE FILM_ID=#{filmId}")
    Film findFilmById(int filmId);

    @Update("UPDATE film SET FILM_STATE=#{filmState} WHERE FILM_ID=#{filmId}")
    int delFilm(Film film);

    @Update("UPDATE film SET FILM_HOT=#{filmHot} WHERE FILM_ID=#{filmId}")
    int delHot(Film film);
    //更改FILM_IMAGE_MAIN
    int updateFilm1(Film film);
    //更改FILM_VIDEO_URL
    int updateFilm2(Film film);

    @Insert("INSERT INTO film(FILM_NAME,FILM_RELEASE_TIME,FILM_DIRECTOR,FILM_PERFORMER,FILM_STATE,FILM_LEVEL,FILM_HOT,FILM_DESCRIPTION)" +
            "VALUES(#{filmName},#{filmReleaseTime},#{filmDirctor},#{filmPerformer},#{filmState},#{filmLevel},#{filmHot},#{filmDescription})")
    int addFilm(Film film);

}
