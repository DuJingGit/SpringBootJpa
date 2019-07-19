package com.itmayiedu.service.impl;

import com.itmayiedu.dao.second.PermissionDao;
import com.itmayiedu.entity.second.EntityPage;
import com.itmayiedu.entity.second.Permission;
import com.itmayiedu.entity.second.PermissionBO;
import com.itmayiedu.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
//    @Transactional
    public EntityPage<PermissionBO> getPermissionsForPage(EntityPage<PermissionBO> page) {
        List<PermissionBO> permissionBOList = new ArrayList<>();
        List<Permission> sumpermissionList = permissionDao.findAll();
        Integer pageSize = page.getPageSize();
        Double pageNumberDouble = Math.floor(page.getPageNumber()/pageSize);
        Integer pageNumber = pageNumberDouble.intValue();
        System.out.println(page.getPageNumber()+"--"+page.getPageSize());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Permission> userPage = permissionDao.findAll(pageable);
        System.out.println(userPage.getSize()+"----");
        List<Permission> permissionList = userPage.getContent();
        page.setPageSum(sumpermissionList.size());
        for (Permission permission : permissionList) {
                PermissionBO permissionBO = new PermissionBO();
                permissionBO.setId(permission.getId());
                permissionBO.setMethod(permission.getMethod());
                permissionBO.setPermission(permission.getPermissionname());
                permissionBO.setUrl(permission.getUrl());
                permissionBOList.add(permissionBO);
        }
        page.setTs(permissionBOList);
        return page;
    }

    @Override
//    @Transactional
    public Permission getPermission(String permission) {
        return permissionDao.findByPermissionname(permission);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionDao.findAll();
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean delPermissionById(Integer Id) {
        try {
            permissionDao.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional(value = "transactionManagerSecond")
    @Override
    public boolean addPermission(Permission permission) {
        try {
            permissionDao.save(permission);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Permission> getPermissionsById(Iterable<Integer> ids) {
        List<Permission> permissionList = permissionDao.findAllById(ids);
        return permissionList;
    }

    @Override
    public PermissionBO getPermissionBOById(Integer id) {
        Optional<Permission> permissionOptional = permissionDao.findById(id);
        Permission permission = permissionOptional.get();
        PermissionBO permissionBO = new PermissionBO();
        permissionBO.setId(permission.getId());
        permissionBO.setMethod(permission.getMethod());
        permissionBO.setPermission(permission.getPermissionname());
        permissionBO.setUrl(permission.getUrl());
        return permissionBO;
    }

    @Override
    public Permission getPermissionById(Integer id) {
        Optional<Permission> permissionOptional = permissionDao.findById(id);
        Permission permission = permissionOptional.get();
        return permission;
    }
}
