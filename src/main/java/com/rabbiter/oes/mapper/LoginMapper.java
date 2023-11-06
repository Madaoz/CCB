package com.rabbiter.oes.mapper;
import com.rabbiter.oes.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {

    //通过8位员工编号登录
    @Select("select userId,userName,userUass,userInstName,role,grade from userInfo where userId = #{username} and passWord = #{password}")
    User userLoginById(@Param("username") String userName, @Param("password") String passWord);

    //通过uass编号登录
    @Select("select userId,userName,userUass,userInstName,role,grade from userInfo where userUass = #{username} and passWord = #{password}")
    User userLoginByUass(@Param("username") String userName, @Param("password") String passWord);

}
