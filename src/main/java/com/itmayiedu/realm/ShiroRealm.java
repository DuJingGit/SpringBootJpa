package com.itmayiedu.realm;

import com.itmayiedu.entity.second.Permission;
import com.itmayiedu.entity.second.Role;
import com.itmayiedu.entity.second.User;
import com.itmayiedu.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Autowired
    HttpServletRequest request;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        User userInfo  = (User)principals.getPrimaryPrincipal();
        HttpSession session = request.getSession();
        User userInfo = userService.getUserByName(session.getAttribute("name").toString());
        for(Role role:userInfo.getRoleList()){
            System.out.println(role.toString());
            authorizationInfo.addRole(role.getRolename());
            for(Permission p:role.getPermissionList()){
                System.out.println(p.toString());
                authorizationInfo.addStringPermission(p.getPermissionname());
            }
        }
        //CacheUntil.setCacheTree(authorizationInfo);
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("ShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String name = (String)token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.getUserByName(name);
        System.out.println(userInfo.toString());
        if(userInfo == null){
            return null;
        }
        System.out.println(userInfo.getPassword()+"-----");
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(name, userInfo.getPassword(), getName());
        return authenticationInfo;
    }
}
