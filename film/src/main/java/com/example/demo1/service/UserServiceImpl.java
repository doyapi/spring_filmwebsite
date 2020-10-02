package com.example.demo1.service;

import com.example.demo1.mapper.UserMapper;
import com.example.demo1.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("/userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean checkRegist(User user){
        int result=userMapper.addUser(user);
        if (result>0){
            return true;
        }
        return false;
    }

    @Override
    public User findUserByName(String name) {

        return userMapper.findUserByName(name);
    }

    public String checkNameAndPhone(User user){
        User getUserByName=userMapper.findUserByName(user.getUserName());
        User getUserByPhone=userMapper.findUserByPhone(user.getPhone());
        if (null!=getUserByName){
            return "nameErr";
        }
        if (null!=getUserByPhone){
            return "phoneErr";
        }
        return "ok";
    }

    @Override
    public String checkRegist2(User user) {
        String result =checkNameAndPhone(user);
        if ("ok".equals(result)){
            int i =userMapper.addUser(user);
            if (i>0){
                return "success";
            }else {
                return "fail";
            }
        }
        return result;
    }

    @Override
    public boolean checkName(String name) {
        User user=userMapper.findUserByName(name);
        if (null!=user){
            return false;
        }
        return true;
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public String checkLogin(User user) {
        User getUser=userMapper.findUserByName(user.getUserName());
        if (null!=getUser){
            //密码判断
            if(getUser.getPwd().equals(user.getPwd())){
                //还需要判断状态、角色
                if(1==getUser.getState()){
                    if(0==getUser.getRole()){
                        return "admin1";
                    }else if(9==getUser.getRole()){
                        return "admin2";
                    }else {
                        return "custom";
                    }
                }else {
                    return "startErr";
                }
            }else{
                return "pwdErr";
            }
        }else{
            return "nameErr";
        }
    }

    @Override
    public List<User> findUserByKey(String key) {

        return userMapper.findUserByKey(key);
    }

    @Override
    public Boolean updateUserState(int state, int userId) {

        User user=new User();
        user.setUserId(userId);
        if(0==state){
            user.setState(1);
        }else {
            user.setState(0);
        }
        int result=userMapper.delUser(user);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public User findUserById(int userId) {

        return userMapper.findUserByID(userId);
    }

    @Override
    public boolean updateUser(User user) {
        int i=userMapper.updateUser(user);
        if (i>0){
            return true;
        }
        return false;
    }


}
