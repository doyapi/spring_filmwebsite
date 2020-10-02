package com.example.demo1.controller;

import com.example.demo1.pojo.Film;
import com.example.demo1.pojo.User;
import com.example.demo1.service.FilmService;
import com.example.demo1.service.UserService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
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
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FilmService filmService;
    @GetMapping("/toLogin")
//    http://localhost:8090/DEMO/toLogin
    public String toLogin()throws Exception{
        return "login";
    }
    @GetMapping("/toIndex")
    public String toIndex()throws Exception{
        return "index4";
    }

    @PostMapping("/login")
    public ModelAndView login(ModelAndView mav, User user, HttpSession session)throws Exception {
        String result = userService.checkLogin(user);
        //查询用户功能
        if ("admin1".equals(result)) {
            User getUser=userService.findUserByName(user.getUserName());
            mav.setViewName("adminindex1");
//            mav.addObject("user", user);
            session.setAttribute("user",getUser);
        }else if ("admin2".equals(result)) {
            User getUser=userService.findUserByName(user.getUserName());
            mav.setViewName("adminindex2");
//            mav.addObject("user", user);
            session.setAttribute("user",getUser);
        } else if ("custom".equals(result)) {
            User getUser=userService.findUserByName(user.getUserName());

            List<Film>films=filmService.findAllFilm(1);
            mav.addObject("films",films);

            mav.setViewName("index");
//            mav.addObject("user", user);
            session.setAttribute("user",getUser);
        } else if ("nameErr".equals(result)) {
            mav.setViewName("login");
            mav.addObject("errMsg", "用户名错误！");
        } else if ("pwdErr".equals(result)) {
            mav.setViewName("login");
            mav.addObject("errMsg", "密码错误！");
        } else {
            mav.setViewName("login");
            mav.addObject("errMsg", "账号不可用，请联系管理员！");
        }
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session,ModelAndView mav)throws Exception{
        session.invalidate();//清空所有session
        List<Film>films=filmService.findAllFilm(1);
        mav.addObject("films",films);
        mav.setViewName("index");
        return mav;//登出后跳转到主页
    }
    @PostMapping("/searchUserByKey")
    public ModelAndView searchUserByKey(String key,ModelAndView mav)throws Exception{
        System.out.println("用户输入的关键字:"+key);
        List<User>users=userService.findUserByKey(key);
        System.out.println("查询到的数据量："+users.size());
        mav.addObject("users",users);
        mav.setViewName("adminindex1");
        return mav;

    }
    @GetMapping("/del/{userId}/{state}")
//    @ResponseBody
    public ModelAndView del(@PathVariable("userId")int userId,
                            @PathVariable("state")int state,ModelAndView mav)throws Exception{
        System.out.println("获取到的用户编号:"+userId);
        System.out.println("获取到的用户状态:"+state);

        boolean result=userService.updateUserState(state,userId);
        System.out.println("修改结果："+result);

        List<User> users =userService.findUserByKey("");
        mav.addObject("users",users);
        mav.setViewName("adminindex1");
        return mav;
    }

    @GetMapping("/toUpdate/{userId}")
//    @ResponseBody
    public ModelAndView toUpdate(@PathVariable("userId")int userId,ModelAndView mav)throws Exception{
        System.out.println("获取到的用户编号:"+userId);

        //1、根据用户编号查询单个用户信息
        User user=userService.findUserById(userId);
        mav.addObject("users",user);
        mav.setViewName("updateuser");
        return mav;
    }
    @PostMapping("/update")
    public ModelAndView update(User user,ModelAndView mav)throws Exception {
        System.out.println("获取到的用户信息:" + user);

        boolean result = userService.updateUser(user);
        System.out.println("修改结果：" + result);

        List<User> users = userService.findUserByKey("");
        mav.addObject("users", users);
        mav.setViewName("adminindex1");
        return mav;
    }
//    @PostMapping("/update1")
//    public ModelAndView update1(MultipartFile file,
//                                User user,ModelAndView mav)throws Exception{
//        System.out.println("获取到的用户信息："+user);
//        //1.文件上传 与 信息修改     分开处理
//        if (file.isEmpty()){
//            mav.addObject("msg","文件为空！");
//        }else{
//            String fileName=file.getOriginalFilename();
//            String suffixName=fileName.substring(fileName.lastIndexOf("."));
//            String newFileName=UUID.randomUUID()+suffixName;
//            String imagePath="D:\\upload\\image\\"+newFileName;
//            File dest =new File(imagePath);
//            if (!dest.getParentFile().exists()){
//                dest.getParentFile().mkdirs();
//            }
//            try{
//                file.transferTo(dest);
//            }catch (Exception e){
//                e.printStackTrace();
//                mav.addObject("msg","上传失败");
//            }
//            user.setUserImage(newFileName);
//            boolean result=userService.updateUser(user);
//            System.out.println("修改结果"+result);
//            //2.查询
//            List<User>users=userService.findUserByKey("");
//            mav.addObject("users",users);
//            mav.setViewName("adminindex");
//        }
//        return mav;
//    }
    @PostMapping("/update1")
    public ModelAndView update1(MultipartFile file,
                                User user, ModelAndView mav)throws Exception{
        System.out.println("获取到的用户信息:"+user);
        //1.文件上传  与   信息修改   分开处理
        if(file.isEmpty()){
            mav.addObject("msg","文件为空！");
        }else{
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID()+suffixName;//每次上传处理文件名   UUID生成前缀
            String imagePath = "D:\\upload\\image\\"+newFileName;
            File dest = new File(imagePath);
            //判断文件上级目录是否存在
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);//上传文件
            }catch (Exception e){
                e.printStackTrace();
                mav.addObject("msg","上传失败！");
            }
            user.setUserImage(newFileName);//注意  需要将处理之后的图片名 放入User 对象  用于更新数据库
        }
        boolean result = userService.updateUser(user);
        System.out.println("修改结果:"+result);
        //2.查询
        List<User> users = userService.findUserByKey("");
        mav.addObject("users",users);//定义的名字  users 可以自定义  与前台遍历内容对应
        mav.setViewName("adminindex1");

        return mav;
    }
}
