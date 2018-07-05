package cn.mrbcy.sound.controller;

import cn.mrbcy.sound.domain.Tag;
import cn.mrbcy.sound.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yang on 2018/7/5.
 */
@RestController
@RequestMapping(value = "/api/v1/tags")
public class TagController extends AbstractRestHandler{
    @Autowired
    private TagService tagService;

    @GetMapping(value = "")
    public Page<Tag> getAllTag(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                               @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                               HttpServletRequest request, HttpServletResponse response){
        return tagService.getAllTags(page, size);
    }
}
