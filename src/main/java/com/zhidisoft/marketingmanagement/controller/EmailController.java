package com.zhidisoft.marketingmanagement.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.base.BaseController;
import com.zhidisoft.marketingmanagement.entity.Email;
import com.zhidisoft.marketingmanagement.entity.EmailTemplate;
import com.zhidisoft.marketingmanagement.entity.Sms;
import com.zhidisoft.marketingmanagement.service.EmailService;
import com.zhidisoft.marketingmanagement.service.EmailTemplateService;
import com.zhidisoft.marketingmanagement.service.SmsService;
import com.zhidisoft.marketingmanagement.service.imp.EmailServiceImp;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.User;


@Controller
@RequestMapping("/market-email")
public class EmailController {
	@Resource
	EmailService emailService;
	
	@Resource
	EmailTemplateService emailtemplate;
	/**
	 * 邮件分页查询
	 * @param page
	 * @param rows
	 * @param userName
	 * @param toAddr
	 * @return
	 */
	
	@RequestMapping("/emailInfo")
	@ResponseBody
	public JsonResult emailInfo(Integer page,Integer rows,String sendName,String toAddr){
		PageBean<Email> emailPageBean = new PageBean<Email>();
		emailPageBean.setPage(page);
		emailPageBean.setRows(rows);
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("sendName", sendName);
		condition.put("toAddr", toAddr);
		emailPageBean.setCondition(condition);
		
		PageBean<Email> pageBean = emailService.findByPage(emailPageBean);
		return JsonResult.buildSuccessResult(pageBean);

	}

	
	/**
	 * 删除邮件
	 */
	@RequestMapping("/emaildelete")
	@ResponseBody
	public JsonResult emailDelete(Integer id){
		
		boolean success = emailService.deleteById(id);
		String message=null;
		if(success){
			message="删除成功";
		}else{
			message="删除失败";
		}
	return JsonResult.buildSuccessResult(message);
	}
	

	/**
	 * 邮件详情
	 */
	@RequestMapping("/emailDetails")
	public String emailEdit(Integer id,String sendName,Model model){
		String name=null;
		try {
			name = new String(sendName.getBytes("ISO-8859-1"),"UTF-8");
			
			Email email=emailService.get(id);
			email.setSendName(name);
			model.addAttribute("email", email);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "market/emailDetails";
    }
	
	
	
	/**
	 * 发送邮件
	 * @throws Exception 
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/sendemail")
	public String sendEmail(@Valid Email email,Errors errors,Model model,HttpServletRequest re) throws Exception {
		User user =  (User) re.getSession().getAttribute("root");
		
		email.setUserId(user.getId());
		boolean flag=false;
		
		if(errors.hasErrors()){
			return "market/sendemail";
		}
		//发送邮件
		 // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
        // 此处填写你的账号
        props.put("mail.user",user.getEmail());
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", "mgxbmeokokbdbfcc");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(email.getToAddr());
        message.setRecipient(RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(email.getSubject());

        // 设置邮件的内容体
        message.setContent(email.getContent(), "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
        flag=true;
		

	      //判断发送是否成功
	      String mess=null;
		if(flag){
			email.setStatus("1");
			mess="发送成功";
		}else{
			email.setStatus("-1");	
			mess="发送失败";
		}
		 emailService.save(email);
		
		
	
		model.addAttribute("message", mess);
		return "market/success";
		
	}
	/**
	 * 跳转到邮件模板选择页面
	 * @return
	 */
	@RequestMapping("/toemailtemplate")
	public String chooseEmailTemplate(){
		
		return "market/emailtemplate";
	}
	/**
	 * 邮件模板分页
	 * @param page
	 * @param rows
	 * @param sendName
	 * @param toAddr
	 * @return
	 */
	@RequestMapping("/emailtemplate")
	@ResponseBody
	public JsonResult emailTemplate(Integer page,Integer rows){
		
		PageBean<EmailTemplate> EmailTemplatePageBean = new PageBean<>();
		EmailTemplatePageBean.setPage(page);
		EmailTemplatePageBean.setRows(rows);
	
		
		PageBean<EmailTemplate> pageBean = emailtemplate.findByPage(EmailTemplatePageBean);
		return JsonResult.buildSuccessResult(pageBean);

	}
	/**
	 * 跳转到邮件营销页面
	 * @return
	 */
	@RequestMapping("/company/emailRecord/form")
	public String toEmailRecordForm(Integer orderId,Model model){
		
		EmailTemplate emailTemplate = emailtemplate.get(orderId);
		
		model.addAttribute("emailTemplate", emailTemplate);
		return "market/sendemail";
	}
	
}