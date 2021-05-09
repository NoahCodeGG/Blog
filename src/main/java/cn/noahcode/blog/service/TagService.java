package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Tag;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
public interface TagService {


    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    Tag selectByPrimaryKey(Long id);

    Tag selectByName(String name);

    List<Tag> selectByPostId(Long blogId);

    List<Tag> listTags();

    int tagCount();
}

