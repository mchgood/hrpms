package com.zhidisoft.system.service;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.system.entity.User;

public interface UserServiceIf extends IBaseService<Integer, User>{
    
	/**通过用户名查询用户
	 * @param user     user实体类
	 * @return         true用户名存在。false用户名不存在
	 */
	boolean findByName(User user);

    /**通过用户名更新用户信息
     * @param user      user实体类
     * @return
     */
    int updateByusername(User user);
    
    /**通过用户ID逻辑删除用户 
     * @param id        用户ID
     * @return
     */
    int deleteUserById(Integer id);
}
