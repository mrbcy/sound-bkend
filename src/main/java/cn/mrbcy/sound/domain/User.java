package cn.mrbcy.sound.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2018/7/7.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String userName; //用户名
    private String nickName; //昵称
    private String password; //密码
    private int state = 0;   //状态 0-正常 1-锁定

    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
    private List<SysRole> roles;

    public User(){

    }

    public User(String userName, String nickName, String password, int state) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public void addRole(SysRole role){
        if(this.roles == null){
            this.roles = new ArrayList<>();
        }
        roles.add(role);
    }
}
