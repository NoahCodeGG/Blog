package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.PostsTagMapper;
import cn.noahcode.blog.model.entity.PostsTag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
@Service
public class PostsTagServiceImpl implements PostsTagService {

    @Resource
    private PostsTagMapper postsTagMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return postsTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PostsTag record) {
        return postsTagMapper.insert(record);
    }

    @Override
    public int insertSelective(PostsTag record) {
        return postsTagMapper.insertSelective(record);
    }

    @Override
    public PostsTag selectByPrimaryKey(Long id) {
        return postsTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PostsTag record) {
        return postsTagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PostsTag record) {
        return postsTagMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PostsTag> selectByBlogId(Long blogId) {
        return postsTagMapper.selectById(blogId);
    }

    @Override
    public int deleteByBlogId(Long blogId) {
        return postsTagMapper.deleteByBlogId(blogId);
    }

    @Override
    public int deleteByTagId(Long tagId) {
        return postsTagMapper.deleteByTagId(tagId);
    }

}

