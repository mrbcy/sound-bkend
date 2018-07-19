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
}
