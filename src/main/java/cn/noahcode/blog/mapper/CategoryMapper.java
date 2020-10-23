package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
@Mapper
public interface CategoryMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Category record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Category record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Category record);

    Category selectByName(String name);

    List<Category> listCategory();

    List<Category> selectByBlogId(Integer blogId);
}