package cn.mrbcy.sound.domain;

import cn.mrbcy.sound.SoundApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2018/7/8.
 */
@SpringBootTest(classes = {SoundApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserJpaRepositoryTest {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private SysRoleJpaRepository sysRoleJpaRepository;

    @Test
    public void testSave(){
        User user = new User("admin","admin","admin123", 0);
        User newUser = userJpaRepository.save(user);

        Assert.assertEquals(user.getId(),newUser.getId());
        Assert.assertEquals(user.getUserName(),newUser.getUserName());
        Assert.assertEquals(user.getNickName(),newUser.getNickName());
        Assert.assertEquals(user.getPassword(),newUser.getPassword());
        Assert.assertEquals(user.getState(),newUser.getState());
        Assert.assertEquals(user.getRoles(),newUser.getRoles());

    }

    @Test
    public void testSaveWithRoles(){
        User user = new User("admin","admin","admin123", 0);
        List<SysRole> roles = new ArrayList<>();
        roles.add(new SysRole("user","用户","普通用户",false));
        roles.add(new SysRole("operator","资源管理员","管理标签和声音资源",false));
        roles.add(new SysRole("admin","平台管理员","管理用户及平台资源",false));

        user.setRoles(roles);

        userJpaRepository.save(user);

        User user2 = userJpaRepository.getUserByName("admin");
        List<SysRole> qRoles = user2.getRoles();
        Assert.assertEquals(qRoles.size(), 3);
        Assert.assertEquals(user2.getPassword(),"admin123");
    }
}
