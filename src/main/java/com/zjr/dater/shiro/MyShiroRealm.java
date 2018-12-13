package com.zjr.dater.shiro;

import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.PermissionService;
import com.zjr.dater.business.service.RoleService;
import com.zjr.dater.business.service.UserService;
import com.zjr.dater.common.utils.MD5Utils;
import com.zjr.dater.common.utils.SpringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhujr on 2018/10/25.
 * 身份认证
 */
@Component
public class MyShiroRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;

    /**
     * 认证信息（身份认证）Authentication是用来验证用户身份
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        Map<String,Object> map = new HashMap<>();
        map.put("name",username);
        //密码进行加密处理
        String pass = MD5Utils.encryptPassword(password,username);
        map.put("password",pass);

        User user = new User();
        user.setId(Long.valueOf(11111));
        user.setName(username);
        user.setPassword(password);

        return new SimpleAuthenticationInfo(user,password,getName());
    }

    /**
     * 授权用户权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户角色
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);

        //获取用户权限
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        return info;
    }
}
