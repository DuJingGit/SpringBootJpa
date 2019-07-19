package com.itmayiedu.controller;

import com.itmayiedu.entity.second.EntityPage;
import com.itmayiedu.entity.second.Permission;
import com.itmayiedu.entity.second.PermissionBO;
import com.itmayiedu.entity.second.Role;
import com.itmayiedu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    private HashMap<String,Object> getPermissionsForPage(EntityPage<PermissionBO> page){
        HashMap<String,Object> map = new HashMap<>();
        map.put("permissionInfo",permissionService.getPermissionsForPage(page));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/allpermission",method = RequestMethod.GET)
    private HashMap<String,Object> getPermissions(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("permissionInfo",permissionService.getPermissions());
        return map;
    }

    @RequestMapping(value = "/permissions",method = RequestMethod.GET)
//    @RequiresPermissions(value = {"/permission/permissions"})
    public String toPermission() {
        return "/shiro-permissions";
    }

    @RequestMapping(value = "/addpermission",method = RequestMethod.GET)
    public String toAddPermission() {
        return "/shiro-add-permission";
    }

    @RequestMapping(value = "/updpermission/{id}",method = RequestMethod.GET)
    public String toUpdPermission(@PathVariable Integer id, ModelMap model) {
        model.put("id",id);
        return "/shiro-upd-permission";
    }

    @ResponseBody
    @RequestMapping(value = "/permission",method = RequestMethod.DELETE)
    private HashMap<String,Object> delPermissionById(Integer id){
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(permissionService.delPermissionById(id)){
            returnValue="删除成功！！！";
        }else {
            returnValue="删除失败！！！";
        }
        map.put("delInfo",returnValue);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/permission",method = RequestMethod.POST)
    private HashMap<String,Object> addPermission(Permission permission){
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(permissionService.addPermission(permission)){
            returnValue="新增成功！！！";
        }else {
            returnValue="新增失败！！！";
        }
        map.put("addInfo",returnValue);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/permission",method = RequestMethod.PUT)
    private HashMap<String,Object> updPermission(Permission permission){
        List<Role> roleList = permissionService.getPermissionById(permission.getId()).getRoleList();
        permission.setRoleList(roleList);
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(permissionService.addPermission(permission)){
            returnValue="修改成功！！！";
        }else {
            returnValue="修改失败！！！";
        }
        map.put("addInfo",returnValue);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/permissionbyid",method = RequestMethod.GET)
    private HashMap<String,Object> getPermissionById(Integer id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("permissionInfo",permissionService.getPermissionBOById(id));
        return map;
    }
}
