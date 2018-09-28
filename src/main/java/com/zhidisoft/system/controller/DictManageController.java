package com.zhidisoft.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.service.DictServiceIf;

/**字典管理 
* @author tkc  
* @date 2018年9月25日  
*/ 
@Controller
public class DictManageController {
   @Resource
   DictServiceIf dictServiceIf;
   
   /**数据字典分页
     * @param page      当前页数
     * @param rows      每页显示数
     * @param name      字典名称
     * @param status    状态
     * @return
     */
   @RequestMapping("/dict/dictList")
   @ResponseBody
   public JsonResult DictInfo(Integer page,Integer rows,String name,Integer status) {
       //创建user的分页对象
       PageBean<Dict> pageBean = new PageBean<Dict>();
       pageBean.setPage(page);
       pageBean.setRows(rows);
       //创建map集合将查询条件添加到PageBean当中
       Map<String, Object> condition = new HashMap<String, Object>();
       condition.put("name", name);
       condition.put("status", status);

       pageBean.setCondition(condition);
       //调用分页方法获得分页后的数据
       PageBean<Dict> bean = dictServiceIf.findByPageFormate(pageBean);
       return JsonResult.buildSuccessResult(bean);
   }
   
   /**显示字典实体类
     * @param id    字典ID
     * @return
     */
   @RequestMapping("/dict/toChangDict")
   public ModelAndView toChangDict(Integer id) {
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("sys/changeDict");
       
       Dict dict = dictServiceIf.findBySMSId(id);
       modelAndView.addObject("dict", dict);
       return modelAndView;
   }
   
   /**修改或者添加字典
     * @param dict      字典实体类
     * @return
     */
   @RequestMapping("/dict/updateDict")
   @ResponseBody
   public JsonResult updateFuncInfo(Dict dict) {
       int rows = dictServiceIf.addOrUpdateSMS(dict);
       if(rows>0) {
           return JsonResult.buildSuccessResult();
       }else {
           return JsonResult.buildFailResult("失败");
       } 
   }
   
   /**删除字典
     * @param id        字典ID
     * @return
     */
    @RequestMapping("dict/deleteDict")
   @ResponseBody
   public JsonResult deleteEmail(Integer id) {
       if(dictServiceIf.deleteById(id)) {
           return JsonResult.buildSuccessResult();
       }else {
           return JsonResult.buildFailResult("失败");
       } 
   }
}
