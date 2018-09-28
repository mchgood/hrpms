package com.zhidisoft.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidisoft.base.BaseController;
import com.zhidisoft.result.CheckBox;
import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.system.service.RolrServiceIf;
import com.zhidisoft.system.service.UserServiceIf;
import com.zhidisoft.system.service.impl.RolerServiceImpl;

/**
* 系统管理控制器
* @author tkc  
* @date 2018年9月15日  
*/ 
@SuppressWarnings("all")
@Controller
public class UserManageController extends BaseController{
	
	@Resource
	UserServiceIf userService;
	@Resource
	RolrServiceIf rolerService;
	
	/**
	 * 用户信息列表的分页处理
	 * @param page         页数
	 * @param rows         每页数目
	 * @param username     用户名
	 * @param status       状态
	 * @param phone        手机号
	 * @return             分页后的数据
	 */
	@RequestMapping("/system/listUserInfo")
	@ResponseBody
	public JsonResult userInfo(Integer page,Integer rows,String username,Integer status,String phone) {
		//创建user的分页对象
		PageBean<User> userPageBean = new PageBean<User>();
		userPageBean.setPage(page);
		userPageBean.setRows(rows);
		//创建map集合将查询条件添加到PageBean当中
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		condition.put("phone", phone);
		condition.put("status", status);
		userPageBean.setCondition(condition);
		//调用分页方法获得分页后的数据
		PageBean<User> pageBean = userService.findByPage(userPageBean);
		return JsonResult.buildSuccessResult(pageBean);
	}
	
	/**
	 * 通过获取的 用户ID查询到用户的信息并且返回到修改用户的界面
	 * @param id       用户ID
	 *        
	 */
	@RequestMapping("/changeUser")
	public ModelAndView changeUser(Integer id) {
	    ModelAndView modelAndView = new ModelAndView();
	    List list = new ArrayList<CheckBox>();
	    if(id!=0) {
	        //通过用户ID获取用户的实体类对象
	        User user = userService.get(id);
	        //通过用户ID获取用户的角色集合
	        list = rolerService.findRoleList(user.getId());
	        //将信息全部存到ModelAndView中
	        modelAndView.addObject("user", user);
	        modelAndView.addObject("list", list);
	    }else {
    	      //获取到数据库中的所有角色
    	      List<Role> roleList = rolerService.findAll();
    	      for (Role role : roleList) {
                CheckBox checkBox = new CheckBox();
                checkBox.setId(role.getId());
                checkBox.setName(role.getRolename());
                list.add(checkBox);
              }
    	      modelAndView.addObject("list", list);
	   }
	    modelAndView.setViewName("sys/changeUser");
	    return modelAndView;
	}

	@RequestMapping("/sys/updateUser")
	@ResponseBody
	public JsonResult updateUser(User user,@RequestParam(required = false, value = "arr[]") List<String> arr) {	    
	    //更新用户信息，若userId=null,添加用户信息
	    int updateByusername = userService.updateByusername(user);
	    //删除用户原来的所有权限
	    int deleteByUserId = rolerService.deleteByUserId(user.getId());
	    //添加用户的权限
	    int addUserRole = rolerService.addUserRole(user.getId(),arr);

	    if(updateByusername>0 && deleteByUserId>=0 && addUserRole>0) {
	        return JsonResult.buildSuccessResult();
	    }else {
	        return JsonResult.buildFailResult("修改失败");
	    }
	}
	
	/**
	 * 用来删除用户
	 * @param id   用户 ID
	 * 
	 */
	@RequestMapping("/sys/deleteUser")
    @ResponseBody
	public JsonResult deleteUser(Integer id) {
	    int userRows = userService.deleteUserById(id);
	    int roleRows = rolerService.deleteByUserId(id);
	    if(userRows>0 && roleRows>0) {
	        return JsonResult.buildSuccessResult();
	    }else {
	        return JsonResult.buildFailResult("删除失败");
	    }
	}
	
	
}
