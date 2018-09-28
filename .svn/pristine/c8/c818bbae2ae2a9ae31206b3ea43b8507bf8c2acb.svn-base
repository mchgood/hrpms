package com.zhidisoft.contentmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.contentmanagement.entity.News;
import com.zhidisoft.contentmanagement.service.NewsService;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
  
@Controller
@RequestMapping("/news")
public class NewsController {
	@Resource
	NewsService newsService;
	
	/**
	 * 邮件分页查询
	 * @param page
	 * @param rows
	 * @param userName
	 * @param toAddr
	 * @return
	 */
	
	@RequestMapping("/newsInfo")
	@ResponseBody
	public JsonResult newsInfo(Integer page,Integer rows,String newstitle){
		PageBean<News> newsPageBean = new PageBean<News>();
		newsPageBean.setPage(page);
		newsPageBean.setRows(rows);
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("newstitle", newstitle);
		
		newsPageBean.setCondition(condition);
		
		PageBean<News> pageBean = newsService.findByPage(newsPageBean);
		return JsonResult.buildSuccessResult(pageBean);

	}
	/**
	 * 跳转到修改新闻页面
	 */
	@RequestMapping("/tonewsedit")
	public String toNewsEdit(Integer id,Model model){
		News news = newsService.get(id);
		model.addAttribute("news",news);
		
		
		return "content/newsedit";
	}
	/**
	 * 修改新闻
	 */
	@RequestMapping("/newsEdit")
	public String newsEdit(@Valid News news,Errors errors,Model model,Integer id){
		
			
		if(errors.hasErrors()){
			return "content/newsedit";
		}
		news.setId(id);
		boolean success = newsService.updateNews(news);
		
		if(success){
			return "content/newslist";
		}else{
			model.addAttribute("message","保存失败");
			
		}
		return "content/newsedit";
	}
	
	/**
	 * 删除新闻
	 */
	@RequestMapping("/newsdelete")
	@ResponseBody
	public JsonResult newsDelete(Integer id){
		
		boolean success = newsService.deleteById(id);
		String message=null;
		if(success){
			message="删除成功";
		}else{
			message="删除失败";
		}
	return JsonResult.buildSuccessResult(message);
	}
	
	/**
	 * 添加新闻
	 */
	@RequestMapping("/newsAdd")	
	public String newsAdd(@Valid News news,Errors errors){
		
		if(errors.hasErrors()){
			return "content/newsedit";
		}
	 newsService.save(news);
		
	
	return "content/newslist";
	}
}
