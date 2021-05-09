package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.PostsCategoryMapper;
import cn.noahcode.blog.model.entity.PostsCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
@Service
public class PostsCategoryServiceImpl implements PostsCategoryService {

    @Resource
    private PostsCategoryMapper postsCategoryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return postsCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PostsCategory record) {
        return postsCategoryMapper.insert(record);
    }

    @Override
    public int insertSelective(PostsCategory record) {
        return postsCategoryMapper.insertSelective(record);
    }

    @Override
    public PostsCategory selectByPrimaryKey(Long id) {
        return postsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PostsCategory record) {
        return postsCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PostsCategory record) {
        return postsCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PostsCategory> selectByBlogId(Long blogId) {
        return postsCategoryMapper.selectByBlogId(blogId);
    }

    @Override
    public int deleteByCategoryId(Long categoryId) {
        return postsCategoryMapper.deleteByCategoryId(categoryId);
    }

    @Override
    public Long countByCategoryId(Long categoryId) {
        return postsCategoryMapper.countByCategoryId(categoryId);
    }

    @Override
    public int deleteByBlogId(Long blogId) {
        return postsCategoryMapper.deleteByBlogId(blogId);
    }

}

