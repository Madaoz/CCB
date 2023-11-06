package com.rabbiter.oes.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.BpjPerson;
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

    //通过姓名查询得分情况
    @Override
    public List<BpjPerson> selectByName(String name){
        return adminMapper.selectByName(name);
    }
    //不分页查询所有得分情况，导出得分情况
    @Override
    public List<BpjPerson> dwonloadExcel(){
        return adminMapper.dwonloadExcel();
    }
}
