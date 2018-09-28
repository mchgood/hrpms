package com.zhidisoft.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.dao.DictDao;
import com.zhidisoft.system.dao.EmailTemplateDao;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.entity.EmailTemplate;
import com.zhidisoft.system.service.EmailServiceIf;
@Service
public class EmailServiceImpl extends BaseServiceImpl<Integer, EmailTemplate> implements EmailServiceIf{
    @Resource
    EmailTemplateDao emailTemplateDao;
    @Resource
    DictDao dictDao;
    
    /*通过邮件ID查询邮件实体类*/
    @Override
    public EmailTemplate findBySMSId(Integer id) {
        EmailTemplate email = null;
        //判断是添加还是修改
        if(id!=null) {
            //修改
            email = emailTemplateDao.selectByPrimaryKey(id);
        }
        return email;
    }

    /*更新或者添加邮件信息*/
    @Override
    public int addOrUpdateSMS(EmailTemplate email) {
        //判断是添加还是修改
        if(email.getId()!=null) {
            //修改
            email.setUpdatetime(new Date());
            if(emailTemplateDao.updateByPrimaryKey(email)<0) {
                return -1;
            }
            
        }else {
            //新增
            email.setCreatetime(new Date());
            if(emailTemplateDao.insert(email)<0) {
                return -1;
            }
        }
        return 1;
    }

}
