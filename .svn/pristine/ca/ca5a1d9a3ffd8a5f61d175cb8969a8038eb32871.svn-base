package com.zhidisoft.system.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.base.BaseServiceImpl;
import com.zhidisoft.system.dao.SMSTemplateDao;
import com.zhidisoft.system.entity.SMSTemplate;
import com.zhidisoft.system.service.SMSServiceIf;
@Service
public class SMSServiceImpl extends BaseServiceImpl<Integer, SMSTemplate> implements SMSServiceIf{
    @Resource
    SMSTemplateDao sMSTemplateDao;
    
    /*通过短信ID查询短信信息*/
    @Override
    public SMSTemplate findBySMSId(Integer id) {
        SMSTemplate sms = null;
        if(id!=null) {
            sms= sMSTemplateDao.selectByPrimaryKey(id);
        }
        return sms;
    }

    /*添加或者修改短信实体类*/
    @Override
    public int addOrUpdateSMS(SMSTemplate sms) {
        //判断是添加还是修改
        if(sms.getId() != null) {
            //修改
            sms.setUpdatetime(new Date());
            if(sMSTemplateDao.updateByPrimaryKey(sms)<0) {
                return -1;
            }
            
        }else {
            //添加
            sms.setCreatetime(new Date());
            if(sMSTemplateDao.insert(sms)<0){
                return -1;
            }
        }
        return 1;
    }

}
