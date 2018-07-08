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
public class SysRoleJpaRepositoryTest {
    @Autowired
    private SysRoleJpaRepository sysRoleJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void testSave(){
        SysRole role = new SysRole("user", "用户", "普通的注册用户", false);
        SysRole newRole = sysRoleJpaRepository.save(role);

        Assert.assertEquals(role.getId(), newRole.getId());
        Assert.assertEquals(role.getDescription(), newRole.getDescription());
        Assert.assertEquals(role.getRole(), newRole.getRole());
        Assert.assertEquals(role.getRoleName(), newRole.getRoleName());
    }

    @Test
    public void testFindAll(){
        List<SysRole> roles = sysRoleJpaRepository.findAll();
        Assert.assertEquals(0, roles.size());

        sysRoleJpaRepository.save(new SysRole("user", "用户", "普通的注册用户", false));
        sysRoleJpaRepository.save(new SysRole("admin", "管理员", "平台管理员用户", false));
        sysRoleJpaRepository.save(new SysRole("operator", "操作员", "可以管理平台资源的用户", false));

        roles = sysRoleJpaRepository.findAll();
        Assert.assertEquals(3, roles.size());
    }

    @Test
    public void testSaveRoleWithPermission(){
        SysRole role = new SysRole("admin","管理员","平台管理员", false);
        List<SysPermission> permissions = new ArrayList<>();
        permissions.add(new SysPermission("sound:view","查询声音"));
        permissions.add(new SysPermission("sound:add","添加声音"));
        permissions.add(new SysPermission("sound:edit","编辑声音"));
        permissions.add(new SysPermission("sound:delete","删除声音"));

        permissions.add(new SysPermission("user:view","查询用户"));
        permissions.add(new SysPermission("user:add","添加用户"));
        permissions.add(new SysPermission("user:edit","编辑用户"));
        permissions.add(new SysPermission("user:delete","删除用户"));

        role.setPermissions(permissions);

        sysRoleJpaRepository.save(role);

        SysRole qRole = sysRoleJpaRepository.getOne(role.getId());
        Assert.assertEquals(qRole.getPermissions().size(), 8);


    }

    @Test
    public void saveRoleWithUser(){
        SysRole role = new SysRole("admin","管理员","平台管理员", false);

        List<User> users = new ArrayList<>();
        users.add(new User("admin","admin","admin123", 0));
        users.add(new User("admin2","admin2","admin123", 0));
        users.add(new User("admin3","admin3","admin123", 0));

        role.setUsers(users);
        sysRoleJpaRepository.save(role);

        User user = userJpaRepository.getUserByName("admin");
        Assert.assertEquals(user.getUserName(),"admin");
    }
}
