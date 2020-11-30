package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.BlogsCategory;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
public interface BlogsCategoryService {


    int deleteByPrimaryKey(Integer id);

    int deleteByBlogId(Integer blogId);

    int insert(BlogsCategory record);

    int insertSelective(BlogsCategory record);

    BlogsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogsCategory record);

    int updateByPrimaryKey(BlogsCategory record);

    List<BlogsCategory> selectByBlogId(Integer blogId);

}
