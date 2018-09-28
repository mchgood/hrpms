package com.zhidisoft.talentservice.service;

import java.util.List;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.talentservice.entity.Company;
import com.zhidisoft.talentservice.entity.Person_Job;

public interface LaborCooperationListServiceIf extends IBaseService<Integer, Person_Job> {
	PageBean<Person_Job> talentPageBean(Integer page, Integer rows,String name, String idcard,Integer companyid) ;
	int delect(Integer id);
	List<Company> selectName();
}
