package cn.mrbcy.sound.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by Yang on 2018/7/14.
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
