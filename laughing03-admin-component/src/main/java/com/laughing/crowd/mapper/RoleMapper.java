package com.laughing.crowd.mapper;

import com.laughing.crowd.entity.Menu;
import com.laughing.crowd.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<Role> selectRoleByKeyword(@Param("keyword") String keyword);
    public int insertRole(Role role);
    public int updateRoleById(Role role);
    public int deleteRoleByList(List<Integer> list);

}
