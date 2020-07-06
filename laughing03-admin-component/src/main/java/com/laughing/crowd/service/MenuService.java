package com.laughing.crowd.service;

import com.laughing.crowd.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> getAllMenu();
    public boolean saveMenu(Menu menu);
    public boolean removeMenuById(Integer id);
    public boolean updateMenuById(Menu menu);

}
