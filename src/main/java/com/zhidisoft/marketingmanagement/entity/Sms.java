package com.zhidisoft.marketingmanagement.entity;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Sms {
    private Integer id;

    private Integer userId;
    @Pattern(regexp= "^(\\+\\d{2}-)?(\\d{2,3}-)?([1][3,4,5,7,8][0-9]\\d{8})$",message="请输入正确的电话号码")
    private String telephone;

    private Date sendtime;
    @NotBlank(message="必填内容")
    private String content;
    
    private String sendName;
    
    

    
   	public String getSendName() {
   		return sendName;
   	}

   	public void setSendName(String sendName) {
   		this.sendName = sendName;
   	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}