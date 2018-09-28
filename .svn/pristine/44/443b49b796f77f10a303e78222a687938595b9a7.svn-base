package com.zhidisoft.accumulationfund.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.accumulationfund.service.AccumulationFundServiceIf;
import com.zhidisoft.result.JsonResult;

@Controller
public class ProvidentFundPaymentController {
	
	@Resource
	AccumulationFundServiceIf fundService;
	
	@RequestMapping("/FundPayment")
	@ResponseBody
	public JsonResult fundPayment(AccumulationFund accumulationFund) {
			
		int rows = fundService.insert(accumulationFund);
		if(rows>0) {
			return JsonResult.buildSuccessResult("缴费成功");
		}else if(rows==-4){
			return JsonResult.buildFailResult("不是本公司用户，缴费失败");
		}else {
			return JsonResult.buildFailResult("缴费失败");
		}
		
	}
}
