package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.BlogsTagMapper;
import cn.noahcode.blog.model.entity.BlogsTag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Service
public class BlogsTagServiceImpl implements BlogsTagService {

    @Resource
    private BlogsTagMapper blogsTagMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogsTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogsTag record) {
        return blogsTagMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogsTag record) {
        return blogsTagMapper.insertSelective(record);
    }

    @Override
    public BlogsTag selectByPrimaryKey(Integer id) {
        return blogsTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlogsTag record) {
        return blogsTagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlogsTag record) {
        return blogsTagMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BlogsTag> selectByBlogId(Integer blogId) {
        return blogsTagMapper.selectById(blogId);
    }

    @Override
    public int deleteByBlogId(Integer blogId) {
        return blogsTagMapper.deleteByBlogId(blogId);
    }

}
