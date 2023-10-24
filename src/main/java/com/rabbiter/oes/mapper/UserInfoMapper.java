package com.rabbiter.oes.mapper;


import com.rabbiter.oes.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoMapper {

    /**
     * 更新密码
     * @param user
     * @return 受影响的记录条数
     */
    @Update("update userinfo set passWord = #{passWord} where userId = #{userId}")
    int updatePwd(User user);
}
