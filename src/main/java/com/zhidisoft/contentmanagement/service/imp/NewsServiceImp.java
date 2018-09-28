package com.zhidisoft.contentmanagement.service.imp;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.contentmanagement.dao.NewsDao;
import com.zhidisoft.contentmanagement.entity.News;
import com.zhidisoft.contentmanagement.service.NewsService;
@Service
public class NewsServiceImp extends BaseServiceImpl<Integer, News> implements NewsService {
	@Resource
   NewsDao dao;
	@Override
	public boolean updateNews( News news) {
		// TODO Auto-generated method stub
		return dao.updateNews(news);
	}

}
