package com.zhidisoft.system.dao;

import com.zhidisoft.base.IBaseDao;
import com.zhidisoft.system.entity.User;
import java.util.List;

public interface UserDao extends IBaseDao<Integer, User>{
    
	/**通过用户名查询User实体类
	 * @param username     用户名
	 * @return
	 */
	User findByName(String username);
	
	/**通过用户名查询User实体类忽略状态
	 * @param username     用户名
	 * @return
	 */
	User findByNameIgnoreStatus(String username);
	
	/**更新用户信息
	 * @param user     用户实体类
	 * @return
	 */
	int updateByusername(User user);
	
	/**逻辑删除用户
	 * @param id       用户ID
	 * @return
	 */
	int deleteUserById(Integer id);
}