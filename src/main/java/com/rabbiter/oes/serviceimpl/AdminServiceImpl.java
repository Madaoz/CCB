package com.rabbiter.oes.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.Admin;
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
    public int updatePWD1(String userId) {
        return adminMapper.updatePWD1(userId);
    }

    @Override
    public int updatePWD2(String userUass) {
        return adminMapper.updatePWD2(userUass);
    }

    @Override
    public IPage<BpjPerson> selectAllPage(Page page){
        return adminMapper.selectAllPage(page);
    }

    @Override
    public List<BpjPerson> selectAll1(String name){
        return adminMapper.selectAll1(name);
    }

    @Override
    public List<BpjPerson> selectAll(){
        return adminMapper.selectAll();
    }
}
