package com.itmayiedu.service;

import com.itmayiedu.entity.second.EntityPage;
import com.itmayiedu.entity.second.Permission;
import com.itmayiedu.entity.second.PermissionBO;

import java.util.List;

public interface PermissionService {

    EntityPage<PermissionBO> getPermissionsForPage(EntityPage<PermissionBO> page);

    Permission getPermission(String permission);

    List<Permission> getPermissions();

    boolean delPermissionById(Integer id);

    boolean addPermission(Permission permission);

    List<Permission> getPermissionsById(Iterable<Integer> ids);

    PermissionBO getPermissionBOById(Integer id);

    Permission getPermissionById(Integer id);
}
