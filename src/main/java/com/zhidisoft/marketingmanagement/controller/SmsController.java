package com.zhidisoft.marketingmanagement.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.zhidisoft.marketingmanagement.entity.Sms;
import com.zhidisoft.marketingmanagement.entity.SmsTemplate;
import com.zhidisoft.marketingmanagement.service.SmsService;
import com.zhidisoft.marketingmanagement.service.SmsTemplateService;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.User;
@Controller
@RequestMapping("/market-sms")
public class SmsController {
	
	@Resource
	SmsService smsService;
	@Resource
	SmsTemplateService smstemplate;
	/**
	 * 短信分页
	 * @param page
	 * @param rows
	 * @param sendName
	 * @param toAddr
	 * @return
	 */
	@RequestMapping("/messageInfo")
	@ResponseBody
	public JsonResult messageInfo(Integer page,Integer rows,String sendName,String telephone){
		PageBean<Sms> messagePageBean = new PageBean<Sms>();
		messagePageBean.setPage(page);
		messagePageBean.setRows(rows);
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("sendName", sendName);
		condition.put("telephone", telephone);
		messagePageBean.setCondition(condition);
		
		PageBean<Sms> pageBean = smsService.findByPage(messagePageBean);
		return JsonResult.buildSuccessResult(pageBean);

	}

	/**
	 * 短信详情
	 * @return
	 */
	@RequestMapping("/smsDetails")
	public String smsDetails(Integer id,String sendName,Model model){
		String name=null;
		try {
			name = new String(sendName.getBytes("ISO-8859-1"),"UTF-8");
			
			Sms sms=smsService.get(id);
			sms.setSendName(name);
			model.addAttribute("sms", sms);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "market/smsDetails";
    }
	
	/**
	 * 删除短信
	 */
	@RequestMapping("/smsdelete")
	@ResponseBody
	public JsonResult smsDelete(Integer id){
		
		boolean success = smsService.deleteById(id);
		String message=null;
		if(success){
			message="删除成功";
		}else{
			message="删除失败";
		}
	return JsonResult.buildSuccessResult(message);
	}


	/**
	 * 发送邮件
	 */
	@RequestMapping("/sendsms")
	public String sendSms(@Valid Sms sms,Errors errors,Model model,HttpServletRequest re){
		User user = (User) re.getSession().getAttribute("root");
		sms.setUserId(user.getId());
		
		
		if(errors.hasErrors()){
			return "market/sendsms";
		}
		
		boolean success = smsService.save(sms);
		
		String message=null;
		if(success){
			message="发送成功";
		}else{
			message="发送失败";
		}
		model.addAttribute("message", message);
		return "market/success";
		
	}
	
	/**
	 * 跳转到短信模板选择页面
	 * @return
	 */
	@RequestMapping("/tosmstemplate")
	public String chooseEmailTemplate(){
		
		return "market/smstemplate";
	}
	
	/**
	 * 短信模板分页
	 * @param page
	 * @param rows
	 * @param sendName
	 * @param toAddr
	 * @return
	 */
	@RequestMapping("/smstemplate")
	@ResponseBody
	public JsonResult smsTemplate(Integer page,Integer rows){
		
		PageBean<SmsTemplate> smsTemplatePageBean = new PageBean<>();
		smsTemplatePageBean.setPage(page);
		smsTemplatePageBean.setRows(rows);
		
		
		PageBean<SmsTemplate> pageBean = smstemplate.findByPage(smsTemplatePageBean);
		return JsonResult.buildSuccessResult(pageBean);

	}
	
	/**
	 * 跳转到短信营销页面
	 * @return
	 */
	@RequestMapping("/company/emailRecord/form")
	public String toSmsRecordForm(Integer orderId,Model model){
		
		SmsTemplate smsTemplate = smstemplate.get(orderId);
		
		model.addAttribute("smsTemplate", smsTemplate);
		return "market/sendsms";
	}
}
