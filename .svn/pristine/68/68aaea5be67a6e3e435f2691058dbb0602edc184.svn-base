package com.zhidisoft.system.service;

import java.util.List;
import java.util.Map;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.result.TreeNode;
import com.zhidisoft.system.entity.Function;

public interface FunctionServiceIf extends IBaseService<Integer, Function>{
	/**查询所有权限，并且通过用户ID查询所有角色的权限
	 * @param roleId       角色ID
	 * @param id           用户ID
	 * @return
	 */
	List<TreeNode> findMenuByUserId(Integer roleId, Integer id);

    /**查询所有权限，并且通过角色ID查询所有角色的权限
     * @param roleId        角色ID
     * @param id            用户ID
     * @return
     */
    List<TreeNode> findMenuByRoleId(Integer roleId, Integer id);
    
    /**格式化分页后的数据
     * @param pageBean      分页实体类
     * @return
     */
    PageBean<Function> formataPage(PageBean<Function> pageBean);
    
    /**通过权限ID查询权限的信息
     * @param id        权限ID
     * @return
     */
    Map<String, Object> fingInfoById(Integer id);
    
    /**添加或者更新权限
     * @param func      权限实体类
     * @return
     */
    int addOrUpdateFunc(Function func);
    
    /**根据权限ID删除权限
     * @param id        权限ID
     * @return
     */
    int deleteFunc(Integer id);
}
