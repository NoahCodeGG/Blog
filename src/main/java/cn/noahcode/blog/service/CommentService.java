package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.params.CommentQuery;

import java.util.List;

/**
 * @author NoahCode
 * @date 12/29/2020
 * @description
 */
public interface CommentService {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> listBlogComments();

    List<Comment> listPageComments();

    int toAdopt(Long id);

    int toRecycle(Long id);

    int toReduction(Long id);

    int commentCount();

    List<Comment> listNewBlogComment();

    List<Comment> listNewPageComment();

    Long selectByPostIdBlogComments(Long postId);

    List<Comment> selectBlogCommentsByPostId(Long postId);

    List<Comment> selectPageCommentsByPostId(Long id);

    List<Comment> listQueryBlogComments(CommentQuery commentQuery);

    List<Comment> listQueryPageComments(CommentQuery commentQuery);

    void deleteByPostId(Long id);
}




