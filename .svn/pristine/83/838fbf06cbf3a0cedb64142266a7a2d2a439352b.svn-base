package com.zhidisoft.talentservice.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.talentservice.entity.Company;
import com.zhidisoft.talentservice.service.LaborCooperationListServiceIf;

@Controller
public class LaborCooperationChangController {
	
	@Resource
	LaborCooperationListServiceIf laborService;
	@RequestMapping("LaborCooperationList/delete")
	@ResponseBody
	public JsonResult deleteLaborCooperation(Integer id) {
		//根据id删除
		int rows = laborService.delect(id);
		
		if(rows>0) {
			return JsonResult.buildSuccessResult("删除成功");
		}else {
			return JsonResult.buildFailResult("删除失败");
		}
		
	}
}
