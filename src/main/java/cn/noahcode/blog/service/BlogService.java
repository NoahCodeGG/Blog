package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Blog;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
public interface BlogService {


    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    Blog selectByPrimaryKey(Integer id);

    List<Blog> listBlog();

    int toRecycle(Integer id);
}












