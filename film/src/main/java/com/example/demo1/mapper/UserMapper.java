package com.example.demo1.mapper;

import com.example.demo1.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    //用户注册方法    使用注解方法实现mybaits操作
    //USER_NAME等是表中列名,#{userName} userName是User类中属性名

//    @Insert("INSERT INTO user(USER_NAME,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE)"+
//            "VALUES(#{userName},#{pwd},#{sex},#{birth},1,1,#{balance},0,#{phone})")

    int addUser(User user);
//根据用户名查询用户
    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE" +
            " FROM user WHERE USER_NAME=#{name}")
    User findUserByName(String name);

    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE" +
            " FROM user WHERE PHONE=#{phone}")
    User findUserByPhone(String phone);
    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE" +
            " FROM user")
    List<User> findAllUsers();
    //根据关键字查询用户信息 可能查询到多个内容 返回值使用List
//    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE"+
//    " FROM user WHERE userId LIKE '%${value}%' or USER_NAME LIKE '%${value}%'")
//准备做头像了
//    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE," +
//            "USER_IMAGE userImage FROM user WHERE USER_ID LIKE '%${value}%'or"+
//            "USER_NAME LIKE '%${value}%'")
    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE,"+
            "USER_IMAGE userImage FROM user WHERE USER_ID LIKE '%${value}%' or " +
            "USER_NAME LIKE '%${value}%'")
    List<User> findUserByKey(String key);
    //考虑到 管理员点击事件  可以切换用户状态
    @Update("UPDATE user SET STATE=#{state} WHERE USER_ID=#{userId}")
    int delUser(User user);

    @Select("SELECT USER_ID userId,USER_NAME userName,PWD,SEX,BIRTH,ROLE,STATE,BALANCE,SCORE,PHONE," +
            "USER_IMAGE userImage FROM user WHERE USER_ID=#{userId}")
    User findUserByID(int userId);
    //修改用户
    int updateUser(User user);
}
