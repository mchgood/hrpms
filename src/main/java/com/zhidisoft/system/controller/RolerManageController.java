package com.zhidisoft.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidisoft.base.BaseController;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.result.TreeNode;
import com.zhidisoft.system.dao.FunctionDao;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.system.service.FunctionServiceIf;
import com.zhidisoft.system.service.RolrServiceIf;
@Controller
public class RolerManageController extends BaseController{
    @Resource
    RolrServiceIf rolerService;
    @Resource
    FunctionServiceIf functionServiceIf;
    
    /** 角色信息分页
     * @param page      当前页数
     * @param rows      每页记录数
     * @param rolename  角色名称，当为null时，全部查询
     * 
     */
    @RequestMapping("/role/roleList")
    @ResponseBody
    public JsonResult roleInfo(Integer page,Integer rows,String rolename) {
        //创建user的分页对象
        PageBean<Role> pageBean = new PageBean<Role>();
        pageBean.setPage(page);
        pageBean.setRows(rows);
        //创建map集合将查询条件添加到PageBean当中
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("rolename", rolename);
        pageBean.setCondition(condition);
        //调用分页方法获得分页后的数据
        PageBean<Role> bean = rolerService.findByPage(pageBean);
        return JsonResult.buildSuccessResult(bean);
    }
    
    /**查询到角色信息返回给前端，若ID为NULL页面显示空白页面
     * @param id    角色ID
     * 
     */
    @RequestMapping("/role/changeRole")
    public ModelAndView changeRole(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = null;
        if(id!=null) {
          //获取角色信息
          role = rolerService.get(id);
        }
        modelAndView.setViewName("sys/changeRole");
        modelAndView.addObject("role", role);
        return modelAndView;
    }
    
    /**用来展示树形菜单
     * @param id   父级菜单的ID
     * @return
     */
    @RequestMapping("/role/menu")
    @ResponseBody
    public JsonResult menu(Integer roleId,Integer id) {
        List<TreeNode> menList = functionServiceIf.findMenuByRoleId(roleId,id);
        return JsonResult.buildSuccessResult(menList);
    }
    

    /**
     * @param role  角色的实体类
     * @param arr   选中的功能集合
     * @return
     */
    @RequestMapping("/role/updateRole")
    @ResponseBody
    public JsonResult changRole(Role role,@RequestParam(required = false, value = "arr[]") List<String> arr) {
        int rows = rolerService.changRole(role, arr);
        if(rows>0) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
    
    /**通过角色ID删除角色信息
     * @param id        角色ID
     * @return
     */
    @RequestMapping("sys/deleteRole")
    @ResponseBody
    public JsonResult deleteRolr(Integer id) {
        if(rolerService.deleteById(id)) {
            return JsonResult.buildSuccessResult();
        }else {
            return JsonResult.buildFailResult("失败");
        } 
    }
}
