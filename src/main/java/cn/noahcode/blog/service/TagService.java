package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Tag;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
public interface TagService{


    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    Tag selectByPrimaryKey(Integer id);

    Tag selectByName(String name);

    List<Tag> selectByBlogId(Integer blogId);

    List<Tag> listTags();
}
