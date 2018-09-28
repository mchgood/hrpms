package com.zhidisoft.system.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.system.dao.RoleDao;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.system.service.UserServiceIf;
import com.zhidisoft.until.CreatePassWordUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl<Integer, User> implements UserServiceIf{
	@Resource
	UserDao userDao;
	
	/*通过用户名查询用户*/
	public boolean findByName(User user) {
		User dbUser = userDao.findByName(user.getUsername());
		if(dbUser.getPassword().equals(user.getPassword())) {
			return true;
		}else {
			return false;
		}
	}

	/*通过用户名更新用户信息*/
    @Override
    public int updateByusername(User user) {
        //加密传递过来的密码
        String username = user.getUsername();
        String password = user.getPassword();
        Map enctPwd = CreatePassWordUtil.enctPwd(username, password);
        user.setPassword((String)(enctPwd.get("pwd")));
        user.setSalt((String)(enctPwd.get("salt")));
        User dbUser = null;
        
        int rows = 0;
        if(user.getId()!=null) {
            dbUser = userDao.selectByPrimaryKey(user.getId());
            user.setCreatetime(dbUser.getCreatetime());
            user.setUpdatetime(new Date());
            rows = userDao.updateByusername(user);
        }else {
            user.setCreatetime(new Date());
            rows = userDao.insert(user);
        }
        return rows;
    }

    /*通过用户ID逻辑删除用户 */
    @Override
    public int deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }
}
