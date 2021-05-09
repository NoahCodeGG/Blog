package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Option;

import java.util.List;
import java.util.Map;

/**
 * @author NoahCode
 * @date 12/28/2020
 * @description
 */
public interface OptionService {


    int deleteByPrimaryKey(Integer id);

    int insert(Option record);

    int insertSelective(Option record);

    Option selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Option record);

    int updateByPrimaryKey(Option record);

    List<Map<String, String>> listOption();

    Option selectByOptionKey(String optionKey);

    int updateByOptionKey(Option option);
}
