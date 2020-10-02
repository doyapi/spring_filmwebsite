package com.example.demo1.controller;

import com.example.demo1.pojo.Film;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    FilmService filmService;


//    @GetMapping("/toIndex")
//    public ModelAndView toIndex(ModelAndView mav)throws Exception{
//        System.out.println("访问了toIndex方法，可以继续编写访问方法");
//        List<Film> films=filmService.findAllFilm(1);
//        mav.addObject("films",films);
//        mav.setViewName("index");
//        return mav;
//    }

//    @GetMapping("/toIndex")
//    public String toIndex()throws Exception{
//        return "index";
//    }

    @GetMapping("/toStaticIndex")
    public String toStaticIndex()throws Exception{
        return "redirect:http://localhost:8090/DEMO/index.html";
    }
//    getmapping只接受get请求    postmapping只接受post请求    request都可以接收（get/post）

    @GetMapping("/mavToIndex")
    public ModelAndView mavToIndex(ModelAndView mav)throws Exception{
        System.out.println("方法类型为ModeAndView");
        mav.setViewName("index");
        mav.addObject("name","Pipipo");
        return mav;
    }

    @GetMapping("/toRegist")
    public String toRegist()throws Exception{
        return "regist";
    }

    @PostMapping("/regist1")
    public String regist1(HttpServletRequest request)throws Exception{
        String name=request.getParameter("name");
        System.out.println("前台获取的name"+name);
        request.setAttribute("name",name);
        return "success";
    }
    @PostMapping("/regist2")
    public ModelAndView regist2(ModelAndView mav,String name,String pwd)throws Exception{
        System.out.println("前台获取的name:"+name);
        System.out.println("前台获取的pwd:"+pwd);
        mav.setViewName("success");
        mav.addObject("name",name);
        return mav;
    }

    @PostMapping("/regist3")
    public ModelAndView regist3(ModelAndView mav,User user)throws Exception{
        System.out.println("获取到的用户信息"+user.toString());
        boolean result = userService.checkRegist(user);
        System.out.println("注册结果"+result);
        if (result){
            mav.setViewName("success");
            mav.addObject("name",user.getUserName());
        }else {
            System.out.println("注册失败");
        }

        return mav;
    }

    @PostMapping("/regist4")
    public ModelAndView regist4(ModelAndView mav,User user)throws Exception{
        System.out.println("获取到的用户信息"+user.toString());
        String result = userService.checkRegist2(user);
        if("success".equals(result)){
            mav.setViewName("success");
        }else if ("nameErr".equals(result)){
            mav.setViewName("regist");
            mav.addObject("nameMsg","用户名重复！");
        }else if ("phoneErr".equals(result)){
            mav.setViewName("regist");
            mav.addObject("phoneMsg","电话重复！");
        }else{
            mav.setViewName("regist");
            mav.addObject("sysMsg","系统异常！");

        }
        return mav;
    }
    //JSON数据返回
//    http://localhost:8090/DEMO/find/user/pipipo
    @GetMapping("/find/user/{name}")
    @ResponseBody
    public User find(@PathVariable("name")String name)throws Exception{
        System.out.println("获取到的用户名："+name);
        return userService.findUserByName(name);
    }

    //前台传递表单数据到后台，后台发送JSON数据回来
    @PostMapping("/test1")
    @ResponseBody
    public User test1(User user)throws Exception{
        System.out.println("前台内容传递"+user.toString());
        user.setUserName("后台传递的值");
        return user;
    }

    //用户名重复验证
    @PostMapping("/checkName")
    @ResponseBody
    public String checkName(String userName)throws Exception{
        //调用业务逻辑
        boolean result=userService.checkName(userName);
        if (result) {
            return "{\"msg\":\"ok\"}";
        }
        return "{\"msg\":\"no\"}";
    }

    @GetMapping("findAllUsers")
    @ResponseBody
    public List<User> findAllUsers()throws Exception{
        return userService.findAllUsers();
    }

    @PostMapping("/testUpload")
    public String testUpload(MultipartFile file, String name)throws Exception{
        if (file.isEmpty()){
            return "error";
        }
        String fileName=file.getOriginalFilename();
        System.out.println(name);
        System.out.println("上传文件名"+fileName);
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传后缀"+suffixName);
        String prefixName=fileName.substring(0,fileName.indexOf("."));
        System.out.println("上传前缀"+prefixName);
        User user=new User();
        String newFileName=UUID.randomUUID()+suffixName;
        System.out.println("重构后，文件名"+newFileName);

        String imagePath="D:\\upload\\"+newFileName;
        System.out.println("上传地址："+imagePath);
        File dest =new File(imagePath);

        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

//    @GetMapping("/toFilm")
//    public String toFilm()throws Exception{
//        return "filmpage";
//    }
}


