package com.zhidisoft.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.dao.DictDao;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.entity.EmailTemplate;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.service.DictServiceIf;
@Service
public class DictServiceImpl extends BaseServiceImpl<Integer, Dict> implements DictServiceIf{
    @Resource
    DictDao dictDao;
    
    /*格式化分页后的数据*/
    @Override
    public PageBean<Dict> findByPageFormate(PageBean<Dict> pageBean) {
        //获取分页后的pageBean
        PageBean<Dict> page = findByPage(pageBean);
        List<Dict> result = page.getResult();
        
        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        //获取字典集合
        List<Dict> DictList = (List<Dict>)session.getAttribute("DictList");
        //格式化状态
        for (Dict dd : result) {
            for (Dict dict : DictList) {
                //格式化删除标志位
                if(dict.getName().equals("del_flag") &&dd.getStatus().equals(dict.getValue())) {
                    dd.setStatusName(dict.getLabel());
                }
            }
        }
        return page;
    }

    /*通过字典ID查询字典信息*/
    @Override
    public Dict findBySMSId(Integer id) {
        //判断是添加还是修改
        Dict dict = null;
        if(id!=null) {
            dict = dictDao.selectByPrimaryKey(id);
        }
        return dict;
    }

    /*添加或者修改字典信息*/
    @Override
    public int addOrUpdateSMS(Dict dict) {
        if(dict.getId()!=null) {
            //更新
            dict.setUpdatetime(new Date());
            if(dictDao.updateByPrimaryKey(dict)<0) {
                return -1;
            }
            
        }else {
            //添加
            dict.setCreatetime(new Date());
            if(dictDao.insert(dict)<0){
                return -1;
            }
        }
        return 1;
    }

}
