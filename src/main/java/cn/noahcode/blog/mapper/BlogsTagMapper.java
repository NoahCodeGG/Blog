package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.BlogsTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Mapper
public interface BlogsTagMapper {
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
    int insert(BlogsTag record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(BlogsTag record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    BlogsTag selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BlogsTag record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BlogsTag record);

    List<BlogsTag> selectById(Integer blogId);
}