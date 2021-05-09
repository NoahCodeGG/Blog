package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.LinkMapper;
import cn.noahcode.blog.model.entity.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Service
public class LinkServiceImpl implements LinkService{

    @Resource
    private LinkMapper linkMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return linkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Link record) {
        return linkMapper.insert(record);
    }

    @Override
    public int insertSelective(Link record) {
        return linkMapper.insertSelective(record);
    }

    @Override
    public Link selectByPrimaryKey(Integer id) {
        return linkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Link record) {
        return linkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Link record) {
        return linkMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Link> listLink() {
        return linkMapper.listLink();
    }

    @Override
    public Link selectByName(String name) {
        return linkMapper.selectByName(name);
    }

}
