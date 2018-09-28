package com.zhidisoft.system.entity;

import java.util.Date;

public class EmailTemplate {
    private Integer id;

    private String subject;

    private String content;

    private Integer orderId;

    private Date createtime;

    private Integer createby;

    private Date updatetime;

    private Integer updateby;
    
    private String statusLabel;
    
    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }
}