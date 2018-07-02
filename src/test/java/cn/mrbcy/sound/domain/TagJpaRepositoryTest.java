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
 * Created by Yang on 2018/7/3.
 */
@SpringBootTest(classes = {SoundApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
/**
 * 只测试自己扩展的方法和映射关系，Spring生成的方法默认没有什么问题
 */
@Transactional
public class TagJpaRepositoryTest {
    @Autowired
    private TagJpaRepository repository;

    /**
     *
     */
    @Test
    public void testTagLikeName(){
        repository.save(new Tag("风","testPic","testDesc"));
        repository.save(new Tag("小风","testPic2","testDesc2"));
        repository.save(new Tag("风暴","testPic3","testDesc3"));
        repository.save(new Tag("空","testPic4","testDesc4"));
        List<Tag> tags = repository.findTagLikeName("风");
        Assert.assertEquals(3, tags.size());
    }
}
