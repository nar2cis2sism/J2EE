package com.project.server.storage.dao;

import static engine.java.common.MyValidator.validate;

import java.util.Collection;

import com.project.server.storage.DAOManager.BaseDAO;
import com.project.server.storage.db.UserInfo;

import engine.java.common.MyValidator;
import engine.java.dao.DAOTemplate.DAOExpression;
import engine.java.dao.DAOTemplate.DAOParam;
import engine.java.dao.Page;

public class UserDAO extends BaseDAO {
    
    /**
     * 根据用户名获取用户信息
     */
    public static UserInfo getUserByUsername(String username) {
        return findItemByProperty(UserInfo.class, "username", username);
    }
    
    /**
     * 根据用户ID获取用户信息
     */
    public static UserInfo getUserById(long uid) {
        return findItemByProperty(UserInfo.class, "uid", uid);
    }
    
    /**
     * 通过关键字搜索联系人
     * 
     * @return 满足条件的总数量
     */
    public static long findCountByKey(String key) {
        return dao.find(UserInfo.class).where(whereByKey(key)).getCount();
    }
    
    /**
     * 通过关键字搜索联系人
     * 
     * @param page 搜索范围
     */
    public static Collection<UserInfo> findByKey(String key, Page page) {
        return dao.find(UserInfo.class).where(whereByKey(key)).usePage(page).getAll();
    }
    
    private static DAOExpression whereByKey(String key) {
        // 用户名模糊查找
        DAOExpression where = DAOExpression.create(new DAOParam("username").lower()).like("%" + key.toLowerCase() + "%");
        
        if (validate(key, MyValidator.EMAIL_ADDRESS))
        {
            // 邮箱不区分大小写
            where = where.or(new DAOParam("register_email").lower()).equal(key.toLowerCase());
        }
        else if (validate(key, MyValidator.MOBILE_NUMBER))
        {
            // 手机号精确查找
            where = where.or("mobile_number").equal(key);
        }
        
        return where;
    }
}