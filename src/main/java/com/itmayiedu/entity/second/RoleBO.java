package com.itmayiedu.entity.second;

import java.util.List;

public class RoleBO {

    private Integer id;
    private String role;
    private List<PermissionBO> permissionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PermissionBO> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionBO> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "RoleBO{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
