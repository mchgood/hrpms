package com.zhidisoft.talentservice.service;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.talentservice.entity.Person;

public interface TalentInformationListServiceIf extends IBaseService<Integer, Person> {
	PageBean<Person> talentPageBean (Integer page, Integer rows,String name,String idcard,String jobintension,String foraddress);
	int delect(Integer id);
	Person findById(Integer id);
	int updateTalent(Person person);
}
