package com.zhidisoft.talentservice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.talentservice.entity.Person;
import com.zhidisoft.talentservice.service.TalentInformationListServiceIf;

@Controller
public class TalentInformstionController {
	
	@Resource
	TalentInformationListServiceIf talentService;
	
	@RequestMapping("/TalentInformationList")
	@ResponseBody
	public JsonResult getTalentInformationList(Integer page, Integer rows,String name, String idcard, String jobintension, String foraddress) {
		/*String name, String idcard, String jobintension, String foraddress*/
		System.out.println(name+"1234");
		//获得pageBean对象
		PageBean<Person> pageBean = talentService.talentPageBean(page, rows, name,idcard, jobintension, foraddress);
		
		//调用分页查询的方法
		PageBean<Person> findByPage = talentService.findByPage(pageBean);
		
		return JsonResult.buildSuccessResult(findByPage);
	}
}
