package com.zhidisoft.accumulationfund.service;


import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.PageBean;

public interface AccumulationFundServiceIf extends IBaseService<Integer,AccumulationFund> {
	PageBean<AccumulationFund> accumulationFundPageBean(Integer page, Integer rows,String idcard,String accountno);
	int deleteByPrimaryKey(Integer id);
	AccumulationFund findById(Integer id);
	int insert(AccumulationFund accumulationFund);
	int updateFundUser(AccumulationFund accumulationFund);
}