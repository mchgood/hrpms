package com.zhidisoft.talentservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.talentservice.dao.CompanyDao;
import com.zhidisoft.talentservice.dao.Person_JobDao;
import com.zhidisoft.talentservice.entity.Company;
import com.zhidisoft.talentservice.entity.Person_Job;
import com.zhidisoft.talentservice.service.LaborCooperationListServiceIf;

@Service
public class LaborCooperationListServiceImpl extends BaseServiceImpl<Integer,Person_Job>  implements LaborCooperationListServiceIf  {

	@Resource
	CompanyDao companyDao;
	@Resource
	Person_JobDao personjobDao;
	
	@Override
	public PageBean<Person_Job> talentPageBean(Integer page, Integer rows,String name, String idcard,Integer companyid) {
		
		PageBean<Person_Job> talentPageBean = new PageBean<Person_Job>();
		talentPageBean.setPage(page);
		talentPageBean.setRows(rows);
		//查询条件
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("name", name);
		condition.put("idcard", idcard);
		condition.put("companyid", companyid);
		talentPageBean.setCondition(condition);
		return talentPageBean;
	}

	@Override
	public int delect(Integer id) {
		
		return personjobDao.updateById(id);
	}

	@Override
	public List<Company> selectName() {
	
		return  companyDao.selectName();
	}
	
}
