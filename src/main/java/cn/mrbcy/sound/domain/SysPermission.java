package cn.mrbcy.sound.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yang on 2018/7/7.
 */
@Entity
public class SysPermission {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String permission; //权限名
    private String permissionName; //权限名（中文）

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="SysRolePermission", joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> roles;

    public SysPermission(){

    }

    public SysPermission(String permission, String permissionName) {
        this.permission = permission;
        this.permissionName = permissionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
