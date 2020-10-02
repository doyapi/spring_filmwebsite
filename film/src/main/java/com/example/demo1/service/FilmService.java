package com.example.demo1.service;

import com.example.demo1.pojo.Film;
import com.example.demo1.pojo.ViewRecordVO;

import java.util.List;

public interface FilmService {
    List<Film> findAllFilm(int state);
    List<ViewRecordVO>findRecordByUserId(int userId);
    List<Film> findFilmByKey(String key);
    Boolean updateFilmState(int filmState,int filmId);
    Boolean updateFilmHot(int filmHot,int filmId);
    Film findFilmrById(int filmId);
    boolean updateFilm1(Film film);
    boolean updateFilm2(Film film);
    boolean checkFilm(Film film);
}
