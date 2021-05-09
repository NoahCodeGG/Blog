package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.PostMapper;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.model.params.PostQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return postMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Post record) {
        return postMapper.insert(record);
    }

    @Override
    public int insertSelective(Post record) {
        return postMapper.insertSelective(record);
    }

    @Override
    public Post selectByPrimaryKey(Long id) {
        return postMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Post record) {
        return postMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Post record) {
        return postMapper.updateByPrimaryKey(record);
    }

    @Override
    public int toRecycle(Long id) {
        return postMapper.toRecycle(id);
    }

    @Override
    public int toReduction(Long id) {
        return postMapper.toReduction(id);
    }

    @Override
    public int blogCount() {
        return postMapper.blogCount();
    }

    @Override
    public int visitCount() {
        return postMapper.visitCount();
    }

    @Override
    public List<Post> listBlog() {
        return postMapper.listBlog();
    }

    @Override
    public List<Post> listNewBlog() {
        return postMapper.listNewBlog();
    }

    @Override
    public List<Post> listPublishedBlog() {
        return postMapper.listPublishedBlog();
    }

    @Override
    public int addVisitor(Long id) {
        return postMapper.addVisitor(id);
    }

    @Override
    public List<Post> listPage() {
        return postMapper.listPage();
    }

    @Override
    public Integer[] listYear() {
        return postMapper.listYear();
    }

    @Override
    public Integer[] listMouth(Integer year) {
        return postMapper.listMouth(year);
    }

    @Override
    public List<Post> listMouthArchive(Integer year, Integer mouth) {
        return postMapper.listMouthArchive(year, mouth);
    }

    @Override
    public Post selectBySlug(String slug) {
        return postMapper.selectBySlug(slug);
    }

    @Override
    public List<Post> listQueryBlog(PostQuery postQuery) {
        return postMapper.listQueryBlog(postQuery);
    }

}




