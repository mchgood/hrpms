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
import com.zhidisoft.system.service.FunctionServiceIf;
@Controller
public class FuncManageController extends BaseController{
    @Resource
    FunctionServiceIf functionService;
    
    
    /** 角色信息分页
     * @param page      当前页数
     * @param rows      每页记录数
     * @param funcname  菜单名称，当为null时，全部查询
     * 
     */
    @RequestMapping("/func/funcList")
    @ResponseBody
    public JsonResult funcInfo(Integer page,Integer rows,String funcname) {
        //创建user的分页对象
        PageBean<Function> pageBean = new PageBean<Function>();
        pageBean.setPage(page);
        pageBean.setRows(rows);
        //创建map集合将查询条件添加到PageBean当中
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("funcname", funcname);
        pageBean.setCondition(condition);
        //调用分页方法获得分页后的数据
        PageBean<Function> bean = functionService.formataPage(pageBean);
        return JsonResult.buildSuccessResult(bean);
    }
    
    /**根据菜单ID查询出菜单信息，并返回view
     * @param id        菜单的ID，当为空时，只返回固定信息
     * @return
     */
    @RequestMapping("/func/toChangFunc")
    public ModelAndView toChangFunc(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sys/changeFunc");
        
        Map<String, Object> map = functionService.fingInfoById(id);
        modelAndView.addObject("func", map.get("func"));
        modelAndView.addObject("parentMenu", map.get("parentMenu"));
        return modelAndView;
    }
    
    /**修改或者添加菜单
     * @param func  菜单的实体类
     * @return
     */
    @RequestMapping("/func/updateFunc")
    @ResponseBody
    public JsonResult updateFuncInfo(Function func) {
        int rows = functionService.addOrUpdateFunc(func);
        if(rows>0) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
    
    /**通过权限ID删除权限
     * @param id        权限ID
     * @return
     */
    @RequestMapping("func/deleteFunc")
    @ResponseBody
    public JsonResult deleteFunc(Integer id) {
        if(functionService.deleteById(id)) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
}
