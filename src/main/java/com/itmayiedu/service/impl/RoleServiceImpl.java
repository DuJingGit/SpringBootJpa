package com.itmayiedu.service.impl;

import com.itmayiedu.dao.second.RoleDao;
import com.itmayiedu.entity.second.*;
import com.itmayiedu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public EntityPage<RoleBO> getRolesForPage(EntityPage<RoleBO> page) {
        List<RoleBO> roleBOList = new ArrayList<>();
        List<Role> sumroleList = roleDao.findAll();
        Integer pageSize = page.getPageSize();
        Double pageNumberDouble = Math.floor(page.getPageNumber()/pageSize);
        Integer pageNumber = pageNumberDouble.intValue();
        System.out.println(page.getPageNumber()+"--"+page.getPageSize());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Role> userPage = roleDao.findAll(pageable);
        System.out.println(userPage.getSize()+"----");
        List<Role> roleList = userPage.getContent();
        page.setPageSum(sumroleList.size());
        for (Role role : roleList) {
                RoleBO roleBO = new RoleBO();
                roleBO.setId(role.getId());
                roleBO.setRole(role.getRolename());
                roleBOList.add(roleBO);
        }
        page.setTs(roleBOList);
        return page;
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean delRoleById(Integer id) {
        try {
            roleDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean addRole(Role role) {
        try {
            roleDao.save(role);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<RoleBO> getRolesById(Iterable<Integer> ids) {
        List<Role> roleList = roleDao.findAllById(ids);
        List<RoleBO> roleBOList = new ArrayList<>();
        for (Role role : roleList) {
            RoleBO roleBO = new RoleBO();
            roleBO.setId(role.getId());
            roleBO.setRole(role.getRolename());
            List<Permission> permissionList = role.getPermissionList();
            List<PermissionBO> permissionBOList = new ArrayList<>();
            for (Permission permission : permissionList) {
                PermissionBO permissionBO = new PermissionBO();
                permissionBO.setId(permission.getId());
                permissionBO.setMethod(permission.getMethod());
                permissionBO.setPermission(permission.getPermissionname());
                permissionBO.setUrl(permission.getUrl());
                permissionBOList.add(permissionBO);
            }
            roleBO.setPermissionList(permissionBOList);
            roleBOList.add(roleBO);
        }
        return roleBOList;
    }

    @Override
    public List<RoleBO> getRoles() {
        List<Role> roleList = roleDao.findAll();
        List<RoleBO> roleBOList = new ArrayList<>();
        for (Role role : roleList) {
            RoleBO roleBO = new RoleBO();
            roleBO.setId(role.getId());
            roleBO.setRole(role.getRolename());
            List<Permission> permissionList = role.getPermissionList();
            List<PermissionBO> permissionBOList = new ArrayList<>();
            for (Permission permission : permissionList) {
                PermissionBO permissionBO = new PermissionBO();
                permissionBO.setId(permission.getId());
                permissionBO.setMethod(permission.getMethod());
                permissionBO.setPermission(permission.getPermissionname());
                permissionBO.setUrl(permission.getUrl());
                permissionBOList.add(permissionBO);
            }
            roleBO.setPermissionList(permissionBOList);
            roleBOList.add(roleBO);
        }
        for (RoleBO bo : roleBOList) {
            System.out.println(bo.toString());
        }
        return roleBOList;
    }

    @Override
    public RoleBO getRoleBOById(Integer id) {
        Optional<Role> roleOptional = roleDao.findById(id);
        Role role = roleOptional.get();
        RoleBO roleBO = new RoleBO();
        roleBO.setId(role.getId());
        roleBO.setRole(role.getRolename());
        List<Permission> permissionList = role.getPermissionList();
        List<PermissionBO> permissionBOList = new ArrayList<>();
        for (Permission permission : permissionList) {
            PermissionBO permissionBO = new PermissionBO();
            permissionBO.setId(permission.getId());
            permissionBO.setMethod(permission.getMethod());
            permissionBO.setPermission(permission.getPermissionname());
            permissionBO.setUrl(permission.getUrl());
            permissionBOList.add(permissionBO);
        }
        roleBO.setPermissionList(permissionBOList);
        return roleBO;
    }

    @Override
    public Role getRoleById(Integer id) {
        Optional<Role> roleOptional = roleDao.findById(id);
        Role role = roleOptional.get();
        return role;
    }
}
