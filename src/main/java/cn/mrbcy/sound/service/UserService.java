package cn.mrbcy.sound.service;

import cn.mrbcy.sound.domain.User;

/**
 * Created by Yang on 2018/7/14.
 */
public interface UserService {
    /**
     * 根据username查找用户
     * @param userName 用户名
     * @return 如果找不到，返回null
     */
    User findUserByName(String userName);

    /**
     * 将签发的token保存起来
     * @param userName
     * @param token
     */
    void saveToken(String userName, String token);

    /**
     * 检查token有没有被收回，被收回则返回FALSE
     * @param userName
     * @param token
     * @return
     */
    boolean isTokenValid(String userName, String token);

    /**
     * 废除某个用户的所有token
     * @param userName
     */
    void invalidToken(String userName);
}
