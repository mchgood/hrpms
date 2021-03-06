package com.zhidisoft.talentservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.talentservice.dao.PersonDao;
import com.zhidisoft.talentservice.entity.Person;
import com.zhidisoft.talentservice.service.TalentInformationListServiceIf;

@Service
public class TalentInformationServiceImpl extends BaseServiceImpl<Integer,Person> implements TalentInformationListServiceIf {

	@Resource
	PersonDao personDao;
	
	@Override
	public PageBean<Person> talentPageBean(Integer page, Integer rows,String name, String idcard, String jobintension, String foraddress) {
		
		PageBean<Person> talentPageBean = new PageBean<Person>();
		talentPageBean.setPage(page);
		talentPageBean.setRows(rows);
		//查询条件
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("name", name);
		condition.put("idcard", idcard);
		condition.put("jobintension", jobintension);
		condition.put("foraddress", foraddress);
		talentPageBean.setCondition(condition);
		return talentPageBean;
	}
	
	//删除
	@Override
	public int delect(Integer id) {
		int rows = personDao.deleteByPrimaryKey(id);
		return rows;
	}

	@Override
	public Person findById(Integer id) {
		Person person = personDao.selectByPrimaryKey(id);
		return person;
	}
	
	
	/**
	 * 保存修改后的信息
	 * */
	@Override
	public int updateTalent(Person person) {
		return personDao.updateTalent(person);
	}
}
