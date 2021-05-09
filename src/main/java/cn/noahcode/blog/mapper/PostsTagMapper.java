package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.PostsTag;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Mapper
public interface PostsTagMapper {
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
    int insert(PostsTag record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(PostsTag record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    PostsTag selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PostsTag record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(PostsTag record);

    List<PostsTag> selectById(Long blogId);

    int deleteByBlogId(Long blogId);

    int deleteByTagId(Long tagId);
}