package com.example.demo1.controller;

import com.example.demo1.mapper.FilmMapper;
import com.example.demo1.pojo.Film;
import com.example.demo1.pojo.User;
import com.example.demo1.pojo.ViewRecordVO;
import com.example.demo1.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping("/test2")
    public String test2(HttpSession session)throws Exception{
        User sessionUser=(User)session.getAttribute("user");
        System.out.println("session:保存的用户信息"+sessionUser);
        return "success";
    }

    @GetMapping("/toViewRecord")
    public ModelAndView toViewRecord(HttpSession session,ModelAndView mav)throws Exception{
        User sessionUser=(User)session.getAttribute("user");
        System.out.println("session:保存的用户信息"+sessionUser);
        List<ViewRecordVO> records=filmService.findRecordByUserId(sessionUser.getUserId());
        mav.setViewName("viewrecord");
        mav.addObject("records",records);
        return mav;
    }
    //根据关键字查询电影
    @PostMapping("/searchFilmByKey")
    public ModelAndView searchFilmByKey(String key,ModelAndView mav)throws Exception {
        System.out.println("用户输入的关键字:" + key);
        List<Film> films = filmService.findFilmByKey(key);
        System.out.println("查询到的数据量：" + films.size());
        mav.addObject("films", films);
        mav.setViewName("adminindex2");
        return mav;
    }
//    电影删除操作
    @GetMapping("/filmDel/{filmId}/{filmState}")
//    @ResponseBody
    public ModelAndView filmDel(@PathVariable("filmId")int filmId,
                            @PathVariable("filmState")int filmState,ModelAndView mav)throws Exception{
        System.out.println("获取到的电影编号:"+filmId);
        System.out.println("获取到的电影状态:"+filmState);

        boolean result=filmService.updateFilmState(filmState,filmId);
        System.out.println("修改结果："+result);

        List<Film> films =filmService.findFilmByKey("");
        mav.addObject("films",films);
        mav.setViewName("adminindex2");
        return mav;
    }
    //设为热播
    @GetMapping("/updateFilmHot/{filmId}/{filmHot}")
//    @ResponseBody
    public ModelAndView updatefilmHot(@PathVariable("filmId")int filmId,
                            @PathVariable("filmHot")int filmHot,ModelAndView mav)throws Exception{
        System.out.println("获取到的电影编号:"+filmId);
        System.out.println("获取到的电影热播:"+filmHot);

        boolean result=filmService.updateFilmHot(filmHot,filmId);
        System.out.println("修改结果："+result);

        List<Film> films =filmService.findFilmByKey("");
        mav.addObject("films",films);
        mav.setViewName("adminindex2");
        return mav;
    }
    //更新电影信息
    @GetMapping("/updateFilm/{filmId}")
//    @ResponseBody
    public ModelAndView toUpdateFilm(@PathVariable("filmId")int filmId,ModelAndView mav)throws Exception {
        System.out.println("获取到的电影编号:" + filmId);

        //1、根据用户编号查询单个用户信息
        Film film = filmService.findFilmrById(filmId);
        mav.addObject("films", film);
        mav.setViewName("updateFilm");
        return mav;
    }

    @PostMapping("/update2")
    public ModelAndView update2(MultipartFile file,
                                Film film, ModelAndView mav)throws Exception{
        System.out.println("获取到的电影信息:"+film);
        if(file.isEmpty()){
            mav.addObject("msg","文件为空！");
        }else{
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID()+suffixName;//每次上传处理文件名   UUID生成前缀
            String imagePath = "D:\\upload\\image\\"+newFileName;
            File dest = new File(imagePath);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                mav.addObject("msg","上传失败！");
            }
            film.setFilmImageMain(newFileName);
        }
        boolean result = filmService.updateFilm1(film);
        System.out.println("修改结果:"+result);
        //2.查询
        List<Film> films = filmService.findFilmByKey("");
        mav.addObject("films",films);
        mav.setViewName("adminindex2");

        return mav;
    }

//    @PostMapping("/update3")
//    public ModelAndView update3(MultipartFile file,
//                                Film film, ModelAndView mav)throws Exception{
//        System.out.println("获取到的电影信息:"+film);
//        if(file.isEmpty()){
//            mav.addObject("msg","文件为空！");
//        }else{
//            String fileName = file.getOriginalFilename();
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            String newFileName = UUID.randomUUID()+suffixName;//每次上传处理文件名   UUID生成前缀
//            String videoPath = "D:\\upload\\video\\"+newFileName;
//            File dest = new File(videoPath);
//            if(!dest.getParentFile().exists()){
//                dest.getParentFile().mkdirs();
//            }
//            try {
//                file.transferTo(dest);
//            }catch (Exception e){
//                e.printStackTrace();
//                mav.addObject("msg","上传失败！");
//            }
//            film.setFilmVideoUrl(newFileName);
//        }
//        boolean result = filmService.updateFilm2(film);
//        System.out.println("修改结果:"+result);
//        //2.查询
//        List<Film> films = filmService.findFilmByKey("");
//        mav.addObject("films",films);
//        mav.setViewName("adminindex2");
//
//        return mav;
//    }

    //用来跳转到http://localhost:8090/DEMO/addFilm
    @GetMapping("/toAddFilm")
    public String toAddFilm()throws Exception{
        return "toAddFilm";
    }
    @PostMapping("/addFilm")
    public ModelAndView addFilm(ModelAndView mav,Film film,MultipartFile file)throws Exception{
        System.out.println("获取到的电影信息"+film.toString());
        boolean result=filmService.checkFilm(film);
        if (result){
            mav.setViewName("success");
            mav.addObject("filmName",film.getFilmName());
        }else{
            System.out.println("录入失败");
        }

        return mav;
    }



    @GetMapping("/updateFilmURL/{filmId}")
//    @ResponseBody
    public ModelAndView updateFilmURL(@PathVariable("filmId")int filmId,ModelAndView mav)throws Exception {
        System.out.println("获取到的电影编号:" + filmId);

        //1、根据用户编号查询单个用户信息
        Film film = filmService.findFilmrById(filmId);
        mav.addObject("films", film);
        mav.setViewName("updateFilmURL");
        return mav;
    }


    @PostMapping("/toUpdateFilmURL")
    public ModelAndView toUpdateFilmURL(MultipartFile file,
                                Film film, ModelAndView mav)throws Exception{
        System.out.println("获取到的电影信息:"+film);
        if(file.isEmpty()){
            mav.addObject("msg","文件为空！");
        }else{
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID()+suffixName;//每次上传处理文件名   UUID生成前缀
            String videoPath = "D:\\upload\\video\\"+newFileName;
            File dest = new File(videoPath);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                mav.addObject("msg","上传失败！");
            }
            film.setFilmVideoUrl(newFileName);
        }
        boolean result = filmService.updateFilm2(film);
        System.out.println("修改结果:"+result);
        //2.查询
        List<Film> films = filmService.findFilmByKey("");
        mav.addObject("films",films);
        mav.setViewName("adminindex2");

        return mav;
    }
}
