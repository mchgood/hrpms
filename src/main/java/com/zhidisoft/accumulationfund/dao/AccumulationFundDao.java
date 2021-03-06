package com.zhidisoft.accumulationfund.dao;


import org.apache.ibatis.annotations.Param;

import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.base.IBaseDao;


public interface AccumulationFundDao  extends IBaseDao<Integer, AccumulationFund>{
	AccumulationFund findById(Integer id);
	AccumulationFund findByIdCard(String idcard);
	int updateByIdCard(@Param("idcard")String idcard,@Param("paydate")String paydate,@Param("paymoney")Double paymoney,@Param("proxyfee")Double proxyfee);
	int updateFundUser(AccumulationFund fund);
}