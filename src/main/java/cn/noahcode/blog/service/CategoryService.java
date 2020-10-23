package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Category;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
public interface CategoryService {


    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category selectByPrimaryKey(Integer id);

    Category selectByName(String name);

    List<Category> selectByBlogId(Integer blogId);

    List<Category> listCategory();
}
