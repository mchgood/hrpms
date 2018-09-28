package com.zhidisoft.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidisoft.base.BaseController;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.entity.SMSTemplate;
import com.zhidisoft.system.service.SMSServiceIf;
@Controller
public class SMSManageController extends BaseController{
    @Resource
    SMSServiceIf SMSService;
    
    /**短信模板分页
     * @param page              当前页数
     * @param rows              每页条数
     * @param templateCode      模板代码
     * @param subject           模板主题
     * 
     */
    @RequestMapping("/sms/smsList")
    @ResponseBody
    public JsonResult SMSInfo(Integer page,Integer rows,Integer templateCode,String subject) {
        //创建user的分页对象
        PageBean<SMSTemplate> pageBean = new PageBean<SMSTemplate>();
        pageBean.setPage(page);
        pageBean.setRows(rows);
        //创建map集合将查询条件添加到PageBean当中
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("templateCode", templateCode);
        condition.put("subject", subject);
        pageBean.setCondition(condition);
        //调用分页方法获得分页后的数据
        PageBean<SMSTemplate> bean = SMSService.findByPage(pageBean);
        return JsonResult.buildSuccessResult(bean);
    }
    
    /**根据传过来的ID查询模板，若Id为NULL，返回NULL
     * @param id    模板ID
     * 
     */
    @RequestMapping("/sms/toChangsms")
    public ModelAndView toChangFunc(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sys/changeSMS");
        SMSTemplate sms = SMSService.findBySMSId(id);
        modelAndView.addObject("sms", sms);
        return modelAndView;
    }   
    
    /**添加或者修改短信模板
     * @param sms   短信实体类
     * @return
     */
    @RequestMapping("/sms/updateSMS")
    @ResponseBody
    public JsonResult updateFuncInfo(SMSTemplate sms) {
        int rows = SMSService.addOrUpdateSMS(sms);
        if(rows>0) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
    
    /**根据短信ID删除短信
     * @param id        短信ID
     * @return
     */
    @RequestMapping("sms/deletesms")
    @ResponseBody
    public JsonResult deleteFunc(Integer id) {
        if(SMSService.deleteById(id)) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
}
