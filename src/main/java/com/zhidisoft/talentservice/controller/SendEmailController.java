package com.zhidisoft.talentservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;

@Controller
public class SendEmailController {

	 @Autowired
	//在spring中配置的邮件发送的bean
	 private JavaMailSender javaMailSender;
	 
	@RequestMapping("/SendEmail")
	@ResponseBody
	public JsonResult sendEmail(String username,String toAddr,String content,String subject) {
		
		System.out.println(toAddr);
		MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
		String from;
        try {
			prop.load(this.getClass().getResourceAsStream("/mail.properties"));
			from = prop.get("mail.smtp.username")+"";
			mMessageHelper=new MimeMessageHelper(createMimeMessage,true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo(toAddr);//收件人邮箱
            mMessageHelper.setSubject(subject);//邮件的主题
            mMessageHelper.setText(content,true);//邮件的文本内容，true表示文本以html格式打开
           /* File file=new File("E:/img/枫叶.png");//在邮件中添加一张图片
            FileSystemResource resource=new FileSystemResource(file);
            
			mMessageHelper.addInline("fengye", resource);
			//这里指定一个id,在上面引用
			mMessageHelper.addAttachment("枫叶.png", resource);*/
			//在邮件中添加一个附件
           javaMailSender.send(createMimeMessage);//发送邮件
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		return JsonResult.buildFailResult("发送成功");
	}
}
