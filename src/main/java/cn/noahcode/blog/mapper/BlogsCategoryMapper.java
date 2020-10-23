package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.BlogsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Mapper
public interface BlogsCategoryMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(BlogsCategory record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(BlogsCategory record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    BlogsCategory selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BlogsCategory record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BlogsCategory record);

    List<BlogsCategory> selectByBlogId(Integer blogId);
}