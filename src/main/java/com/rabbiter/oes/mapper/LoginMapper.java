package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {

    @Select("select adminId,adminName,sex,tel,email,cardId,role from `admin` where adminId = #{username} and pwd = #{password}")
    public Admin adminLogin(@Param("username") Integer username, @Param("password") String password);


    @Select("select userId,userName,userUass,userInstName,role,grade from userInfo where userId = #{username} and passWord = #{password}")
    public User userLogin(@Param("username") String userName, @Param("password") String passWord);

    @Select("select userId,userName,userUass,userInstName,role,grade from userInfo where userUass = #{username} and passWord = #{password}")
    public User userLogin1(@Param("username") String userName, @Param("password") String passWord);

    @Select("select userId from userInfo where userInstName = #{userInstName} and role = '1'")
    public List<User> leaderId(@Param("userInstName") String userInstName);
}
