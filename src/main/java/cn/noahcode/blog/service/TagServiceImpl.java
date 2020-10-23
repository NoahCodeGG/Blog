package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.TagMapper;
import cn.noahcode.blog.model.entity.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
@Service
public class TagServiceImpl implements TagService{

    @Resource
    private TagMapper tagMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Tag record) {
        return tagMapper.insert(record);
    }

    @Override
    public int insertSelective(Tag record) {
        return tagMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Tag record) {
        return tagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Tag record) {
        return tagMapper.updateByPrimaryKey(record);
    }

    @Override
    public Tag selectByPrimaryKey(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public Tag selectByName(String name) {
        return tagMapper.selectByName(name);
    }

    @Override
    public List<Tag> selectByBlogId(Integer blogId) {
        return tagMapper.selectByBlogId(blogId);
    }

    @Override
    public List<Tag> listTags() {
        return tagMapper.listTags();
    }

}
