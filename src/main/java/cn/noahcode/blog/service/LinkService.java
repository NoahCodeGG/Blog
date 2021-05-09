package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Link;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
public interface LinkService{


    int deleteByPrimaryKey(Integer id);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);

    List<Link> listLink();

    Link selectByName(String name);
}
