package com.itmayiedu.service;

import com.itmayiedu.entity.second.EntityPage;
import com.itmayiedu.entity.second.Role;
import com.itmayiedu.entity.second.RoleBO;

import java.util.List;

public interface RoleService {

    /**
     *获取所以有效role
     * @return
     */
    EntityPage<RoleBO> getRolesForPage(EntityPage<RoleBO> page);

    boolean delRoleById(Integer id);

    boolean addRole(Role role);

    List<RoleBO> getRolesById(Iterable<Integer> ids);

    List<RoleBO> getRoles();

    RoleBO getRoleBOById(Integer id);

    Role getRoleById(Integer id);

}
