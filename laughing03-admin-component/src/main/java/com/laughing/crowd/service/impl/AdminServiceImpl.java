package com.laughing.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laughing.crowd.constant.CrowdConstant;
import com.laughing.crowd.entity.Admin;
import com.laughing.crowd.exception.DeleteAnomalyException;
import com.laughing.crowd.exception.LoginFailedException;
import com.laughing.crowd.exception.ModifyAnomalyException;
import com.laughing.crowd.exception.SaveAnomalyException;
import com.laughing.crowd.mapper.AdminMapper;
import com.laughing.crowd.service.AdminService;
import com.laughing.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    /**增加用户
     * @param admin
     * @return
     */
    public boolean saveAdmin(Admin admin) {
        admin.setUserPswd(CrowdUtil.Md5(admin.getUserPswd()));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        admin .setCreateTime(format.format(date));
        try{
            int num=adminMapper.insert(admin);
            if(num>0){
                return true;
            }
        }catch (Exception e){
            logger.info(CrowdConstant.Save_Anomaly_Exception);
            throw new SaveAnomalyException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
        }
        return false;


    }

    /**登录 根据用户输入账号密码判断数据库是否存在然后返回
     * @param loginAcct
     * @param password
     * @return
     */
    public Admin getAdminByLoginAcct(String loginAcct, String password) {
        Admin admin = adminMapper.selectAdminByLoginAcct(loginAcct);
        if(admin!=null){
            String userPswd = admin.getUserPswd();
            String mdtPswd = CrowdUtil.Md5(password);

            if(userPswd.equals(mdtPswd)){
                return admin;
            }
        }
        throw new LoginFailedException("用户名密码错误");

    }

    /**返回用户查询对应的数据
     * @param keyword 传入的参数
     * @param pageNum 第几页
     * @param pageSize 页面容量
     * @return PageInfo
     */
    public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> adminList = adminMapper.selectAdminListByKeyword(keyword);

        return new PageInfo<Admin>(adminList);
    }

    /**删除
     * @param id
     * @return true成功false失败
     */
    public boolean removeByPrimaryKey(Integer id) {
        if(adminMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
        throw new DeleteAnomalyException(CrowdConstant.Delete_Anomaly_Exception);
    }

    /**根据id查找对应的信息
     * @param id
     * @return
     */
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**根据id修改admin
     * @param admin
     */
    public void updateAdminById(Admin admin) {
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new ModifyAnomalyException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }
}
