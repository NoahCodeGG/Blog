package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.BlogsCategoryMapper;
import cn.noahcode.blog.model.entity.BlogsCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Service
public class BlogsCategoryServiceImpl implements BlogsCategoryService{

    @Resource
    private BlogsCategoryMapper blogsCategoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogsCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogsCategory record) {
        return blogsCategoryMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogsCategory record) {
        return blogsCategoryMapper.insertSelective(record);
    }

    @Override
    public BlogsCategory selectByPrimaryKey(Integer id) {
        return blogsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlogsCategory record) {
        return blogsCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlogsCategory record) {
        return blogsCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BlogsCategory> selectByBlogId(Integer blogId) {
        return blogsCategoryMapper.selectByBlogId(blogId);
    }

}
