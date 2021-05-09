package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.CommentMapper;
import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.params.CommentQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 12/29/2020
 * @description
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Comment> listBlogComments() {
        return commentMapper.listBlogComments();
    }

    @Override
    public List<Comment> listPageComments() {
        return commentMapper.listPageComments();
    }

    @Override
    public int toAdopt(Long id) {
        return commentMapper.toAdopt(id);
    }

    @Override
    public int toRecycle(Long id) {
        return commentMapper.toRecycle(id);
    }

    @Override
    public int toReduction(Long id) {
        return commentMapper.toReduction(id);
    }

    @Override
    public int commentCount() {
        return commentMapper.commentCount();
    }

    @Override
    public List<Comment> listNewBlogComment() {
        return commentMapper.listNewBlogComment();
    }

    @Override
    public List<Comment> listNewPageComment() {
        return commentMapper.listNewPageComment();
    }

    @Override
    public Long selectByPostIdBlogComments(Long postId) {
        return commentMapper.selectByPostIdCount(postId);
    }

    @Override
    public List<Comment> selectBlogCommentsByPostId(Long postId) {
        return commentMapper.selectBlogCommentsByPostId(postId);
    }

    @Override
    public List<Comment> selectPageCommentsByPostId(Long id) {
        return commentMapper.selectPageCommentsByPostId(id);
    }

    @Override
    public List<Comment> listQueryBlogComments(CommentQuery commentQuery) {
        return commentMapper.listQueryBlogComments(commentQuery);
    }

    @Override
    public List<Comment> listQueryPageComments(CommentQuery commentQuery) {
        return commentMapper.listQueryPageComments(commentQuery);
    }

    @Override
    public void deleteByPostId(Long id) {
        commentMapper.deleteByPostId(id);
    }

}




