package com.zhidisoft.system.dao;

import com.zhidisoft.base.IBaseDao;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.entity.Role;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleDao extends IBaseDao<Integer, Role>{

	/**通过用户ID查询角色集合
	 * @param userId   用户ID
	 * @return
	 */
	List<Role> findRoleList(Integer userId);
	
	/**删除用户根据用户ID
	 * @param userId       用户ID
	 * @return
	 */
	int deleteByUserId(Integer userId);
	
	/**添加用户的角色
	 * @param userId       用户ID
	 * @param roleId       角色ID
	 * @return
	 */
	int addUserRole(@Param("userId")Integer userId,@Param("roleId")Integer roleId);
	
	/**删除角色的权限根据角色ID
	 * @param roleId       角色ID
	 * @return
	 */
	int deleteRoleFunctionByRoleId(Integer roleId);
	
	/**添加角色的权限
	 * @param roleId       角色ID
	 * @param funcId       权限ID
	 * @return
	 */
	int addRoleFunction(@Param("roleId")Integer roleId,@Param("funcId")Integer funcId);
	
	/**删除角色
	 * @param id       角色ID
	 * @return
	 */
	int deleteByRoleId(Integer id);
}