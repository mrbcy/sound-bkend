package cn.mrbcy.sound.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yang on 2018/7/7.
 */
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String role; //角色名，英文
    private String roleName; //角色名，中文
    private String description; //描述
    private boolean disable = false; //是否禁用，禁用后不能再添加用户

    //角色-权限关系
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="SysRolePermission", joinColumns = {@JoinColumn(name="roleId")}, inverseJoinColumns = {@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="SysUserRole", joinColumns = {@JoinColumn(name="roleId")}, inverseJoinColumns = {@JoinColumn(name="userId")})
    private List<User> users;

    public SysRole(){

    }

    public SysRole(String role, String roleName, String description, boolean disable) {
        this.role = role;
        this.roleName = roleName;
        this.description = description;
        this.disable = disable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
