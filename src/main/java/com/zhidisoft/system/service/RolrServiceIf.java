package com.zhidisoft.system.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.CheckBox;
import com.zhidisoft.system.entity.Role;

public interface RolrServiceIf extends IBaseService<Integer, Role>{
	
    /**通过用户ID查询到所有权限，并且标记已经授权权限
     * @param UserId    用户ID
     * @return          树级角色集合
     */
    List<CheckBox> findRoleList(Integer UserId);
	
	/**通过用户ID删除
	 * @param userId       用户ID
	 * @return             大于0成功，小于0失败
	 */
	int deleteByUserId(Integer userId);
	
	/**添加用户角色
	 * @param userId       用户ID
	 * @param arr          数组，里面装的是勾选权限的ID
	 * @return             大于0成功，小于0失败
	 */
	int addUserRole(Integer userId,List<String> arr);
	
	/**修改角色对应的权限
	 * @param role         角色
	 * @param arr          数组，里面装的是勾选权限的ID
	 * @return             大于0成功，小于0失败
	 */
	int changRole(Role role,List<String> arr);
	
	/**删除角色，同时删除角色对应的所有权限    
	 * @param id       角色ID
	 * @return         大于0成功，小于0失败
	 */
	int deleteRole(Integer id);
}
