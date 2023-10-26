package com.rabbiter.oes.serviceimpl;

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
    public List<BpjPerson> selectAll(){
        return adminMapper.selectAll();
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public Admin findById(Integer adminId) {
        return adminMapper.findById(adminId);
    }

    @Override
    public int deleteById(int adminId) {
        return adminMapper.deleteById(adminId);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public int add(Admin admin) {
        return 0;
    }

    @Override
    public Object resetPsw(Integer adminId, String newPsw, String oldPsw) {
        Admin admin = findById(adminId);

        if(!admin.getPwd().equals(oldPsw)) {
            return "原密码错误";
        }
        admin.setPwd(String.valueOf(newPsw));
        update(admin);
        return true;
    }


}
