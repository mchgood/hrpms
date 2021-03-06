package com.zhidisoft.filter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.zhidisoft.system.dao.DictDao;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.Dict;
import com.zhidisoft.system.entity.User;

public class RememberAuthenticationFilter extends FormAuthenticationFilter {
    
    @Resource
    UserDao userDao;
    @Resource
    DictDao dictDao;

    /**
     * 这个方法决定了是否能让用户登录
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        // 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true 证明是没登陆直接通过记住我功能进来的
        if (!subject.isAuthenticated() && subject.isRemembered()) {
            Session session = subject.getSession(true);
            User root = (User)subject.getPrincipal();
            //向session中存入数据
            List<Dict> DictList = dictDao.selectAll();
            session.setAttribute("DictList", DictList);
            session.setAttribute("root", root);
        }

        // 这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered()
        // 让它同时也兼容remember这种情况
        return subject.isAuthenticated() || subject.isRemembered();
    }
}