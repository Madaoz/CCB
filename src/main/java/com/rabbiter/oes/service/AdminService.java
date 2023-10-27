package com.rabbiter.oes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.BpjPerson;

import java.util.List;

public interface AdminService{

    public int updatePWD1(String userId);

    public int updatePWD2(String userUass);

    public List<BpjPerson> selectAll();

    IPage<BpjPerson> selectAllPage(Page page);

    public List<Admin> findAll();

    public Admin findById(Integer adminId);

    public int deleteById(int adminId);

    public int update(Admin admin);

    public int add(Admin admin);

    Object resetPsw(Integer adminId, String newPsw, String oldPsw);
}
