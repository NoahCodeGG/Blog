package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.PostsTag;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
public interface PostsTagService {


    int deleteByPrimaryKey(Long id);

    int insert(PostsTag record);

    int insertSelective(PostsTag record);

    PostsTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostsTag record);

    int updateByPrimaryKey(PostsTag record);

    List<PostsTag> selectByBlogId(Long blogId);

    int deleteByBlogId(Long blogId);

    int deleteByTagId(Long tagId);

}

