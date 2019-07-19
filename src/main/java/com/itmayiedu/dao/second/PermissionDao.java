package com.itmayiedu.dao.second;

import com.itmayiedu.entity.second.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission, Integer> {

    Permission findByPermissionname(String permissionname);

}
