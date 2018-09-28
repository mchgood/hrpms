package com.zhidisoft.talentservice.dao;

import com.zhidisoft.base.IBaseDao;
import com.zhidisoft.talentservice.entity.Company;

import java.util.List;

public interface CompanyDao extends IBaseDao<Integer, Company>{
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    Company selectByPrimaryKey(Integer id);

    List<Company> selectAll();

    int updateByPrimaryKey(Company record);
    
    List<Company> selectName();
}