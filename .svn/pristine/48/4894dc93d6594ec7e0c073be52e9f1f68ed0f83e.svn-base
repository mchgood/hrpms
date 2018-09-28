package com.zhidisoft.accumulationfund.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidisoft.accumulationfund.dao.AccumulationFundDao;
import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.accumulationfund.entity.ExcelBean;
import com.zhidisoft.accumulationfund.service.ExportServiceIf;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.until.ExcelUtil;

@Service
public class ExportServiceImpl2{
	
	@Autowired  
	private HttpSession session;  
	
	@Resource
	AccumulationFundDao fundDao;

	public  XSSFWorkbook exportExcelInfo(List<ExcelBean> headerList,PageBean<AccumulationFund> pageBean) 
			throws InvocationTargetException, 
			ClassNotFoundException, IntrospectionException, 
			ParseException, IllegalAccessException {
	    //根据条件查询数据，把数据装载到一个list中
		//PageBean<AccumulationFund> pageBean = (PageBean<AccumulationFund>)session.getAttribute("findByPage");
		List<AccumulationFund> list = null;
		if(pageBean!=null) {	
			list = pageBean.getResult();
		}
		//List<AccumulationFund> list = fundDao.selectAll();
		 XSSFWorkbook xssfWorkbook=null;
		 Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);  
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "用户信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    
	    try {
			xssfWorkbook = ExcelUtil.createExcelFile(AccumulationFund.class, list, map, sheetName);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	    
	    return xssfWorkbook;
	}
	
	/**
	 * 下载
	 * */
	public XSSFWorkbook selectByCondition() throws InvocationTargetException, ClassNotFoundException, IllegalAccessException, IntrospectionException, ParseException {
		
		 List<ExcelBean> headerList = new ArrayList<>();	
	    //设置  标题栏
	    headerList.add(new ExcelBean("序号","id",0));
	    headerList.add(new ExcelBean("姓名","name",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("公积金号","accountno",0));
	    headerList.add(new ExcelBean("缴费时间","paydate",0));
	    headerList.add(new ExcelBean("缴费金额","paymoney",0));
	    headerList.add(new ExcelBean("代收费用","proxyfee",0));
	    PageBean<AccumulationFund> pageBean = (PageBean<AccumulationFund>)session.getAttribute("findByPage");   
	    return  exportExcelInfo(headerList,pageBean);
	}
	
	/**
	 * 下载模板
	 * @throws ParseException 
	 * @throws IntrospectionException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * */
	public XSSFWorkbook model() throws InvocationTargetException, ClassNotFoundException, IllegalAccessException, IntrospectionException, ParseException {
		List<ExcelBean> headerList = new ArrayList<>();	
	    //设置  标题栏
	    headerList.add(new ExcelBean("序号"," ",0));
	    headerList.add(new ExcelBean("姓名"," ",0));
	    headerList.add(new ExcelBean("身份证号"," ",0));
	    headerList.add(new ExcelBean("公积金号"," ",0));
	    headerList.add(new ExcelBean("缴费时间"," ",0));
	    headerList.add(new ExcelBean("缴费金额"," ",0));
	    headerList.add(new ExcelBean("代收费用"," ",0));
	    PageBean<AccumulationFund> pageBean = null;  
	    return  exportExcelInfo(headerList,pageBean);
	}
}
