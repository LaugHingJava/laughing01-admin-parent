package com.laughing.crowd.service;

import com.github.pagehelper.PageInfo;
import com.laughing.crowd.entity.Admin;

public interface AdminService {
    public boolean saveAdmin(Admin admin);

    public Admin getAdminByLoginAcct(String loginAcct,String password);
    public PageInfo<Admin>  getAdminPage(String keyword,Integer pageNum,Integer pageSize);
    boolean removeByPrimaryKey(Integer id);
    public Admin getAdminById(Integer id);
    public void updateAdminById(Admin admin);
}
