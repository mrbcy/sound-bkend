package cn.mrbcy.sound.auth;


import cn.mrbcy.sound.domain.SysPermission;
import cn.mrbcy.sound.domain.SysRole;
import cn.mrbcy.sound.domain.User;
import cn.mrbcy.sound.service.UserService;
import cn.mrbcy.sound.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yang on 2018/7/14.
 */
@Service
public class MyRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        User user = userService.findUserByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        for(SysRole role : user.getRoles()){
            simpleAuthorizationInfo.addRole(role.getRole());
            // 添加权限
            for(SysPermission permission : role.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.findUserByName(username);
        if(user == null){
            throw new AuthenticationException("Username or password error");
        }

        // 验证token
        if(!JWTUtil.verify(token, username, user.getPassword())){
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "myrealm");
    }
}
