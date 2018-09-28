package com.zhidisoft.talentservice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidisoft.accumulationfund.entity.Customer;
import com.zhidisoft.accumulationfund.service.CustomerServiceIf;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.talentservice.entity.Person;
import com.zhidisoft.talentservice.service.TalentInformationListServiceIf;

@Controller
public class TalentInformationUserUpdate {
	
	@Resource
	CustomerServiceIf customerService;
	
	@Resource
	TalentInformationListServiceIf talentService;
	
	/**
	 * 删除
	 * */
	@RequestMapping("/TalentService/deleteTalentUser")
    @ResponseBody
	public JsonResult deleteUser(Integer id) {
	    int rows = talentService.delect(id);
	    if(rows>0) {
	        return JsonResult.buildSuccessResult("删除成功");
	    }else {
	        return JsonResult.buildFailResult("删除失败");
	    }
	}
	
	/**
	 * 修改要用到的数据
	 * 
	 * */
	@RequestMapping("/Talent/changTalentUser")
	public ModelAndView changeUser(Integer id) {

	    ModelAndView modelAndView = new ModelAndView();
	    if(id!=0) {
	        //通过用户ID获取用户的实体类对象
	    	Person person = talentService.findById(id);
	    	String idcard = person.getIdcard();
	    	Customer customer = customerService.findByIdCard(idcard);
	    	person.setName(customer.getName());
	    	modelAndView.addObject("map", person);
	    }
	    modelAndView.setViewName("TalentService/TalentInformationAdd");
	    return modelAndView;
	}
	/**
	 * 保存修改后的数据
	 * */
	
	@RequestMapping("/TalentService/updateTalent")
	@ResponseBody
	public JsonResult UpdateFundUser(Person person) {
		System.out.println(person.getIdcard());
	    int fRows = 0;
	    int cRows = 0;
	    if(person!=null) {
	    	 fRows = talentService.updateTalent(person);
	    	 cRows = customerService.updateCustomerUser(person.getName(),person.getIdcard());
	    }
	   if(fRows > 0&& cRows>0) {
		   return JsonResult.buildFailResult("修改成功");
	   }else {
		   return JsonResult.buildFailResult("修改失败");
	   }
	   
	}
}
