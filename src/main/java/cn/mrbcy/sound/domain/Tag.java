package cn.mrbcy.sound.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by Yang on 2018/6/23.
 */
@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String picUrl; // 图片地址
    private String description; // 推荐理由

    public Tag() {
    }

    public Tag(String name, String picUrl, String description) {
        this.name = name;
        this.picUrl = picUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
