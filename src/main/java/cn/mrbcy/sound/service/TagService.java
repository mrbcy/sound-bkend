package cn.mrbcy.sound.service;

import cn.mrbcy.sound.domain.Tag;

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
}
