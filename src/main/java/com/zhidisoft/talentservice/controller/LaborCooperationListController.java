package com.zhidisoft.talentservice.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.talentservice.entity.Company;
import com.zhidisoft.talentservice.entity.Person_Job;
import com.zhidisoft.talentservice.service.LaborCooperationListServiceIf;

@Controller
public class LaborCooperationListController {
	
	@Resource
	LaborCooperationListServiceIf laborService;
	
	@ResponseBody
	@RequestMapping("/LaborCooperationList")
	public JsonResult getLaborCooperationList(Integer page, Integer rows,String name, String idcard,Integer companyid,HttpSession session) {
		System.out.println(companyid);

		PageBean<Person_Job> talentPageBean = laborService.talentPageBean(page, rows, name, idcard, companyid);
		
		PageBean<Person_Job> findByPage = laborService.findByPage(talentPageBean);
			
		//查询数据字典，所有公司的名称
		List<Company> companyList = laborService.selectName();
		session.setAttribute("companyList", companyList);
		
		return JsonResult.buildSuccessResult(findByPage);
	}
}
