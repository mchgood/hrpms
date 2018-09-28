package com.zhidisoft.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.zhidisoft.system.dao.DictDao;
import com.zhidisoft.system.dao.FunctionDao;
import com.zhidisoft.system.dao.RoleDao;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.entity.User;


/**  
 * 自定义relam,用于进行权限管理
* @author tkc  
* @date 2018年9月12日  
*/ 
@SuppressWarnings("all")
public class AhtuRealm extends AuthorizingRealm{
	@Resource
	UserDao userDao;
	@Resource
	RoleDao roleDao;
	@Resource
	FunctionDao functionDao;
	@Resource
	DictDao dictDao;
	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = ((UsernamePasswordToken)token).getUsername();
		
		// 根据username从数据库查询用户信息
		User root = userDao.findByName(username);
		if(root == null){
			return null;
		}
		//获取session
		Session session = SecurityUtils.getSubject().getSession();
		List<Dict> DictList = dictDao.selectAll();
		session.setAttribute("DictList", DictList);
		session.setAttribute("root", root);
		return new SimpleAuthenticationInfo(root,root.getPassword(),ByteSource.Util.bytes(root.getSalt()),"authRealm");
	}
	
	/*
	 * 授权
	 *  */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前用户
		User user = (User)principals.getPrimaryPrincipal();
		//获取用户角色集合
		List<Role> roleList = roleDao.findRoleList(user.getId());
		// 遍历角色集合，将角色名称保存到set集合中
		Set stringFun = new HashSet();
		for (Role role : roleList) {
			List<Function> funList = functionDao.findFunListByRoleId(role.getId());
			//stringRole.add(role.getId());
			for (Function fun : funList) {
				if (fun!=null) {
					stringFun.add(fun.getFuncname());
				}
				List<Function> funListByParentId = functionDao.findFunListByParentId(fun.getId());
				for (Function fn : funListByParentId) {
				    stringFun.add(fn.getFuncurl());
                }
			}	
		}
		
		// 将 角色的set 及 权限的set 保存到 SimpleAuthorizationInfo
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		info.setStringPermissions(stringFun);
		return info;
	}
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}