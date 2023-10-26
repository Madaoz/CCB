package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.BpjPerson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Update("update `userinfo` set passWord = '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92' where userId = #{userId}")
    public int updatePWD1(String userId);

    @Update("update `userinfo` set passWord = '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92' where userUass = #{userUass}")
    public int updatePWD2(String userUass);

    @Select("select * from leaderinfo")
    public List<BpjPerson> selectAll();

    @Select("select adminName,sex,tel,email,cardId,role from `admin`")
    public List<Admin> findAll();

    @Select("select adminId,adminName,sex,tel,email,cardId,role,pwd from `admin` where adminId = #{adminId}")
    public Admin findById(Integer adminId);

    @Delete("delete from `admin` where adminId = #{adminId}")
    public int deleteById(int adminId);

    @Update("update `admin` set adminName = #{adminName},sex = #{sex}," +
            "tel = #{tel}, email = #{email},pwd = #{pwd},cardId = #{cardId},role = #{role} where adminId = #{adminId}")
    public int update(Admin admin);

    @Options(useGeneratedKeys = true,keyProperty = "adminId")
    @Insert("insert into `admin`(adminName,sex,tel,email,pwd,cardId,role) " +
            "values(#{adminName},#{sex},#{tel},#{email},#{pwd},#{cardId},#{role})")
    public int add(Admin admin);
}
