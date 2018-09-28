package com.zhidisoft.system.entity;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable{
    private Integer id;

    private String funcname;

    private String funcurl;

    private String funcode;

    private Integer functype;

    private Integer parentid;

    private String iconclass;

    private String iconurl;

    private Integer sortnum;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String funcnote;
    
    private String parentName;
    
    private String statusLabel;
    
    

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

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

    public String getFuncname() {
        return funcname;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname == null ? null : funcname.trim();
    }

    public String getFuncurl() {
        return funcurl;
    }

    public void setFuncurl(String funcurl) {
        this.funcurl = funcurl == null ? null : funcurl.trim();
    }

    public String getFuncode() {
        return funcode;
    }

    public void setFuncode(String funcode) {
        this.funcode = funcode == null ? null : funcode.trim();
    }

    public Integer getFunctype() {
        return functype;
    }

    public void setFunctype(Integer functype) {
        this.functype = functype;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getIconclass() {
        return iconclass;
    }

    public void setIconclass(String iconclass) {
        this.iconclass = iconclass == null ? null : iconclass.trim();
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl == null ? null : iconurl.trim();
    }

    public Integer getSortnum() {
        return sortnum;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getFuncnote() {
        return funcnote;
    }

    public void setFuncnote(String funcnote) {
        this.funcnote = funcnote == null ? null : funcnote.trim();
    }
}