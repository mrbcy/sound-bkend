package cn.mrbcy.sound;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yang on 2018/7/24.
 */
@SpringBootTest(classes = {SoundApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

    @Test
    public void simpleValueTest() throws Exception{
        redisTemplate.opsForValue().set("testKey","testValue");
        Assert.assertEquals("testValue", redisTemplate.opsForValue().get("testKey"));
    }

    @Test
    public void setValueTest() throws Exception{
        Assert.assertEquals((long)0, (long)redisTemplate.opsForSet().size("user1"));

        // 放入一些值
        redisTemplate.opsForSet().add("user1","token1");
        redisTemplate.opsForSet().add("user1","token2");
        redisTemplate.opsForSet().add("user1","token3");
        redisTemplate.opsForSet().add("user1","token4");
        redisTemplate.opsForSet().add("user1","token5");
        redisTemplate.opsForSet().add("user1","token1");
        Assert.assertEquals((long)5, (long)redisTemplate.opsForSet().size("user1"));

        // 取出测试
        Set<String> realTokens = new HashSet<>(Arrays.asList("token1","token2","token3","token4","token5"));
        Set<String> tokens = redisTemplate.opsForSet().members("user1");
        Assert.assertTrue(realTokens.equals(tokens));

        // 删除测试
        redisTemplate.delete("user1");
        Assert.assertEquals((long)0, (long)redisTemplate.opsForSet().size("user1"));
    }
}
