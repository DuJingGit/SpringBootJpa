package com.itmayiedu.entity.second;

import java.util.List;

public class UserBO {
    private Integer id=1;
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String headurl;
    private List<RoleBO> roleList;// 一个用户具有多个角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleBO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleBO> roleList) {
        this.roleList = roleList;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
