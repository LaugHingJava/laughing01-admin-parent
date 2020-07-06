package com.laughing.crowd.mapper;

import com.laughing.crowd.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    public List<Menu> selectAllMenu();
    public int insertMenu(Menu menu);
    public int deleteMenuById(@Param("id") Integer id);
    public int updateMenuById(Menu menu);
}
