package cn.mrbcy.sound.service.impl;

import cn.mrbcy.sound.domain.Tag;
import cn.mrbcy.sound.domain.TagJpaRepository;
import cn.mrbcy.sound.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yang on 2018/6/24.
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagJpaRepository tagJpaRepository;

    @Override
    public List<Tag> findAll() {
        return tagJpaRepository.findAll();
    }

    @Override
    public Tag findTagById(long id) {
        return tagJpaRepository.getOne(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagJpaRepository.save(tag);
    }

    @Override
    public void deleteTag(long id) {
        tagJpaRepository.deleteById(id);
    }

    @Override
    public List<Tag> findTagLikeName(String tagName) {
        return tagJpaRepository.findTagLikeName(tagName);
    }

    @Override
    public Page<Tag> getAllTags(Integer page, Integer size) {
        return tagJpaRepository.findAll(PageRequest.of(page,size));
    }
}
