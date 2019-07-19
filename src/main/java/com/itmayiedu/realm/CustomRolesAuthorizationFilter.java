package com.itmayiedu.realm;

import com.itmayiedu.service.PermissionService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Resource
    private PermissionService permissionService;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest request1 = (HttpServletRequest) request;
        Subject subject = getSubject(request, response);
        if(subject.isPermitted("roleQuery")&&request1.getMethod().equals(permissionService.getPermission("roleQuery").getMethod())){
            return true;
        }
        if(subject.isPermitted("roleAdd")&&request1.getMethod().equals(permissionService.getPermission("roleAdd").getMethod())){
            return true;
        }
        if(subject.isPermitted("roleDel")&&request1.getMethod().equals(permissionService.getPermission("roleDel").getMethod())){
            return true;
        }
        if(subject.isPermitted("roleUpdate")&&request1.getMethod().equals(permissionService.getPermission("roleUpdate").getMethod())){
            return true;
        }
        return false;
    }
}
