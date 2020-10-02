package com.example.demo1.service;

import com.example.demo1.pojo.User;

import java.util.List;

public interface UserService {

    boolean checkRegist(User user);
    //根据用户名查用户
    User findUserByName(String name);
    //检查用户名是否重复 电话重复、用户名重复、均OK
    String checkNameAndPhone(User user);

    String checkRegist2(User user);
    boolean checkName(String name);
    List<User> findAllUsers();


    //用户登录业务逻辑
    //1、登陆成功（管理员、普通）    2、失败（用户名错、密码错、状态不可用）    使用int或String作为返回值
    String checkLogin(User user);
    List<User> findUserByKey(String key);
    Boolean updateUserState(int state,int userId);
    User findUserById(int userId);
    //根据用户输入条件 修改用户
    boolean updateUser(User user);
}
