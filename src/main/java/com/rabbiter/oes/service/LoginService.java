package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.Student;
import com.rabbiter.oes.entity.Teacher;
import com.rabbiter.oes.entity.User;

import java.util.List;

public interface LoginService {

    public Admin adminLogin(Integer username, String password);

    public Teacher teacherLogin(Integer username, String password);

    public Student studentLogin(Integer username, String password);

    public User userLogin(String userId,String passWord);
    public User userLogin1(String userId,String passWord);

    public List<User> leaderId(String userIntNO);
}
