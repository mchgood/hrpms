package com.zhidisoft.accumulationfund.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.accumulationfund.entity.Customer;
import com.zhidisoft.accumulationfund.service.AccumulationFundServiceIf;
import com.zhidisoft.accumulationfund.service.CustomerServiceIf;
import com.zhidisoft.result.JsonResult;

@Controller
public class ProvidentFundUserController {
	
	@Resource
	CustomerServiceIf customerService;
	
	@Resource
	AccumulationFundServiceIf fundService;
	
	/**
	 * 删除
	 * */
	@RequestMapping("/ProvidentFund/deleteFundUser")
    @ResponseBody
	public JsonResult deleteUser(Integer id) {
	    int rows = fundService.deleteByPrimaryKey(id);
	    if(rows>0) {
	        return JsonResult.buildSuccessResult();
	    }else {
	        return JsonResult.buildFailResult("删除失败");
	    }
	}
	
	/**
	 * 修改要用到的数据
	 * */
	
	
	@RequestMapping("/ProvidentFund/changeFundUser")
	public ModelAndView changeUser(Integer id) {

	    ModelAndView modelAndView = new ModelAndView();
	    if(id!=0) {
	        //通过用户ID获取用户的实体类对象
	    	AccumulationFund accumulationFund = fundService.findById(id);
	    	String idcard = accumulationFund.getIdcard();
	    	Customer customer = customerService.findByIdCard(idcard);
	    	accumulationFund.setName(customer.getName());
	    	modelAndView.addObject("map", accumulationFund);
	    }
	    modelAndView.setViewName("AccumulationFund/changeFundUser");
	    return modelAndView;
	}
	
	/**
	 * 保存修改后的数据
	 * */
	
	@RequestMapping("/ProvidentFund/updateFundUser")
	@ResponseBody
	public JsonResult UpdateFundUser(AccumulationFund accumulationFund) {
	    int fRows = 0;
	    int cRows = 0;
	    if(accumulationFund!=null) {
	    	 fRows = fundService.updateFundUser(accumulationFund);
	    	 cRows = customerService.updateCustomerUser(accumulationFund.getName(),accumulationFund.getIdcard());
	    }
	   if(fRows > 0 && cRows>0) {
		   return JsonResult.buildSuccessResult("修改成功");
	   }else {
		   return JsonResult.buildFailResult("修改失败");
	   }
	   
	}
}
