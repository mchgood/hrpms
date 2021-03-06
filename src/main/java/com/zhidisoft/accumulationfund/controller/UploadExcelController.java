package com.zhidisoft.accumulationfund.controller;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.accumulationfund.service.UploadServiceIf;

@Controller
public class UploadExcelController {
	
	@Resource
	UploadServiceIf uploadService;
	
	@RequestMapping("/import")
	@ResponseBody
	public void importExcel(MultipartFile upfile,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(!upfile.isEmpty()) {
			InputStream in = upfile.getInputStream();
			uploadService.uploadPayerCreditInfoExcel(in, upfile);
			in.close();	
		}
		//重定向到展示页面
		response.sendRedirect("company/accumulationFund");
	}

}
