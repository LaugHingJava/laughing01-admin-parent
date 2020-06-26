package com.laughing.crowd.service.impl;

import com.laughing.crowd.entity.Admin;
import com.laughing.crowd.mapper.AdminMapper;
import com.laughing.crowd.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    public void saveAdmin(Admin admin) {
        int num=adminMapper.insert(admin);


    }
}
