package com.rabbiter.oes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.BpjPerson;

import java.util.List;

public interface AdminService{

    int updatePWD(String userId);

    List<BpjPerson> dwonloadExcel();

    IPage<BpjPerson> selectAllPage(Page page);

    List<BpjPerson> selectByName(String name);

}
