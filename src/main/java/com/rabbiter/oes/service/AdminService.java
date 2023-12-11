package com.rabbiter.oes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.User;

import java.util.List;

public interface AdminService{

    int updatePWD(String userId);

    List<BpjPerson> dwonloadExcel();

    IPage<BpjPerson> selectAllPage(Page page);

    List<BpjPerson> selectOne(String name,String id);

    List<BpjPerson> selectById(String id);

    List<BpjPerson> selectByName(String name);

    User findUserInfo(String pjId);

    int insertUserInfo(String pjId,String pjName,String pjUass,String pjInstname);

    int updateUserInstName(String pjId,String pjInstname);

    BpjPerson findBpjPerson(String bpjId);

    int insertLeaderInfo(String bpjId,String bpjName,String bpjUass,String bpjInstname);

    int updateleaderInfo(String bpjId,String bpjInstname);

    BpjPerson findScoreManage(String pjId,String bpjId);

    int insertScoreManage(String pjId,String pjName,String pjUass,String pjInstname,String bpjId,String bpjName,String bpjUass,String bpjInstname,String level);

    int updateScorePJInstName(String pjId,String pjInstname);

    int updateScoreBPJInstName(String bpjId,String bpjInstname);

    int resetLeaderInfo();

    int resetScoreManage();

    int resetOptionInfo();

    List<BpjPerson> downSubmit();
}
