package com.itmayiedu.entity.second;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Table(name="u_permission")
@Entity
public class Permission {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "permission")
    private String permissionname;
    private String url;
    private String method;
    @ManyToMany(mappedBy = "permissionList",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("permissionList")
    private List<Role> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permissionname + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
