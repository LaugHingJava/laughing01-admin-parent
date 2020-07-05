package com.laughing.crowd.service;

import com.github.pagehelper.PageInfo;
import com.laughing.crowd.entity.Role;

import java.util.List;

public interface RoleService {
    public PageInfo<Role> getRoleByKeyword(String keyword, Integer pageNum, Integer pageSize);
    public int saveRole(Role role);
    public boolean updateRoleById(Role role);
    public boolean removeRoleByList(List<Integer> list);
}
