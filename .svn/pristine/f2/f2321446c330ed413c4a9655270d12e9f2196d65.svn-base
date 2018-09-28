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
import com.zhidisoft.system.entity.EmailTemplate;
import com.zhidisoft.system.entity.SMSTemplate;
import com.zhidisoft.system.service.EmailServiceIf;

@Controller
public class EmailTempletelManageController {
    @Resource
    EmailServiceIf emailServiceIf;
    
    
    /**
     * @param page      当前页数
     * @param rows      每页记录数
     * @param subject   标题（搜索使用）
     * @return
     */
    @RequestMapping("/email/emailList")
    @ResponseBody
    public JsonResult EmilInfo(Integer page,Integer rows,String subject) {
        //创建user的分页对象
        PageBean<EmailTemplate> pageBean = new PageBean<EmailTemplate>();
        pageBean.setPage(page);
        pageBean.setRows(rows);
        //创建map集合将查询条件添加到PageBean当中
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("subject", subject);

        pageBean.setCondition(condition);
        //调用分页方法获得分页后的数据
        PageBean<EmailTemplate> bean = emailServiceIf.findByPage(pageBean);
        return JsonResult.buildSuccessResult(bean);
    }
    
    /**查询对应ID的邮件模板实体类
     * @param id    邮件Id
     * @return
     */
    @RequestMapping("/email/toChangEmail")
    public ModelAndView toChangEmail(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sys/changeEmail");
        
        EmailTemplate email = emailServiceIf.findBySMSId(id);
        modelAndView.addObject("email", email);
        return modelAndView;
    }
    
    /**更新或者添加邮件模板
     * @param email     邮件实体类
     * @return
     */
    @RequestMapping("/email/updateEmail")
    @ResponseBody
    public JsonResult updateFuncInfo(EmailTemplate email) {
        int rows = emailServiceIf.addOrUpdateSMS(email);
        if(rows>0) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
    
    /**根据邮件ID删除邮件
     * @param id        邮件ID
     * @return
     */
    @RequestMapping("email/deleteEmail")
    @ResponseBody
    public JsonResult deleteEmail(Integer id) {
        if(emailServiceIf.deleteById(id)) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
    
    
}
