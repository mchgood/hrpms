package com.zhidisoft.accumulationfund.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.accumulationfund.dao.AccumulationFundDao;
import com.zhidisoft.accumulationfund.dao.CustomerDao;
import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.accumulationfund.entity.Customer;
import com.zhidisoft.accumulationfund.service.UploadServiceIf;
import com.zhidisoft.until.ExcelUtil;

@Service
public class UploadServiceImpl implements UploadServiceIf {
	
	@Resource
	AccumulationFundDao fundDao;
	
	@Resource
	CustomerDao customerDao;
	
	@Override
	public void uploadPayerCreditInfoExcel(InputStream in, MultipartFile upfile) throws Exception {
		List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, upfile.getOriginalFilename());
		List<AccumulationFund> fundList = new ArrayList<AccumulationFund>();
		List<Customer> customerList = new ArrayList<Customer>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> ob = listob.get(i);
			//设置客户表信息
			Customer customer = new Customer();
			customer.setIdcard(String.valueOf(ob.get(2)));
			customer.setName(String.valueOf(ob.get(1)));
			//设置公积金表信息
			AccumulationFund  accumulationFund = new AccumulationFund();
			accumulationFund.setId(Integer.getInteger(ob.get(0).toString()));
			accumulationFund.setIdcard(String.valueOf(ob.get(1)));//idcard
			accumulationFund.setAccountno(String.valueOf(ob.get(2)));
						
			accumulationFund.setPaydate(String.valueOf(ob.get(3)));
			
			accumulationFund.setPaymoney(Double.valueOf(ob.get(4).toString()));
			accumulationFund.setProxyfee(Double.valueOf(ob.get(5).toString()));
		/*	if(ob.get(6).equals("可用")) {
				accumulationFund.setStatus("0");
			}else {
				accumulationFund.setStatus("1");
			}
			// 省略其他属性的设置 ......
*/			fundList.add(accumulationFund);
			customerList.add(customer);
		}
		
		// 根据姓名和id，判断数据库中是否有重复记录，如果没有，则说明需要将excel中的记录保存到数据库
		
		//加入到数据库
		for (AccumulationFund fund : fundList) {
			AccumulationFund findByIdCard = fundDao.findByIdCard(fund.getIdcard());//判断数据库是否存在
			if(findByIdCard==null) {
				fundDao.insert(fund);
			}
			
		}
		//添加到客户表
		for (Customer customer : customerList) {
			Customer customerBean = customerDao.selectByIdCard(customer.getIdcard());//判断是否存在
			if(customerBean==null) {
				customerDao.insert(customer);
			}
		}
	}

}
