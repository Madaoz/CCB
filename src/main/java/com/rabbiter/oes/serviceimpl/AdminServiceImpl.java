package com.rabbiter.oes.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.mapper.AdminMapper;
import com.rabbiter.oes.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int updatePWD(String userId) {
        return adminMapper.updatePWD(userId);
    }

    //分页查询得分情况
    @Override
    public IPage<BpjPerson> selectAllPage(Page page){
        return adminMapper.selectAllPage(page);
    }

    //通过姓名和id查询得分情况
    @Override
    public List<BpjPerson> selectOne(String name,String id){
        return adminMapper.selectOne(name,id);
    }

    //通过姓名查询得分情况
    @Override
    public List<BpjPerson> selectByName(String name){
        return adminMapper.selectByName(name);
    }

    //通过姓名查询得分情况
    @Override
    public List<BpjPerson> selectById(String id){
        return adminMapper.selectById(id);
    }

    //不分页查询所有得分情况，导出得分情况
    @Override
    public List<BpjPerson> dwonloadExcel(){
        return adminMapper.dwonloadExcel();
    }

    @Override
    public User findUserInfo(String pjId){
        return adminMapper.findUserInfo(pjId);
    }

    @Override
    public int insertUserInfo(String pjId,String pjName,String pjUass,String pjInstname){
        return adminMapper.insertUserInfo(pjId,pjName,pjUass,pjInstname);
    }

    @Override
    public int updateUserInstName(String pjId,String pjInstname){
        return adminMapper.updateUserInstName(pjId,pjInstname);
    }

    @Override
    public BpjPerson findBpjPerson(String bpjId){
        return adminMapper.findBpjPerson(bpjId);
    }

    @Override
    public int insertLeaderInfo(String bpjId,String bpjName,String bpjUass,String bpjInstname){
        return adminMapper.insertLeaderInfo(bpjId,bpjName,bpjUass,bpjInstname);
    }

    @Override
    public int updateleaderInfo(String bpjId,String bpjInstname){
        return adminMapper.updateleaderInfo(bpjId,bpjInstname);
    }

    @Override
    public BpjPerson findScoreManage(String pjId,String bpjId){
        return adminMapper.findScoreManage(pjId,bpjId);
    }

    @Override
    public int insertScoreManage(String pjId,String pjName,String pjUass,String pjInstname,String bpjId,String bpjName,String bpjUass,String bpjInstname,String level){
        return adminMapper.insertScoreManage(pjId,pjName,pjUass,pjInstname,bpjId,bpjName,bpjUass,bpjInstname,level);
    }

    @Override
    public int updateScorePJInstName(String pjId,String pjInstname){
        return adminMapper.updateScorePJInstName(pjId,pjInstname);
    }

    @Override
    public int updateScoreBPJInstName(String bpjId,String bpjInstname){
        return adminMapper.updateScoreBPJInstName(bpjId,bpjInstname);
    }

    @Override
    public int resetLeaderInfo(){
       return adminMapper.resetLeaderInfo();
    }

    @Override
    public int resetScoreManage(){
        return adminMapper.resetScoreManage();
    }
}
