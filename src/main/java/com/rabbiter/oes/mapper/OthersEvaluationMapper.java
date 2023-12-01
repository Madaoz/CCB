package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.Option;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.entity.SelfScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OthersEvaluationMapper {

    //查询未评价信息
//    @Select("select bpj_name bpjName,bpj_id bpjId,score,bpj_instname instname ,level from score_manage where pj_id = #{userId} and (ISNULL(score) or score = '') order by bpj_uass ")
    @Select("select bpj_name bpjName,bpj_id bpjId,score,bpj_instname instname ,level from score_manage where pj_id = #{userId} and (ISNULL(score) or score = '')")
    List<BpjPerson> findById(String userId);

    //查询已评价人员
//    @Select("select bpj_name bpjName,bpj_id bpjId,score,bpj_instname instname " +
//            "from score_manage where pj_id = #{userId} and NOT (ISNULL(score) or score = '') order by bpj_uass")
    @Select("select bpj_name bpjName,bpj_id bpjId,score,bpj_instname instname " +
            "from score_manage where pj_id = #{userId} and NOT (ISNULL(score) or score = '') order by score")
    List<BpjPerson> findByUass(String userId);

//    //将他人测评得分更新到score_manage表中
//    @Update("Update score_manage set score = #{score},A = #{scoreA},B = #{scoreB},C = #{scoreC},D = #{scoreD} ,E = #{scoreE} where pj_id = #{pjid} and bpj_id = #{bpjid}")
//    int updataOthersScore(OtherScore otherScore);

    //将他人测评得分从optioninfo表中取数，更新到score_manage表中
    @Update("update score_manage s set submit = '1' where pj_id = #{pjid} and bpj_id = #{bpjid}")
    int updataOthersScore(String pjid,String bpjid);

    //根据勾稽关系标志，将上同下级得分的平均分更新到leaderinfo表中
    @Update("Update leaderinfo t set superior = (select avg(score) from score_manage where bpj_id = t.id and level = '2' and not(ISNULL(score) or score = ''))," +
            "equal = (select avg(score) from score_manage where bpj_id = t.id and level = '1' and not(ISNULL(score) or score = ''))," +
            "subordinate = (select avg(score) from score_manage where bpj_id = t.id and level = '0' and not(ISNULL(score) or score = ''))," +
            "superiorNm = (select count(bpj_id) from score_manage where bpj_id = t.id and NOT (ISNULL(score) or score = '') and level = '2' and not(ISNULL(score) or score = ''))," +
            "equalNm = (select count(bpj_id) from score_manage where bpj_id = t.id and NOT (ISNULL(score) or score = '') and level = '1' and not(ISNULL(score) or score = ''))," +
            "subordinateNm = (select count(bpj_id) from score_manage where bpj_id = t.id and NOT (ISNULL(score) or score = '') and level = '0' and not(ISNULL(score) or score = ''))" +
            "where exists (select 1 from score_manage where bpj_id = t.id)")
    int updateLeaderInfo();

    //根据勾稽关系标志，将不同类型得分的平均分更新到leaderinfo表中
    @Update("update leaderinfo t set A = (select avg(A) from score_manage where bpj_id = t.id and not(ISNULL(A) or A = ''))," +
            "B = (select avg(B) from score_manage where bpj_id = t.id and not(ISNULL(B) or B = ''))," +
            "C = (select avg(C) from score_manage where bpj_id = t.id and not(ISNULL(C) or C = ''))," +
            "D = (select avg(D) from score_manage where bpj_id = t.id and not(ISNULL(D) or D = ''))," +
            "E = (select avg(E) from score_manage where bpj_id = t.id and not(ISNULL(E) or E = ''))" +
            "where exists (select 1 from score_manage where bpj_id = t.id)")
    int updateLeaderMark();

    //更新根据题目类型得到的平均分的总得分
    @Update("update leaderinfo t set markTotalScore = coalesce(t.A,0) + coalesce(t.B,0) + coalesce(t.C,0) + coalesce(t.D,0) + coalesce(t.E,0)")
    int updateLeaderMarkTotal();

    //更新总得分和总评价人数
    @Update("UPDATE leaderinfo t set totalscore = (coalesce(t.equal,0) + coalesce(t.superior,0) + coalesce(t.subordinate,0))," +
            "totalNm = (coalesce(t.equalNm,0) + coalesce(t.superiorNm,0) + coalesce(t.subordinateNm,0))")
    int udeateNm();

    //查询optioninfo表中是否存在对应关系
    @Select("select * from optioninfo where pjId = #{pjId} and bpjId = #{bpjId}")
    OtherScore findOption(String pjId,String bpjId);

    //optioninfo表中插入新的对应关系
    @Insert("insert into optioninfo (pjId,bpjId,quId,option,score) values (#{pjId},#{bpjId},#{quId},#{option},#{score})")
    int insertOption(String pjId,String bpjId,int quId,int option,String score);

    //更新optioninfo表中的题目的选项及得分
    @Update("update optioninfo set option = #{option} where pjId = #{pjId} and bpjId = #{bpjId} and quId = #{quId}")
    int updateOption(String pjId,String bpjId,int quId,int option);

    @Update("update optioninfo set score = #{score} where pjId = #{pjId} and bpjId = #{bpjId}")
    int updateOptionScore(String pjId,String bpjId,String score);

    @Update("update score_manage set score = #{score},A = #{scoreA},B = #{scoreB},C = #{scoreC},D = #{scoreD},E = #{scoreE} where pj_id = #{pjid} and bpj_id = #{bpjid}")
    int updateScoreManage(OtherScore otherScore);

    @Select("select bpjId from optioninfo where pjId = #{pjid}")
    List<Option> findBpjId(String pjid);

    @Update("update score_manage set submit = '1' where pj_id = #{pjid} and bpj_id = #{bpjid}")
    int updateSubmit(String pjid,String bpjid);
}
