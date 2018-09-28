package com.zhidisoft.contentmanagement.dao;

import com.zhidisoft.base.IBaseDao;
import com.zhidisoft.contentmanagement.entity.News;
import java.util.List;

import javax.validation.Valid;

public interface NewsDao extends IBaseDao<Integer, News>{

	boolean updateNews( News news);
  
}