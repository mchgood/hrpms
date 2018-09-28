package com.zhidisoft.statistics.controller;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.statistics.entity.PersonJob;
import com.zhidisoft.statistics.service.PersonJobCountService;

@RequestMapping("/statistics")
@Controller
public class PersonJobCountController  {
	Map <String,Object> map=new HashMap<>();
	@Resource
	PersonJobCountService sevice;
	/**
	 * 人才外包统计分页查询
	 * @param page
	 * @param rows
	 * @param userName
	 * @param toAddr
	 * @return
	 */
	
	@RequestMapping("/personjobcountInfo")
	@ResponseBody
	public JsonResult personjobcountInfo(Integer page,Integer rows,String customerName,String idcard,String phone,String companyName){
		
		PageBean<PersonJob> perPageBean = new PageBean<PersonJob>();
		perPageBean.setPage(page);
		perPageBean.setRows(rows);
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("customerName", customerName);
		condition.put("idcard", idcard);
		condition.put("phone", phone);
		condition.put("companyName", companyName);
		perPageBean.setCondition(condition);
		
		PageBean<PersonJob> pageBean = sevice.findByPage(perPageBean);
		
		map.put("PersonJob",pageBean.getResult());
		return JsonResult.buildSuccessResult(pageBean);

	}
    @RequestMapping("/induce")
    @ResponseBody
	public JsonResult induce() throws Exception{
    	boolean success=false;
    	 // 创建一个EXCEL
      HSSFWorkbook wb = new HSSFWorkbook();
       // 创建一个SHEET
      HSSFSheet sheet = wb.createSheet("人才外包统计");
       String[] title = { "客户名称", "身份证号", "手机号码", "合作公司", "合作天数", "公司付款总额", "支付员工总额", "利润总额", "合作状态" };
        int i = 0;
        // 创建一行
        HSSFRow row1 = sheet.createRow(0);
     // 填充标题
      for (String s : title) {
      HSSFCell cell = row1.createCell(i);
     cell.setCellValue(s);
      i++;
     }
      // 下面是填充数据
      List<PersonJob> list = (List<PersonJob>) map.get("PersonJob");
      int x=1; 
      for(PersonJob personjob :list){
    	   	    
    	  HSSFRow row= sheet.createRow(x);
    	  
    	  row.createCell(0).setCellValue(personjob.getCustomer().getCustomerName());
    	  row.createCell(1).setCellValue(personjob.getIdcard());
    	  row.createCell(2).setCellValue(personjob.getCustomer().getPhone());
    	  row.createCell(3).setCellValue(personjob.getCompany().getCompanyName());
    	  row.createCell(4).setCellValue(personjob.getMonths());
    	  row.createCell(5).setCellValue(personjob.getCompanyprice());
    	  row.createCell(6).setCellValue(personjob.getPersonprice());
    	  row.createCell(7).setCellValue(personjob.getProfit());
    	  row.createCell(8).setCellValue(personjob.getStatus());
          x++;
      }
      
        
      FileOutputStream fileOut = new FileOutputStream("E:/jsp/PersonJob.xls");
      wb.write(fileOut);
      fileOut.close();
      //返回信息
      success=true;
      String message=null;
      if(success){
    	  message="导出成功";
      }else{
    	  message="导出失败";
      }
      
      return JsonResult.buildSuccessResult(message);
    }
    	

    	
    
    	
		
	
	//
  


}
