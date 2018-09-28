package com.zhidisoft.accumulationfund.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AccumulationFund implements Serializable {
    private Integer id;

    private String idcard;

    private String accountno;

    private String paydate;

    private Double paymoney;

    private Double proxyfee;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;
    
    private String name;


	private Customer customer;
    
    
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate == null ? null : paydate.trim();
    }

    public Double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Double paymoney) {
        this.paymoney = paymoney;
    }

    public Double getProxyfee() {
        return proxyfee;
    }

    public void setProxyfee(Double proxyfee) {
        this.proxyfee = proxyfee;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getName() {
  		return name;
  	}

  	public void setName(String name) {
  		this.name = name;
  	}

	public AccumulationFund() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccumulationFund(Integer id, String idcard, String accountno, String paydate, Double paymoney,
			Double proxyfee, String status, Integer createby, Date createtime, Integer updateby, Date updatetime,
			String remark, String name, Customer customer) {
		super();
		this.id = id;
		this.idcard = idcard;
		this.accountno = accountno;
		this.paydate = paydate;
		this.paymoney = paymoney;
		this.proxyfee = proxyfee;
		this.status = status;
		this.createby = createby;
		this.createtime = createtime;
		this.updateby = updateby;
		this.updatetime = updatetime;
		this.remark = remark;
		this.name = name;
		this.customer = customer;
	}
	
    
    
}