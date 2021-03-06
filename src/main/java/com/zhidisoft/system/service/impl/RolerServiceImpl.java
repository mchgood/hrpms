package com.zhidisoft.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.CheckBox;
import com.zhidisoft.system.dao.RoleDao;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.service.RolrServiceIf;

@Service
public class RolerServiceImpl extends BaseServiceImpl<Integer, Role> implements RolrServiceIf {
	
	@Resource
	RoleDao roleDao;
	@Resource
	UserDao userDao;
	
	/*通过用户ID查询角色集合*/
	public List<CheckBox> findRoleList(Integer UserId){
	    List list = new ArrayList<CheckBox>();
	    //通过userId查询到user的所有角色信息
		List<Role> selectRoleList = roleDao.findRoleList(UserId);
		List<Role> roleList = findAll();
		for (Role role : roleList) {
		    CheckBox checkBox = new CheckBox();
		    checkBox.setId(role.getId());
            checkBox.setName(role.getRolename());
            for (Role select : selectRoleList) {
                if(role.getId()==select.getId()) {
                    checkBox.setStatus(1);
                }
            }
            list.add(checkBox);
        }
		return list;
	}
	
    /*通过用户ID删除*/
    @Override
    public int deleteByUserId(Integer userId) {
        int row = roleDao.deleteByUserId(userId);
        return row;
    }
    
    /*添加用户角色*/
    @Override
    public int addUserRole(Integer userId,List<String> arr) {
        if(arr!=null) {
            for (String str : arr) {
                if(roleDao.addUserRole(userId, Integer.valueOf(str))<0) {
                    return -1;
                } 
            }
        }else {
            return -1;
        }
        
        return 1;
    }
    
    /*更新角色*/
    public int changRole(Role role,List<String> arr) {
        //首先判断是添加角色还是修改角色
        if(role.getId()!=null) {
            //修改角色 
            //设置修改时间
            role.setUpdatetime(new Date());
            //更新role信息
            if(roleDao.updateByPrimaryKey(role)<0) {
                return -1;
            }
            //修改角色权限
            //删除角色权限
            if(roleDao.deleteRoleFunctionByRoleId(role.getId())<0) {
                return -1;
            }
            //添加角色权限
            for (String str : arr) {
                if(roleDao.addRoleFunction(role.getId(), Integer.valueOf(str))<0) {
                    return -1;
                }
            }
        }else {
            //添加角色
            //设置创建时间
            role.setCreatetime(new Date());
            if(roleDao.insert(role)<0) {
                return -1;
            }
            
            //添加角色权限关联
            for (String str : arr) {
                if(roleDao.addRoleFunction(role.getId(), Integer.valueOf(str))<0) {
                    return -1;
                }
            }
        }
        return 1;
    }
    
    /*删除角色*/
    @Override
    public int deleteRole(Integer id) {
        //删除角色
        if(roleDao.deleteByPrimaryKey(id)<0) {
            return -1;
        }
        
        //删除角色所有权限
        if(roleDao.deleteRoleFunctionByRoleId(id)<0) {
            return -1;
        }
        return 1;
    }
}
