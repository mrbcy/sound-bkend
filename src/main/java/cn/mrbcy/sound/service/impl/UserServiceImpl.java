package cn.mrbcy.sound.service.impl;

import cn.mrbcy.sound.domain.User;
import cn.mrbcy.sound.domain.UserJpaRepository;
import cn.mrbcy.sound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yang on 2018/7/14.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User findUserByName(String userName) {
        if(userName == null || "".equals(userName)){
            return null;
        }

        return userJpaRepository.getUserByName(userName);
    }
}
