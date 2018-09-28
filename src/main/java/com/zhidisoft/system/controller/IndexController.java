package com.zhidisoft.system.controller;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhidisoft.base.BaseController;
import com.zhidisoft.marketingmanagement.entity.EmailTemplate;
import com.zhidisoft.system.entity.User;

/**  
 * index界面用于控制跳转
* @author tkc  
* @date 2018年9月15日  
*/ 
@Controller
public class IndexController extends BaseController{
	/**跳转到登录页面
	 * 
	 */
    @RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/toIndex")
    public String toIndex() {
        return "index";
    }
	
	/**跳转到用户管理页面
	 * 
	 */
	@RequiresPermissions("company/system/userInfo")
	@RequestMapping("/company/system/userInfo")
	public String touserInfo() {
		return "sys/userList";
	}

    /**跳转到角色管理界面
     * 
     */
	@RequiresPermissions("company/system/systemRole")
    @RequestMapping("/company/system/systemRole")
    public String toroleInfo() {
        return "sys/roleList";
    }
    
    /**跳转到菜单管理
     * 
     */
	@RequiresPermissions("company/system/test")
    @RequestMapping("/company/system/test")
    public String toFunctionInfo() {
        return "sys/funcList";
    }
    
    /**跳转到短信管理
     * 
     */
	@RequiresPermissions("company/system/smsTemplate")
    @RequestMapping("/company/system/smsTemplate")
    public String toSMSInfo() {
        return "sys/smsList";
    }
    
    /**跳转到邮件模板
     * @return
     */
	@RequiresPermissions("company/system/emailTemplate")
    @RequestMapping("/company/system/emailTemplate")
    public String toEmailInfo() {
        return "sys/emailList";
    }
    
    /**跳转到数据字典页面
     * @return
     */
	@RequiresPermissions("company/system/userSearch")
    @RequestMapping("/company/system/userSearch")
    public String toDict() {
        return "sys/dictList";
    }

	/**
	 * 跳到公积金展示页面
	 * */
	@RequestMapping("/company/accumulationFund")
	public String toAccumulationFund() {
		return "AccumulationFund/ProvidentFundInquiry";
	}

	/**
	 * 跳到公积金缴费页面
	 * */
	@RequestMapping("/company/accumulationFund/form")
	public String toAccumulationFundPayment() {
		return "AccumulationFund/ProvidentFundPayment";
	}
	
	/**
	 * 跳转到营销查询页面
	 * @return
	 */
	@RequestMapping("/company/emailRecord")
	public String toEmailRecord(){
		
		return "market/sendlist";
	}
	/**
	 * 跳转到邮件营销页面
	 * @return
	 */
	@RequestMapping("/company/emailRecord/form")
	public String toEmailRecordForm(){
		
		return "market/sendemail";
	}
	/**
	 * 跳转到短信营销页面
	 * @return
	 */
	@RequestMapping("/company/smsRecord/form")
	public String toSmsRecordForm(){
		

		return "market/sendsms";
	}
	/**
	 * 跳转到人才外包统计页面
	 */
	@RequestMapping("/company/personJobCount")
	public String toPersonJobCount(){
		
		return "statistics/personjobcount";
	}
	
	/**
	 * 跳转到人才外包统计页面
	 */
	@RequestMapping("/company/news")
	public String toNews(){
		
		return "content/newslist";
	}
	
	/**
	 * 跳转到公积金催缴页面
	 * */
	/*
	
	@RequestMapping("/company/providentfundcall/form")
	public String toAccumulationFundCall() {
		return "AccumulationFund/ProvidentFundCall";
	}*/
	
	/**
	 * 跳转到人才信息列表页面
	 * */
	
	@RequestMapping("/company/personInfo")
	public String toTalentInformationList() {
		return "TalentService/TalentInformation";
	}
	
	
	/**
	 * 跳转到人才信息添加页面
	 * */
	
	@RequestMapping("/addTalentInformation")
	public String toTalentInformationAdd() {
		return "TalentService/TalentInformationAdd";
	}
	
	/**
	 * 跳转到发送邮件的模板
	 * */
	
	@RequestMapping("company/providentfundcall/form")
	public String toSendEmail() {
		return "Email/EmailSend";
	}
	
	/**
	 * 跳转到劳务合作列表的模板
	 * */
	@RequestMapping("/company/labor")
	public String toLaborCooperation() {
		return "TalentService/LaborCooperation";
	}
	/**
	 * 跳转到劳务合作列表的模板
	 * */
	@RequestMapping("/LaborCooperationAdd")
	public String toLaborCooperationAdd() {
		return "TalentService/LaborCooperationAdd";
	}
}
