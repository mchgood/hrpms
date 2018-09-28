package com.zhidisoft.accumulationfund.dao;

import com.zhidisoft.accumulationfund.entity.Customer;
import com.zhidisoft.base.IBaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomerDao extends IBaseDao<Integer, Customer>{
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectAll();
    
    Customer selectByIdCard(String idcard);

    int updateByPrimaryKey(Customer record);
    
    int updateCustomerUser(@Param("name")String name,@Param("idcard")String idCard);
}