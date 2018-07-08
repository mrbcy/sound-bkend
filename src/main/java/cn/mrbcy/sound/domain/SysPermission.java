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
    private int id;
    @Column(unique = true)
    private String name; //权限名
    @ManyToMany
    @JoinTable(name="SysRolePermission", joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> roles;

    public SysPermission(){

    }

    public SysPermission(String name, List<SysRole> roles) {
        this.name = name;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
