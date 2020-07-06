package com.laughing.crowd.controller;

import com.laughing.crowd.constant.CrowdConstant;
import com.laughing.crowd.entity.Menu;
import com.laughing.crowd.service.MenuService;
import com.laughing.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Resource
    private MenuService menuService;

    /**返回根节点和所有子节点的json数据
     * @return
     */
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTreeNew() {

        // 1.查询全部的Menu对象
        List<Menu> menuList = menuService.getAllMenu();

        // 2.声明一个变量用来存储找到的根节点
        Menu root = null;
        // 3.创建Map对象用来存储id和Menu对象的对应关系便于查找父节点
        Map<Integer, Menu> menuMap = new HashMap<Integer, Menu>();
        // 4.遍历menuList填充menuMap
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }
        // 5.再次遍历menuList查找根节点、组装父子节点
        for (Menu menu : menuList) {
            // 6.获取当前menu对象的pid属性值
            Integer pid = menu.getPid();
            // 7.如果pid为null，判定为根节点
            if(pid == null) {
                root = menu;
                // 8.如果当前节点是根节点，那么肯定没有父节点，不必继续执行
                continue ;
            }
            // 9.如果pid不为null，说明当前节点有父节点，那么可以根据pid到menuMap中查找对应的Menu对象
            Menu father = menuMap.get(pid);
            // 10.将当前节点存入父节点的children集合
            father.getChildren().add(menu);
        }
        // 11.经过上面的运算，根节点包含了整个树形结构，返回根节点就是返回整个树
        return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_SUCCESS,null,root);
    }

    /**新增节点
     * @param menu
     * @return
     */
    @RequestMapping("/menu/saveMenu.json")
    public ResultEntity<Menu> saveMenu(Menu menu) {
        if(menuService.saveMenu(menu)){
            return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_SUCCESS,null,null);
        }
        return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_FAILED,CrowdConstant.Save_Anomaly_Exception,null);

    }
    /**修改菜单节点
     * @param menu
     * @return
     */
    @RequestMapping("/menu/updateMenu.json")
    public ResultEntity<Menu> updateMenu(Menu menu) {
        if(menuService.updateMenuById(menu)){
            return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_SUCCESS,null,null);
        }
        return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_FAILED,CrowdConstant.Modify_Anomaly_Exception,null);

    }
    /**删除节点
     * @param id
     * @return
     */
    @RequestMapping("/menu/removeMenu.json")
    public ResultEntity<Menu> removeMenu(@RequestParam("id")Integer id) {
        if(menuService.removeMenuById(id)){
            return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_SUCCESS,null,null);
        }
        return new ResultEntity<Menu>(CrowdConstant.RESULT_CODE_FAILED,CrowdConstant.Delete_Anomaly_Exception,null);

    }

//    public ResultEntity<Menu> getWholeTreeOld() {
//        List<Menu> menuList = menuService.getAllMenu();
//        //查询出所有菜单列表
//        Menu root = null;
//        for(Menu sonMenu:menuList){ //遍历菜单列表
//            Integer pid = sonMenu.getPid(); //拿到父id
//            if(pid==null){ //如果等于null证明是根节点
//                root=sonMenu;  //把根节点存入root 同一个内存地址
//                continue; //跳出本次循环
//            }
//            //如果不是继续遍历菜单列表
//            for(Menu faterMenu :menuList){
//                //拿到id
//                Integer id = faterMenu.getId();
//                //如果当前id等于父节点id
//                if(id.equals(pid)){
//                    //则添加一个子节点添加一个元素
//                    faterMenu.getChildren().add(sonMenu);
//                    break;
//                }
//            }
//        }
//
//
//        return new ResultEntity<Menu>(null,null,root);
//    }
}
