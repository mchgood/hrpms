package com.zhidisoft.marketingmanagement.entity;


import java.util.Date;




import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Email {
    public Integer id;
  
   
    public Integer userId;
    @NotBlank(message="必填信息")
   	@org.hibernate.validator.constraints.Email(message="邮箱格式不正确")
   	public String toAddr;
    @NotBlank(message="必填信息")
    public String subject;
    @NotBlank(message="必填信息")
    public String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date sendtime;

    public String status;
    
    public String sendName;
    
    

    
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

	public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr == null ? null : toAddr.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	@Override
	public String toString() {
		return "Email [id=" + id + ", userId=" + userId + ", toAddr=" + toAddr + ", subject=" + subject + ", content="
				+ content + ", sendtime=" + sendtime + ", status=" + status + ", sendName=" + sendName + "]";
	}

	
}