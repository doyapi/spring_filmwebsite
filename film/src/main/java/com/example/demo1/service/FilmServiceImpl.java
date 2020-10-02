package com.example.demo1.service;

import com.example.demo1.mapper.FilmMapper;
import com.example.demo1.mapper.ViewRecordMapper;
import com.example.demo1.pojo.Film;
import com.example.demo1.pojo.ViewRecordVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("/filmService")
public class FilmServiceImpl implements FilmService{
    @Resource
    FilmMapper filmMapper;
    @Resource
    ViewRecordMapper viewRecordMapper;
    @Override
    public List<Film> findAllFilm(int state){

        return filmMapper.findFilmByState(state);
    }

    @Override
    public List<ViewRecordVO> findRecordByUserId(int userId) {
        return viewRecordMapper.findRecordByUserId(userId);
    }

    @Override
    public List<Film> findFilmByKey(String key) {
        return filmMapper.findFilmByKey(key);
    }

    @Override
    public Boolean updateFilmState(int FilmState, int filmId) {
        Film film=new Film();
        film.setFilmId(filmId);
        if(0==FilmState){
            film.setFilmState(1);
        }else {
            film.setFilmState(0);
        }
        int result=filmMapper.delFilm(film);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateFilmHot(int filmHot, int filmId) {
        Film film=new Film();
        film.setFilmId(filmId);
        if(0==filmHot){
            film.setFilmHot(1);
        }else {
            film.setFilmHot(0);
        }
        int result=filmMapper.delHot(film);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public Film findFilmrById(int filmId) {

        return filmMapper.findFilmById(filmId);
    }

    @Override
    public boolean updateFilm1(Film film) {
        int i=filmMapper.updateFilm1(film);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFilm2(Film film) {
        int i=filmMapper.updateFilm2(film);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkFilm(Film film) {
        int result=filmMapper.addFilm(film);
        if(result>0){
            return true;
        }
        return false;
    }


}
