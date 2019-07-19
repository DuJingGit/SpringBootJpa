package com.itmayiedu.entity.second;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Table(name="u_role")
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "role")
    private String rolename;
    @ManyToMany(mappedBy = "roleList",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("roleList")
    private List<User> userList;
    @ManyToMany
    @JoinTable(name = "u_role_permission",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + rolename + '\'' +
                '}';
    }
}
