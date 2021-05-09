package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.PostsCategory;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
public interface PostsCategoryService {


    int deleteByPrimaryKey(Long id);

    int insert(PostsCategory record);

    int insertSelective(PostsCategory record);

    PostsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostsCategory record);

    int updateByPrimaryKey(PostsCategory record);

    List<PostsCategory> selectByBlogId(Long blogId);

    int deleteByCategoryId(Long categoryId);

    Long countByCategoryId(Long categoryId);

    int deleteByBlogId(Long blogId);
}

