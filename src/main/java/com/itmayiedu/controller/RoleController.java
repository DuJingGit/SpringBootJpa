package com.itmayiedu.controller;

import com.itmayiedu.entity.second.*;
import com.itmayiedu.service.PermissionService;
import com.itmayiedu.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/roles",method = RequestMethod.GET)
//    @RequiresPermissions(value = {"/role/roles"})
    public String toRole() {
        return "/shiro-roles";
    }

    @RequestMapping(value = "/addrole",method = RequestMethod.GET)
    public String toAddRole() {
        return "/shiro-add-role";
    }

    @RequestMapping(value = "/updrole/{id}",method = RequestMethod.GET)
    public String toUpdRole(@PathVariable Integer id, ModelMap model) {
        model.put("id",id);
        return "/shiro-upd-role";
    }

    @RequestMapping(value = "/topermissiontorole/{id}",method = RequestMethod.GET)
    public String toPermissionToRole(@PathVariable Integer id, ModelMap model) {
        model.put("id",id);
        return "/shiro-role-permission";
    }

    @ResponseBody
    @RequestMapping(value = "/role",method = RequestMethod.GET)
    private HashMap<String,Object> getRolesForPage(EntityPage<RoleBO> page){
        HashMap<String,Object> map = new HashMap<>();
        map.put("roleInfo",roleService.getRolesForPage(page));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/allrole",method = RequestMethod.GET)
    private HashMap<String,Object> getRoles(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("roleInfo",roleService.getRoles());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/rolebyid",method = RequestMethod.GET)
    private HashMap<String,Object> getRoleById(Integer id){
        HashMap<String,Object> map = new HashMap<>();
        RoleBO roleBO = roleService.getRoleBOById(id);
       map.put("roleInfo",roleBO);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/role",method = RequestMethod.DELETE)
    private HashMap<String,Object> delRoleById(Integer id){
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(roleService.delRoleById(id)){
            returnValue="删除成功！！！";
        }else {
            returnValue="删除失败！！！";
        }
        map.put("delInfo",returnValue);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/role",method = RequestMethod.POST)
    private HashMap<String,Object> addRole(Role role){
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(roleService.addRole(role)){
            returnValue="新增成功！！！";
        }else {
            returnValue="新增失败！！！";
        }
        map.put("addInfo",returnValue);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/role",method = RequestMethod.PUT)
    private HashMap<String,Object> updRole(Role role){
        System.out.println("------------------adfd");
        List<Permission> permissionList = roleService.getRoleById(role.getId()).getPermissionList();
        List<User> userList = roleService.getRoleById(role.getId()).getUserList();
        role.setPermissionList(permissionList);
        role.setUserList(userList);
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(roleService.addRole(role)){
            returnValue="修改成功！！！";
        }else {
            returnValue="修改失败！！！";
        }
        map.put("updInfo",returnValue);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/permissiontorole",method = RequestMethod.PUT)
    private HashMap<String,Object> permissionToRole(IdToIds idToIds){
        HashMap<String,Object> map = new HashMap<>();
        Role role = roleService.getRoleById(idToIds.getId());
        role.setPermissionList(permissionService.getPermissionsById(idToIds.getIds()));
        String returnValue;
        if(roleService.addRole(role)){
            returnValue="修改成功！！！";
        }else {
            returnValue="修改失败！！！";
        }
        map.put("updInfo",returnValue);
        return map;
    }
}
