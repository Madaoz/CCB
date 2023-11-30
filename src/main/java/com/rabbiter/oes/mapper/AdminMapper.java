package com.rabbiter.oes.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    /**
     * 管理员根据8位员工编号重置用户登陆密码为初始密码123456
     *
     * @param userId
     * @return
     */
    @Update("update `userinfo` set passWord = '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92' where userId = #{userId}")
    int updatePWD(String userId);
    //不分页查询所有得分数据
    @Select("select * from leaderinfo")
    List<BpjPerson> dwonloadExcel();

    //分页查询所有得分数据
    @Select("select * from leaderinfo")
    IPage<BpjPerson> selectAllPage(Page page);

    @Select("select * from leaderinfo where name = #{name} and id = #{id}")
    List<BpjPerson> selectOne(String name,String id);

    @Select("select * from leaderinfo where id = #{id}")
    List<BpjPerson> selectById(String id);

    //根据姓名查询得分情况
    @Select("select * from leaderinfo where name = #{name}")
    List<BpjPerson> selectByName(String name);

    //查询新增数据是否已存在数据库中
    @Select("select * from userinfo where userId = #{pjId}")
    User findUserInfo(String pjId);
    //插入新的userinfo信息
    @Insert("insert into userinfo (userName,userId,passWord,userUass,userInstName,role) values (#{pjName},#{pjId},'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',#{pjUass},#{pjInstname},'2')")
    int insertUserInfo(String pjId, String pjName, String pjUass, String pjInstname);
    //更新userinfo的机构信息
    @Update("update userinfo set userInstName = #{pjInstname} where userId = #{pjId}")
    int updateUserInstName(String pjId,String pjInstname);
    //查询leader信息是否已存在
    @Select("select * from leaderinfo where id = #{bpjId}")
    BpjPerson findBpjPerson(String bpjId);
    //批量插入leader信息
    @Insert("insert into leaderinfo (id,name,uass,instName) values (#{bpjId},#{bpjName},#{bpjUass},#{bpjInstname})")
    int insertLeaderInfo(String bpjId,String bpjName,String bpjUass,String bpjInstname);
    //更新leaderinfo的机构
    @Update("update leaderinfo set instName = #{bpjInstname} where id = #{bpjId}")
    int updateleaderInfo(String bpjId,String bpjInstname);
    //查看此勾稽关系是否已经存在
    @Select("select * from score_manage where pj_id = #{pjId} and bpj_id = #{bpjId}")
    BpjPerson findScoreManage(String pjId,String bpjId);
    //批量导入勾稽关系表
    @Insert("insert into score_manage (pj_id ,pj_name ,pj_uass ,pj_instname ,bpj_id ,bpj_name ,bpj_uass ,bpj_instname ,level) values(#{pjId},#{pjName},#{pjUass},#{pjInstname},#{bpjId},#{bpjName},#{bpjUass},#{bpjInstname},#{level})")
    int insertScoreManage(String pjId,String pjName,String pjUass,String pjInstname,String bpjId,String bpjName,String bpjUass,String bpjInstname,String level);
    //更新评价人的机构信息
    @Update("update score_manage set pj_instname = #{pjInstname} where pj_id = #{pjId}")
    int updateScorePJInstName(String pjId,String pjInstname);
    //更新被评价人机构信息
    @Update("update score_manage set bpj_instname = #{bpjInstname} where bpj_id = #{bpjInstname}")
    int updateScoreBPJInstName(String bpjId,String bpjInstname);
    //清空leaderinfo表内所有字段数据
    @Delete("delete from leaderinfo")
    int resetLeaderInfo();
    //清空score_manage表内所有字段数据
    @Delete("delete from score_manage")
    int resetScoreManage();
}
