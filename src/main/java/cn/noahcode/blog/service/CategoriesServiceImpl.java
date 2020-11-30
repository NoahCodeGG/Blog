package cn.noahcode.blog.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.noahcode.blog.mapper.CategoriesMapper;
import cn.noahcode.blog.model.entity.Categories;
import cn.noahcode.blog.service.CategoriesService;
/**
 * @author NoahCode
 * @date 11/29/2020
 * @description
 */
@Service
public class CategoriesServiceImpl implements CategoriesService{

    @Resource
    private CategoriesMapper categoriesMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return categoriesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Categories record) {
        return categoriesMapper.insert(record);
    }

    @Override
    public int insertSelective(Categories record) {
        return categoriesMapper.insertSelective(record);
    }

    @Override
    public Categories selectByPrimaryKey(Integer id) {
        return categoriesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Categories record) {
        return categoriesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Categories record) {
        return categoriesMapper.updateByPrimaryKey(record);
    }

}
