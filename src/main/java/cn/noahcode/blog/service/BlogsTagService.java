package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.BlogsTag;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
public interface BlogsTagService {


    int deleteByPrimaryKey(Integer id);

    int insert(BlogsTag record);

    int insertSelective(BlogsTag record);

    BlogsTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogsTag record);

    int updateByPrimaryKey(BlogsTag record);

    List<BlogsTag> selectByBlogId(Integer blogId);
}
