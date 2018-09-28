package com.zhidisoft.talentservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToEmailModelController {
	/**
	 * 跳转到邮件模板选择页面
	 * @return
	 */
	@RequestMapping("/toemailtemplate")
	public String chooseEmailTemplate(){
		
		return "market/emailtemplate";
	}
}
