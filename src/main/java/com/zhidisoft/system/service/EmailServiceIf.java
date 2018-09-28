package com.zhidisoft.system.service;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.EmailTemplate;

public interface EmailServiceIf extends IBaseService<Integer, EmailTemplate>{

    /**通过邮件ID查询邮件实体类
     * @param id    邮件ID
     * @return
     */
    EmailTemplate findBySMSId(Integer id);

    /**更新或者添加邮件信息
     * @param email     邮件实体类
     * @return
     */
    int addOrUpdateSMS(EmailTemplate email);

}
