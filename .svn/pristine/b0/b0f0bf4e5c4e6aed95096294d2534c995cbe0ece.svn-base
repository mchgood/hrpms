package com.zhidisoft.system.dao;

import com.zhidisoft.base.IBaseDao;
import com.zhidisoft.system.entity.Function;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FunctionDao extends IBaseDao<Integer, Function>{

	/**通过角色ID查询权限集合
	 * @param roleId       角色ID
	 * @return
	 */
	List<Function> findFunListByRoleId(Integer roleId);
	
	/**通过权限的父ID查询权限集合
	 * @param parentId     权限的父ID
	 * @return
	 */
	List<Function> findFunListByParentId(Integer parentId);
	
	/**查询权限集合
	 * @param userId       用户ID
	 * @param id           权限ID
	 * @return
	 */
	List<Function> findMenuList(@Param("userId")Integer userId,@Param("funcId")Integer id);
	
	/**查询用户拥有的所有权限
	 * @param id           用户ID
	 * @return
	 */
	List<Function> findAllMenuList(@Param("id")Integer id);
	
	/**查询所有的父级菜单
	 * 
	 * */
	List<Function> selectAllParentMenu();
	
	/**删除权限
	 * @param id       权限ID
	 * @return
	 */
	int deleteByFuncId(@Param("id")Integer id);
	
	/**删除权限相关的角色信息
	 * @param id       权限ID
	 * @return
	 */
	int deleteFuncAndRole(@Param("id")Integer id);
}