package com.zhidisoft.accumulationfund.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.accumulationfund.service.AccumulationFundServiceIf;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;


@Controller
public class ProvidentFundController{
	
	@Resource
	AccumulationFundServiceIf accumulationFundService;
	
	@RequestMapping("/ProvidentFund")
	@ResponseBody
	public JsonResult getProvidentFundList(Integer page,Integer rows,String idcard,String accountno,HttpSession session){
		
		
		PageBean<AccumulationFund> Bean = accumulationFundService.accumulationFundPageBean(page, rows, idcard, accountno);
		
		PageBean<AccumulationFund> findByPage = accumulationFundService.findByPage(Bean);
		
		session.setAttribute("findByPage", findByPage);
		
		return JsonResult.buildSuccessResult(findByPage);
	} 
}
