package com.zhidisoft.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.result.TreeNode;
import com.zhidisoft.system.dao.FunctionDao;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.service.FunctionServiceIf;
import com.zhidisoft.system.service.RolrServiceIf;
@Service
@SuppressWarnings("all")
public class FunctionServiceImpl extends BaseServiceImpl<Integer, Function> implements FunctionServiceIf{
	@Resource
	FunctionDao functionDao;
	
	/*查询所有权限，并且通过用户ID查询所有角色的权限*/
	@Override
	public List<TreeNode> findMenuByUserId(Integer userId,Integer id) {
	    List<TreeNode> treeNodeList = new ArrayList();
	    //首先查出所有父级菜单
	    List<Function> funcList = functionDao.findMenuList(userId,id);
	    for(Function func:funcList){          
            TreeNode tn = new  TreeNode();
            
            tn.setId(func.getId());
            tn.setText(func.getFuncname());
            // 父菜单
            if(func.getParentid()==null || func.getFuncurl()==null){
                tn.setState("closed");
            }
            if(func.getFuncurl()!=null){
                tn.getAttributes().put("url", func.getFuncurl());
            }
           
            treeNodeList.add(tn);           
        }
	    return treeNodeList;
	}

	/*查询所有权限，并且通过角色ID查询所有角色的权限*/
    @Override
    public List<TreeNode> findMenuByRoleId(Integer roleId, Integer id) {
        List<TreeNode> treeNodeList = new ArrayList();
        //获取所有的功能
        List<Function> funcList = functionDao.selectAll();
        List<Function> selectList = functionDao.findFunListByRoleId(roleId);
        List<TreeNode> parent=new ArrayList();
        //获得所有的父节点
        for(Function func:funcList) {
            if(func.getParentid() ==null && !(func.getFuncname().equals("个人信息"))) {
                TreeNode tn = new  TreeNode();
                tn.setId(func.getId());
                tn.setText(func.getFuncname());
                tn.setState("open");
                parent.add(tn);
            }
        }
        //循环父节点
        for (TreeNode treeNode : parent) {
            //循环查询到的所有节点，当子节点的parentid=父节点id，把节点添加到父节点中
            for (Function fun : funcList) {
                if(fun.getParentid()==treeNode.getId()) {
                    TreeNode tn = new  TreeNode();
                    tn.setId(fun.getId());
                    tn.setText(fun.getFuncname());
                    for (Function f : selectList) {
                        if(f.getId()==fun.getId()) {
                            tn.setChecked(true);
                        }
                    }
                    List<TreeNode> children = treeNode.getChildren();
                    children.add(tn);
                }
            }
        }
        return parent;
    }

    /*格式化分页后的数据*/
    @Override
    public PageBean<Function> formataPage(PageBean<Function> pageBean) {
        PageBean page = findByPage(pageBean);
        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        //获取字典集合
        List<Dict> DictList = (List<Dict>)session.getAttribute("DictList");
        
        List<Function> result = page.getResult();
        List<Function> parentList = functionDao.selectAllParentMenu();
        //格式化状态
        for (Function func : result) {
            for (Dict dict : DictList) {
                //格式化删除标志位
                if(dict.getName().equals("del_flag") &&func.getStatus().equals(dict.getValue())) {
                    func.setStatusLabel(dict.getLabel());
                }
            }
        }
        //格式化父菜单名称
        for(int i =0;i<result.size();i++) {
            for(int j =0;j<parentList.size();j++) {
                if(result.get(i).getParentid() == parentList.get(j).getId()) {
                    result.get(i).setParentName(parentList.get(j).getFuncname());
                }
            }
        }
        return page;
    }

    /*通过权限ID查询权限的信息*/
    @Override
    public Map<String, Object> fingInfoById(Integer id) {
        Function func = null;
        //判断是添加还是修改
        if(id!=null) {
            //修改
            //获取菜单实体类
            func = functionDao.selectByPrimaryKey(id);
        }
        //查出所有父级菜单
        List<Function> parentMenu = functionDao.selectAllParentMenu();
        Map<String, Object> map = new HashMap();
        map.put("func", func);
        map.put("parentMenu", parentMenu);
        return map;
    }
    
    /*添加或者更新权限*/
    public int addOrUpdateFunc(Function func) {
        //判断是添加还是删除
        Integer id = func.getId();
        if(id!=null) {
            //修改 
            //设置更新时间
            func.setUpdatetime(new Date());
            if(functionDao.updateByPrimaryKey(func)<0) {
                return -1;
            }
            
        }else {
           //添加
            //设置添加时间
            func.setCreatetime(new Date());
            if(functionDao.insert(func)<0) {
                return -1;
            }
        }
        return 1;
    }

    /*根据权限ID删除权限*/
    @Override
    public int deleteFunc(Integer id) {
        //逻辑删除菜单
        if(functionDao.deleteByPrimaryKey(id)<0) {
            return -1;
        }
        //删除菜单的关联
        if(functionDao.deleteFuncAndRole(id)<0) {
            return -1;
        }
        return 1;
    }

}
