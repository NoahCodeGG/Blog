package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Categories;
    /**
 * @author NoahCode
 * @date 11/29/2020
 * @description
 */
public interface CategoriesService{


    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    int insertSelective(Categories record);

    Categories selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);

}
