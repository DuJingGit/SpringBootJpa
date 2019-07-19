package com.itmayiedu.realm;

import com.itmayiedu.service.PermissionService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomUsersAuthorizationFilter extends RolesAuthorizationFilter {

    @Resource
    private PermissionService permissionService;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest request1 = (HttpServletRequest) request;
        Subject subject = getSubject(request, response);
        if(subject.isPermitted("userQuery")&&request1.getMethod().equals(permissionService.getPermission("userQuery").getMethod())){
            return true;
        }
        if(subject.isPermitted("userAdd")&&request1.getMethod().equals(permissionService.getPermission("userAdd").getMethod())){
            return true;
        }
        if(subject.isPermitted("userDel")&&request1.getMethod().equals(permissionService.getPermission("userDel").getMethod())){
            return true;
        }
        if(subject.isPermitted("userUpdate")&&request1.getMethod().equals(permissionService.getPermission("userUpdate").getMethod())){
            return true;
        }
        return false;
    }
}
