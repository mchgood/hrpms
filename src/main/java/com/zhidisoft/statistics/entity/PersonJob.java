package com.zhidisoft.statistics.entity;

import java.util.Date;

public class PersonJob {
    private Integer id;

    private String idcard;

    private Integer companyid;

    private String jobtype;

    private Double companyprice;
    
    private Double profit;

    private Double personprice;

    private String jobcontent;

    private Date starttime;

    private Long months;
    
    private Date endtime;

    private String contracturl;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;
    
    private Customer customer;
    
    private Company company;
    
    


	public Double getProfit() {
		return companyprice-personprice;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Long getMonths() {
		months= (endtime.getTime()-starttime.getTime())/(1000*60*60*24);
		return months;
	}

	public void setMonths(Long months) {
		this.months = months;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype == null ? null : jobtype.trim();
    }

    public Double getCompanyprice() {
        return companyprice;
    }

    public void setCompanyprice(Double companyprice) {
        this.companyprice = companyprice;
    }

    public Double getPersonprice() {
        return personprice;
    }

    public void setPersonprice(Double personprice) {
        this.personprice = personprice;
    }

    public String getJobcontent() {
        return jobcontent;
    }

    public void setJobcontent(String jobcontent) {
        this.jobcontent = jobcontent == null ? null : jobcontent.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getContracturl() {
        return contracturl;
    }

    public void setContracturl(String contracturl) {
        this.contracturl = contracturl == null ? null : contracturl.trim();
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

	@Override
	public String toString() {
		return "PersonJob [id=" + id + ", idcard=" + idcard + ", companyid=" + companyid + ", jobtype=" + jobtype
				+ ", companyprice=" + companyprice + ", profit=" + profit + ", personprice=" + personprice
				+ ", jobcontent=" + jobcontent + ", starttime=" + starttime + ", months=" + months + ", endtime="
				+ endtime + ", contracturl=" + contracturl + ", status=" + status + ", createby=" + createby
				+ ", createtime=" + createtime + ", updateby=" + updateby + ", updatetime=" + updatetime + ", remark="
				+ remark + ", customer=" + customer + ", company=" + company + "]";
	}
    
    
}