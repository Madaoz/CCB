package com.rabbiter.oes.mapper;

import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.OtherScore;
import com.rabbiter.oes.entity.SelfScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OthersEvaluationMapper {

    //用8为员工编号登录
    @Select("select bpj_name bpjName,bpj_id bpjId,score,bpj_instname instname from score_manage where pj_id = #{userId} and  (ISNULL(score) or score = '') order by bpj_uass ")
    List<BpjPerson> findById(String userId);
    //用uass登录
    @Select("select bpj_name bpjName,bpj_id bpjId,score,bpj_instname instname " +
            "from score_manage where pj_id = #{userId} and NOT (ISNULL(score) or score = '') order by bpj_uass")
    List<BpjPerson> findByUass(String userId);

    //将他人测评得分更新到score_manage表中
    @Update("Update score_manage set score = #{score} where pj_id = #{pjid} and bpj_id = #{bpjid}")
    int updataOthersScore(OtherScore otherScore);

    //根据勾稽关系标志，将上同下级得分的平均分更新到leaderinfo表中
    @Update("Update leaderinfo t set superior = (select avg(score) from score_manage where bpj_id = t.id and level = '2' and not(ISNULL(score) or score = ''))," +
            "equal = (select avg(score) from score_manage where bpj_id = t.id and level = '1' and not(ISNULL(score) or score = ''))," +
            "subordinate = (select avg(score) from score_manage where bpj_id = t.id and level = '0' and not(ISNULL(score) or score = ''))," +
            "superiorNm = (select count(bpj_id) from score_manage where bpj_id = T.id and NOT (ISNULL(score) or score = '') and level = '2' and not(ISNULL(score) or score = '')),"+
            "equalNm = (select count(bpj_id) from score_manage where bpj_id = T.id and NOT (ISNULL(score) or score = '') and level = '1' and not(ISNULL(score) or score = '')),"+
            "subordinateNm = (select count(bpj_id) from score_manage where bpj_id = T.id and NOT (ISNULL(score) or score = '') and level = '0' and not(ISNULL(score) or score = ''))"+
            "where exists (select 1 from score_manage where bpj_id = t.id)")
    int updateLeaderInfo();
    @Update("UPDATE leaderinfo t set totalscore = (coalesce(t.equal,0) + coalesce(t.selfevaluation,0) + coalesce(t.superior,0) + coalesce(t.subordinate,0))," +
            "totalNm = (coalesce(t.equalNm,0) + coalesce(t.superiorNm,0) + coalesce(t.subordinateNm,0))")
    int udeateNm();

}
