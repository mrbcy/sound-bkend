package cn.mrbcy.sound.domain;

import cn.mrbcy.sound.SoundApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
}
