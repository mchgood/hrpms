package com.zhidisoft.base;

import java.util.List;

import com.zhidisoft.result.PageBean;

public interface IBaseService<PK, E> {
	
	/**
	 * 保存单个实体信息
	 * @param entity
	 * @return
	 */
	boolean save(E entity);
	
	/**
	 * 批量保存实体信息
	 * @param list
	 * @return
	 */
	boolean save(List<E> list);
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	boolean deleteById(PK id);
	
	/**
	 * 根据id批量删除数据
	 * @param list
	 * @return
	 */
	boolean deleteById(List<PK> list);
	
	/**
	 * 更新实体信息
	 * @param entity
	 * @return
	 */
	boolean update(E entity);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 * @return
	 */
	E get(PK id);
	
	/**
	 * 获取所有实体信息
	 * @return
	 */
	List<E> findAll();
	
	/**
	 * 分页获取查询结果
	 * @param page
	 * @return
	 */
	PageBean<E> findByPage(PageBean<E> page);
}
