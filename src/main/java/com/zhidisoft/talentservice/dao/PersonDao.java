package com.zhidisoft.talentservice.dao;

import com.zhidisoft.base.IBaseDao;
import com.zhidisoft.talentservice.entity.Person;

import java.util.List;

public interface PersonDao extends IBaseDao<Integer, Person>{
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    Person selectByPrimaryKey(Integer id);

    List<Person> selectAll();

    int updateByPrimaryKey(Person record);
    int updateTalent(Person person);
}