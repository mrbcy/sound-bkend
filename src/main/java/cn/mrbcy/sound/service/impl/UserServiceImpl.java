package cn.mrbcy.sound.service.impl;

import cn.mrbcy.sound.domain.User;
import cn.mrbcy.sound.domain.UserJpaRepository;
import cn.mrbcy.sound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String REDIS_TOKEN_PREFIX = "JWT_TOKEN_REDIS_";

    @Override
    public User findUserByName(String userName) {
        if(userName == null || "".equals(userName)){
            return null;
        }

        return userJpaRepository.getUserByName(userName);
    }

    @Override
    public void saveToken(String userName, String token) {
        redisTemplate.opsForSet().add(REDIS_TOKEN_PREFIX + userName, token);
    }

    @Override
    public boolean isTokenValid(String userName, String token) {
        return redisTemplate.opsForSet().isMember(REDIS_TOKEN_PREFIX + userName, token);
    }

    @Override
    public void invalidToken(String userName) {
        redisTemplate.delete(REDIS_TOKEN_PREFIX + userName);
    }
}
