package com.neo.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: zglai
 * Date:22:55 2021/1/14
 * @Description:<描述>
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //认证并授权,可以授权多个角色
        simpleAccountRealm.addAccount("Mark","123456","admin","user");
    }

    @Test
    public void testAuthentication(){
        //1，构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        // 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        subject.login(token);

        System.out.println("isAuthenticated="+subject.isAuthenticated());

        //判断是否有某个权限
        subject.checkRole("admin");
        //退出
        subject.logout();
    }
}
