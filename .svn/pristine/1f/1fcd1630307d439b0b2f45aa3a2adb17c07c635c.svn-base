package com.zhidisoft.system.service;

import com.zhidisoft.base.IBaseService;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.entity.EmailTemplate;

public interface DictServiceIf extends IBaseService<Integer, Dict>{

    /**格式化分页后的数据
     * @param pageBean      分页实体类
     * @return
     */
    PageBean<Dict> findByPageFormate(PageBean<Dict> pageBean);

    /**通过字典ID查询字典信息
     * @param id        字典id
     * @return
     */
    Dict findBySMSId(Integer id);

    /**添加或者修改字典信息
     * @param dict      字典实体类
     * @return
     */
    int addOrUpdateSMS(Dict dict);

}
