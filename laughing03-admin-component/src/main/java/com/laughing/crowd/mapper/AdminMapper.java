package com.laughing.crowd.mapper;

import com.laughing.crowd.entity.Admin;
import com.laughing.crowd.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);


    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);



    int updateByPrimaryKey(Admin record);

    //根据登录用户名查找对应用户
    public Admin selectAdminByLoginAcct(@Param("loginAcct")String loginAcct);
    // 根据用户条件模糊查询
    List<Admin> selectAdminListByKeyword(@Param("keyword") String keyword);
    //根据id删除
    int deleteByPrimaryKey(Integer id);
    //根据id查询
    Admin selectByPrimaryKey(Integer id);
    //根据id修改
    int updateByPrimaryKeySelective(Admin admin);
}