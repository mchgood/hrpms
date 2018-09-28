package com.zhidisoft.accumulationfund.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.accumulationfund.dao.AccumulationFundDao;
import com.zhidisoft.accumulationfund.dao.CustomerDao;
import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.accumulationfund.entity.Customer;
import com.zhidisoft.accumulationfund.service.AccumulationFundServiceIf;
import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.PageBean;

@Service
public class AccumulationFundServiceImpl extends BaseServiceImpl<Integer, AccumulationFund> implements AccumulationFundServiceIf{

	@Resource
	CustomerDao customerDao;
	
	@Resource
	AccumulationFundDao fundDao;
	
	@Override
	public PageBean<AccumulationFund> accumulationFundPageBean(Integer page, Integer rows,String idcard,String accountno) {
		
		PageBean<AccumulationFund> pageBean = new PageBean<AccumulationFund>();
		pageBean.setPage(page);
		pageBean.setRows(rows);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("idcard", idcard);
		condition.put("accountno", accountno);
		pageBean.setCondition(condition);
		return pageBean;
	}

	/**
	 * 删除
	 * */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return fundDao.deleteByPrimaryKey(id);
	}

	/**
	 * 修改
	 * */
	@Override
	public AccumulationFund findById(Integer id) {
		
		return fundDao.findById(id);
	}
	
	/**
	 * 添加
	 * */
	@Override
	public int insert(AccumulationFund accumulationFund) {
		//根据idcard判断是否存在
		String idcard = accumulationFund.getIdcard();
		//判断该用户是否是公司客户
		Customer oldCustomer = customerDao.selectByIdCard(idcard);
		if(oldCustomer!=null&&oldCustomer.equals(" ")) {//是公司客户
			AccumulationFund fundBean = fundDao.findByIdCard(idcard);
			int cRows = 0;
			int fRows =0;
			int upDateFRows=0;
			if(fundBean==null) {//第一次缴费
				Customer customer = new Customer();
				customer.setName(accumulationFund.getName());
				customer.setIdcard(accumulationFund.getIdcard());
				cRows = customerDao.insert(customer);
				fRows = fundDao.insert(accumulationFund);
			}else {//该用户不是第一次缴费，在原来的金额上加上缴费的金额
				Double paymoney = fundBean.getPaymoney();
				Double proxyfee = fundBean.getProxyfee();
				Double newPaymoney = accumulationFund.getPaymoney();
				Double newProxyfee = accumulationFund.getProxyfee();
				String paydate = accumulationFund.getPaydate();
				upDateFRows = fundDao.updateByIdCard(idcard, paydate, newPaymoney+paymoney, newProxyfee+proxyfee);
				if(upDateFRows>0) {
					return 2;
				}else {//失败
					return -2;
				}
				
			}
			
			 if(cRows > 0 && fRows > 0 && upDateFRows > 0) {
				 return 1;
			 }else {//失败
				 return -3;
			 }
		}else {//不是公司客户
			return -4;
		}
		
	}

	@Override
	public int updateFundUser(AccumulationFund accumulationFund) {
		
		return fundDao.updateFundUser(accumulationFund);
	}
}
