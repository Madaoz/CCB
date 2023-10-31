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

    List<BpjPerson> selectAll1(String name);

}
