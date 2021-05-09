package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Journal;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
public interface JournalService {


    int deleteByPrimaryKey(Integer id);

    int insert(Journal record);

    int insertSelective(Journal record);

    Journal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Journal record);

    int updateByPrimaryKey(Journal record);

    List<Journal> listJournal();
}
