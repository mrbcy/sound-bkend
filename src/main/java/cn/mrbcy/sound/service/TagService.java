package cn.mrbcy.sound.service;

import cn.mrbcy.sound.domain.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Yang on 2018/6/24.
 */
public interface TagService {
    public List<Tag> findAll();

    public Tag findTagById(long id);

    public Tag saveTag(Tag tag);

    public void deleteTag(long id);

    /**
     * 模糊搜索标签，满足%tagName%的所有标签会被返回
     * @param tagName
     * @return
     */
    public List<Tag> findTagLikeName(String tagName);

    /**
     * 根据分页查询结果返回所有的标签
     * @param page 分页数
     * @param size 每页的条数
     * @return 分页查询结果对象
     */
    public Page<Tag> getAllTags(Integer page, Integer size);
}
