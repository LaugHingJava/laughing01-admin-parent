package com.laughing.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laughing.crowd.entity.Role;
import com.laughing.crowd.mapper.RoleMapper;
import com.laughing.crowd.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    public PageInfo<Role> getRoleByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleMapper.selectRoleByKeyword(keyword);

        return new PageInfo<Role>(list);
    }

    public int saveRole(Role role) {
        return roleMapper.insertRole(role);
    }

    public boolean updateRoleById(Role role) {
        if(roleMapper.updateRoleById(role)>0){
            return true;
        }
        return false;
    }

    public boolean removeRoleByList(List<Integer> list) {
        if(roleMapper.deleteRoleByList(list)>0){
            return true;
        }
        return false;
    }
}
