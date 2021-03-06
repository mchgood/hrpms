package com.zhidisoft.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 基础Dao
 * @author Lu Jianliang
 *
 * @param <PK> -实体主键属性类型
 * @param <E> -实体类型
 */
public interface IBaseDao<PK, E> {
	
	 /**
	 * ***************************************************
     *                                                   *
     * 以下方法由mybatis-generator生成的Mapper接口中提取，         *
     * 	对应的映射文件中已经生成该方法对应的SQL语句                            *
     *                                                   * 
     *****************************************************
     */
	
	/**
	 * 根据主键删除实体对象的数据
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(PK id);
   
    /**
     * 保存实体对象到数据库
     * @param entiy
     * @return
     */
    int insert(E entity);
   
    
    /**
     * 根据主键获取实体对象的信息
     * @param id
     * @return
     */
    E selectByPrimaryKey(PK id);
    
    /**
     * 获取所有实体对象
     * @return
     */
    List<E> selectAll();

    /**
     * 根据主键更新实体对象的信息
     * @param entity
     * @return
     */
    int updateByPrimaryKey(E entity);
    
    /**
   	 *************************************************************************
     *  以下方法为自定义方法，mybatis-generator在对应的映射文件中未生成对应的SQL语句，              *
     *  如需使用，则需要在继承当前接口的子接口对应的映射文件中定义对应的SQL语句，                    		 *
     *  如不需要使用，则不需要修改对应的映射文件                                                                         			 *
     *                                                                       *
     ************************************************************************* 
     */
    
    /**
     * 批量保存实体对象到数据库
     * @param list -待保存的数据集合
     * @return
     */
    int insertBatch(List<E> list);
    
    /**
     * 批量删除指定数据
     * @param list -待删除的实体对象
     */
    int deleteBatch(List<E> list);
    
    /**
     * 批量删除指定数据
     * @param list -待删除主键
     */
    int deleteBatchByPrimaryKey(List<PK> list);
    
    /**
     * 按条件分页查询结果数据
     * @param offset -起始索引
     * @param pageSize -每页记录数
     * @param condition -查询条件
     * @return
     */
    List<E> findByPage(@Param("offset")int offset, @Param("pageSize")int pageSize,@Param("condition")Map<String, Object>condition);
    
    /**
     * 按条件获取总记录数
     * @param condition -查询条件
     * @return
     */
    int count(@Param("condition")Map<String, Object> condition);
    

}
