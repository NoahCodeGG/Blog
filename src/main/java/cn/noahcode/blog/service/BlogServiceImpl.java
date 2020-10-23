package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.BlogMapper;
import cn.noahcode.blog.model.entity.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Blog record) {
        return blogMapper.insert(record);
    }

    @Override
    public int insertSelective(Blog record) {
        return blogMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return blogMapper.updateByPrimaryKey(record);
    }

    @Override
    public Blog selectByPrimaryKey(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Blog> listBlog() {
        return blogMapper.listBlog();
    }

}












