package com.laughing.crowd.service.impl;

import com.laughing.crowd.entity.Menu;
import com.laughing.crowd.mapper.MenuMapper;
import com.laughing.crowd.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
//    返回根节点和所有子节点数据
    public List<Menu> getAllMenu() {
        return menuMapper.selectAllMenu();
    }

    /**增加一个节点
     * @param menu
     * @return
     */
    public boolean saveMenu(Menu menu) {
        if(menuMapper.insertMenu(menu)>0){
            return true;
        }
        return false;
    }

    /**删除节点
     * @param id
     * @return
     */
    public boolean removeMenuById(Integer id) {

        if(menuMapper.deleteMenuById(id)>0){
            return true;
        }
        return false;
    }

    /**修改节点
     * @param menu
     * @return
     */
    public boolean updateMenuById(Menu menu) {
        if(menuMapper.updateMenuById(menu)>0){
            return true;
        }
        return false;
    }
}
