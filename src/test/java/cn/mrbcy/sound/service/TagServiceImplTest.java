package cn.mrbcy.sound.service;

import cn.mrbcy.sound.SoundApplication;
import cn.mrbcy.sound.domain.Tag;
import cn.mrbcy.sound.service.impl.TagServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yang on 2018/6/24.
 */
@SpringBootTest(classes = {SoundApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TagServiceImplTest {
    @Autowired
    private TagServiceImpl tagService;

    @Test
    public void testSave(){
        Tag testTag = new Tag("风","/images/tag/wind.png","风中感受自然");
        Tag newTag = tagService.saveTag(testTag);
        Assert.assertEquals(testTag.getName(), newTag.getName());
        Assert.assertEquals(testTag.getPicUrl(), newTag.getPicUrl());
        Assert.assertEquals(
                testTag.getDescription(), newTag.getDescription());
    }

    @Test
    public void testFindTagLikeName(){
        tagService.saveTag(new Tag("风","testPic","testDesc"));
        tagService.saveTag(new Tag("小风","testPic2","testDesc2"));
        tagService.saveTag(new Tag("风暴","testPic3","testDesc3"));
        tagService.saveTag(new Tag("空","testPic4","testDesc4"));
        List<Tag> tags = tagService.findTagLikeName("风");
        Assert.assertEquals(3, tags.size());
    }

    @Test
    public void testFindOne(){
        Tag testTag = new Tag("风","/images/tag/wind.png","风中感受自然");
        Tag newTag = tagService.saveTag(testTag);
        newTag = tagService.findTagById(newTag.getId());
        Assert.assertEquals(testTag.getName(), newTag.getName());
        Assert.assertEquals(testTag.getPicUrl(), newTag.getPicUrl());
        Assert.assertEquals(
                testTag.getDescription(), newTag.getDescription());
    }

    @Test
    public void testFindAll(){
        tagService.saveTag(new Tag("风","testPic","testDesc"));
        tagService.saveTag(new Tag("小风","testPic2","testDesc2"));
        tagService.saveTag(new Tag("风暴","testPic3","testDesc3"));
        tagService.saveTag(new Tag("空","testPic4","testDesc4"));
        List<Tag> tags = tagService.findAll();
        Assert.assertEquals(4, tags.size());
    }
}
