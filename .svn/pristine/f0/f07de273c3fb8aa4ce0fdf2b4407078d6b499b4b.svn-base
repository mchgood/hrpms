package com.zhidisoft.system.service;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.system.entity.SMSTemplate;

public interface SMSServiceIf extends IBaseService<Integer, SMSTemplate>{
   
    /**通过短信ID查询短信信息
     * @param id        短信ID
     * @return
     */
    SMSTemplate findBySMSId(Integer id);

    /**添加或者修改短信实体类
     * @param sms       短信实体类
     * @return
     */
    int addOrUpdateSMS(SMSTemplate sms);
}
