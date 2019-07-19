package com.itmayiedu.realm;

import com.itmayiedu.service.PermissionService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomPermissionsAuthorizationFilter extends RolesAuthorizationFilter {

    @Resource
    private PermissionService permissionService;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
       System.out.println("----------------------permission");
        HttpServletRequest request1 = (HttpServletRequest) request;
        Subject subject = getSubject(request, response);
        if(subject.isPermitted("permissionQuery")&&request1.getMethod().equals(permissionService.getPermission("permissionQuery").getMethod())){
            return true;
        }
        if(subject.isPermitted("permissionAdd")&&request1.getMethod().equals(permissionService.getPermission("permissionAdd").getMethod())){
            return true;
        }
        if(subject.isPermitted("permissionDel")&&request1.getMethod().equals(permissionService.getPermission("permissionDel").getMethod())){
            return true;
        }
        if(subject.isPermitted("permissionUpdate")&&request1.getMethod().equals(permissionService.getPermission("permissionUpdate").getMethod())){
            return true;
        }
        return false;
    }
}
