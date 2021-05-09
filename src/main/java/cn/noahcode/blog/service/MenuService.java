package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Menu;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/6/2021
 * @description
 */
public interface MenuService {

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> listPages(String team);

    List<String> listTeams();

    int deleteByTeam(String team);
}


