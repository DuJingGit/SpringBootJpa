package com.itmayiedu.controller;


import com.itmayiedu.entity.second.*;
import com.itmayiedu.realm.AES;
import com.itmayiedu.service.RoleService;
import com.itmayiedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
//    @RequiresPermissions(value = {"/user/users"})
    public String toUser() {
        return "/shiro-users";
    }

    @RequestMapping(value = "/adduser",method = RequestMethod.GET)
    public String toAddUser(ModelMap model) {
        model.put("addInfo",2);
        return "/shiro-add-user";
    }

    @RequestMapping(value = "/upduser/{id}",method = RequestMethod.GET)
    public String toUpdUser(@PathVariable Integer id, ModelMap model)
    {
        System.out.println(id+"----id'");
        model.put("id",id);
        return "/shiro-upd-user";
    }

    @ResponseBody
    @RequestMapping(value = "/userbyid",method = RequestMethod.GET)
    private HashMap<String,Object> getUserById(Integer id){
        HashMap<String,Object> map = new HashMap<>();
        map.put("userInfo",userService.getUserBOById(id));
        return map;
    }

    @RequestMapping(value = "/toroletouser/{id}",method = RequestMethod.GET)
    public String toRoleToUser(@PathVariable Integer id, ModelMap model) {
        model.put("id",id);
        return "/shiro-user-role";
    }


    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    private HashMap<String,Object> getUsersForPage(EntityPage<UserBO> page){
        System.out.println("userController--------");
        HashMap<String,Object> map = new HashMap<>();
//        Page<User> page1 = new Page<>();
        map.put("userInfo",userService.getUsersForPage(page));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    private HashMap<String,Object> delUserById(Integer id){
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(userService.delUserById(id)){
            returnValue="删除成功！！！";
        }else {
            returnValue="删除失败！！！";
        }
        map.put("delInfo",returnValue);
        return map;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    private String addUser(MultipartHttpServletRequest multiReq,User user,ModelMap model){
        // 获取上传文件的路径
        String uploadFilePath = multiReq.getFile("file").getOriginalFilename();
        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.lastIndexOf("."));
        String headname = user.getName()+uploadFileSuffix;
        String headurl = "/upload/"+headname;
        saveFile(multiReq,headname);
        user.setHeadurl(headurl);
        int returnValue;
        String cKey = "1234567890123456";
        try {
            user.setPassword(AES.Encrypt(user.getPassword(),cKey));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userService.addUser(user)){
            returnValue=1;
        }else {
            returnValue=0;
        }
        model.put("addInfo",returnValue);
        return "/shiro-add-user";
    }

    @RequestMapping(value = "/userfile",method = RequestMethod.POST)
    private String updUser(MultipartHttpServletRequest multiReq, User user,ModelMap model){
        // 获取上传文件的路径
        String uploadFilePath = multiReq.getFile("file").getOriginalFilename();
        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.lastIndexOf("."));
        String headname = user.getName()+uploadFileSuffix;
        System.out.println(headname+"-----------------");
        String headurl = "/upload/"+headname;
        saveFile(multiReq,headname);
        user.setHeadurl(headurl);
        user.setRoleList(userService.getUserById(user.getId()).getRoleList());
        String returnValue;
        String cKey = "1234567890123456";
        try {
            user.setPassword(AES.Encrypt(user.getPassword(),cKey));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userService.addUser(user)){
            returnValue="修改成功！！！";
        }else {
            returnValue="修改失败！！！";
        }
        model.put("indexInfo",returnValue);
        return "/shiro-users";
    }

    private void saveFile(MultipartHttpServletRequest multiReq,String filename) {
        String saveurl = "E:\\headImage\\"+filename;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            File file = new File(saveurl);
            if (file.exists()){
                file.delete();
            }
            fis = (FileInputStream) multiReq.getFile("file").getInputStream();
            fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1) {
                fos.write(temp, 0, temp.length);
                fos.flush();
                i = fis.read(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/roletouser",method = RequestMethod.PUT)
    private HashMap<String,Object> roleToUser(IdToIds idToIds){
        List<Integer> integerList = idToIds.getIds();
        System.out.println(idToIds.getId()+"-------------id");
        for (Integer integer : integerList) {
            System.out.println(integer+"-------------ids");
        }
        UserBO userBO = userService.getUserBOById(idToIds.getId());
        User user = new User();
        user.setId(userBO.getId());
        user.setName(userBO.getName());
        user.setHeadurl(user.getHeadurl());
        String cKey = "1234567890123456";
        try{
            user.setPassword(AES.Encrypt(userBO.getPassword(),cKey));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<RoleBO> roleBOList = roleService.getRolesById(idToIds.getIds());
        List<Role> roleList = new ArrayList<>();
        for (RoleBO roleBO : roleBOList) {
            Role role = new Role();
            role.setId(roleBO.getId());
            role.setRolename(roleBO.getRole());
            List<PermissionBO> permissionBOList = roleBO.getPermissionList();
            List<Permission> permissionList = new ArrayList<>();
            for (PermissionBO permissionBO : permissionBOList) {
                Permission permission = new Permission();
                permission.setId(permissionBO.getId());
                permission.setMethod(permissionBO.getMethod());
                permission.setPermissionname(permissionBO.getPermission());
                permission.setUrl(permissionBO.getUrl());
                permissionList.add(permission);
            }
            role.setPermissionList(permissionList);
            roleList.add(role);
        }
        user.setRoleList(roleList);
        HashMap<String,Object> map = new HashMap<>();
        String returnValue;
        if(userService.addUser(user)){
            returnValue="修改成功！！！";
        }else {
            returnValue="修改失败！！！";
        }
        map.put("updInfo",returnValue);
        return map;
    }
}
