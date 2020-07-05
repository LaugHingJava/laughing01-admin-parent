package com.laughing.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.laughing.crowd.constant.CrowdConstant;
import com.laughing.crowd.entity.Admin;
import com.laughing.crowd.service.AdminService;
import com.laughing.crowd.util.CrowdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

//管理员控制器
@Controller
public class AdminController {
    @Resource
    private AdminService adminService;
    /**处理登录
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @PostMapping("/do/login.html")
    public Object doLogin(@RequestParam("loginAcct") String loginAcct,
                                @RequestParam("userPswd") String userPswd, HttpSession session){

        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);

        // 将登录成功返回的admin 对象存入Session 域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main.html";//要用重定向否则每次刷新都请求一次后台
    }

    /**退出登录
     * @param session
     * @return
     */
    @RequestMapping("/do/loginOut.html")
    public Object loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login.html";
    }

    @RequestMapping("/admin/page.html")
    public Object loginOut(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,ModelMap modelMap){


        PageInfo<Admin>  pageInfo= adminService.getAdminPage(keyword,pageNum,pageSize);

        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);

        return "admin-page";
    }

    /**删除后重定向分页之前停留哪里哪里
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @RequestMapping("/admin/delAdminById.html")
    public Object delAdminById(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               @RequestParam("id") Integer id){


        if(adminService.removeByPrimaryKey(id)){

            // 页面跳转：回到分页页面

            // 尝试方案1：直接转发到admin-page.jsp会无法显示分页数据
            // return "admin-page";

            // 尝试方案2：转发到/admin/get/page.html地址，一旦刷新页面会重复执行删除浪费性能因为重定向url有参数会重新请求
            // return "forward:/admin/get/page.html";

            // 尝试方案3：重定向到/admin/get/page.html地址
            // 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
            return "redirect:/admin/page.html?keyword="+keyword+"&pageNum="+pageNum;
        }
        return "system-error";
    }

    /**增加页面
     * @param admin
     * @return
     */
    @RequestMapping("/admin/addAdmin.html")
    public Object addAdmin(Admin admin){
        if(adminService.saveAdmin(admin)){
            return "redirect:/admin/page.html?pageNum="+Integer.MAX_VALUE;
        }
        return "system-error";
    }

    /**根据id查出数据并且跳转修改页面
     * @param id
     * @return
     */
    @RequestMapping("/admin/toModifyAdmin.html")
    public Object toModifyAdmin(@RequestParam("id") Integer id,ModelMap modelMap){
        Admin admin = adminService.getAdminById(id);
       if(admin!=null){
           modelMap.addAttribute(CrowdConstant.ATTR_NAME_ADMIN,admin);
           return "admin-modify";
       }

        return "admin-page";
    }


    /**修改admin
     * @param admin
     * @return
     */
    @RequestMapping("/admin/modifyAdmin.html")
    public Object modifyAdmin(Admin admin,@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        System.out.println(admin.getId());
        adminService.updateAdminById(admin);
        return "redirect:/admin/page.html?keyword="+keyword+"&pageNum="+pageNum;
    }
}
