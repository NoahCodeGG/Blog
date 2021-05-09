package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.params.CommentQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/4/2021
 * @description
 */
@Mapper
public interface CommentMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Comment record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Comment record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Comment selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Comment record);

    List<Comment> listBlogComments();

    List<Comment> listPageComments();

    int toAdopt(Long id);

    int toRecycle(Long id);

    int toReduction(Long id);

    int commentCount();

    List<Comment> listNewBlogComment();

    List<Comment> listNewPageComment();

    Long selectByPostIdCount(Long postId);

    List<Comment> selectBlogCommentsByPostId(Long postId);

    List<Comment> selectPageCommentsByPostId(Long id);

    List<Comment> listQueryBlogComments(CommentQuery commentQuery);

    List<Comment> listQueryPageComments(CommentQuery commentQuery);

    void deleteByPostId(Long id);
}