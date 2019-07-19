package com.itmayiedu.realm;

import com.itmayiedu.entity.second.Permission;
import com.itmayiedu.entity.second.Role;
import com.itmayiedu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *  权限 拦截策略
 */
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest request1 = (HttpServletRequest) request;
        //请求的url
        String requestURL = getPathWithinApplication(request);
        System.out.println("请求的url :"+requestURL);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UnauthorizedException ex = new UnauthorizedException("请登录");
            subject.getSession().setAttribute("ex",ex);
            WebUtils.issueRedirect(request, response, "/login/shiro-403");
            return false;
        }
        String name = (String) request1.getSession().getAttribute("name");
        System.out.println(name+"----name");
        List<Permission> permissions = new ArrayList<>();
        List<Role> roleList = userService.getUserByName(name).getRoleList();
        for (Role role : roleList) {
            List<Permission> permissionList = role.getPermissionList();
            for (Permission permission : permissionList) {
                permissions.add(permission);
            }
        }

        boolean hasPermission = false;
        for (Permission permission : permissions) {

              if (permission.getUrl().equals(requestURL)){
                  hasPermission = true;
                  break;
              }
        }
        if (hasPermission){
            return true;
        }else {
            UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径" + requestURL + "的权限");
            subject.getSession().setAttribute("ex",ex);
            WebUtils.issueRedirect(request, response, "/login/shiro-unauthorized");
            return false;
        }

    }
}