package com.laughing.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.laughing.crowd.constant.CrowdConstant;
import com.laughing.crowd.entity.Role;
import com.laughing.crowd.service.RoleService;
import com.laughing.crowd.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoleController {
    @Resource
    private RoleService roleService;

    /**分页查询
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/role/get/page/info.json")
    public Object getRolePageInfo(@RequestParam(value = "keyword",defaultValue = "")String keyword,
    @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){

        return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_SUCCESS,
                null,roleService.getRoleByKeyword(keyword,pageNum,pageSize));
    }

    /**增加
     * @param role
     * @return
     */
    @RequestMapping("/role/addRole.json")
    public Object addRole(Role role){
        if(roleService.saveRole(role)>0){

            return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_SUCCESS,
                    CrowdConstant.ADDROLE_SUCCESS_MESSAGE,null);
        }else{
            return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_FAILED,
                    CrowdConstant.ADDROLE_FAILED_MESSAGE,null);
        }
    }

    /**修改
     * @param role
     * @return
     */
    @RequestMapping("/role/modifyRole.json")
    public Object modifyRole(Role role){
        if(roleService.updateRoleById(role)){

            return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_SUCCESS,
                    CrowdConstant.MODIFY_SUCCESS_MESSAGE,null);
        }else{
            return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_FAILED,
                    CrowdConstant.MODIFY_FAILED_MESSAGE,null);
        }
    }
    /**批量删除
     * @param list
     * @return
     */
    @RequestMapping("/role/delRole.json")
    public Object delRole(@RequestBody()List<Integer> list){
        if(roleService.removeRoleByList(list)){
            return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_SUCCESS,
                    CrowdConstant.DEL_SUCCESS_MESSAGE,null);
        }else{
            return new ResultEntity<PageInfo>(CrowdConstant.RESULT_CODE_FAILED,
                    CrowdConstant.DEL_FAILED_MESSAGE,null);
        }
    }
}
