package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.CategoryMapper;
import cn.noahcode.blog.model.entity.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    @Override
    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Category selectByName(String name) {
        return categoryMapper.selectByName(name);
    }

    @Override
    public List<Category> selectByBlogId(Integer blogId) {
        return categoryMapper.selectByBlogId(blogId);
    }

    @Override
    public List<Category> listCategory() {
        return categoryMapper.listCategory();
    }

}
