package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.PostsCategory;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Mapper
public interface PostsCategoryMapper {
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
    int insert(PostsCategory record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(PostsCategory record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    PostsCategory selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PostsCategory record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(PostsCategory record);

    List<PostsCategory> selectByBlogId(Long blogId);

    int deleteByBlogId(Long blogId);

    int deleteByCategoryId(Long categoryId);

    Long countByCategoryId(Long categoryId);
}