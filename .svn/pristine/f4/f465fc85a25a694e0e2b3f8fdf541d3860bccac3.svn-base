package com.zhidisoft.accumulationfund.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.accumulationfund.dao.CustomerDao;
import com.zhidisoft.accumulationfund.entity.Customer;
import com.zhidisoft.accumulationfund.service.CustomerServiceIf;
import com.zhidisoft.base.BaseServiceImpl;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Integer, Customer> implements CustomerServiceIf {

	@Resource
	CustomerDao customerDao;
	
	@Override
	public Customer findByIdCard(String idcard) {
		return customerDao.selectByIdCard(idcard);
	}

	@Override
	public int updateCustomerUser(String name, String idCard) {
		
		return customerDao.updateCustomerUser(name, idCard);
	}

}
