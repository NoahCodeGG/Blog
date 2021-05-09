package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Tag;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

/**
 * @author NoahCode
 * @date 12/31/2020
 * @description
 */
@Mapper
public interface TagMapper {
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
    int insert(Tag record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Tag record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Tag selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Tag record);

    Tag selectByName(String name);

    List<Tag> listTags();

    List<Tag> selectByBlogId(Long blogId);

    int tagCount();
}