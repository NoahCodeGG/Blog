package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Categories;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author NoahCode
 * @date 11/29/2020
 * @description
 */
@Mapper
public interface CategoriesMapper {
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
    int insert(Categories record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Categories record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Categories selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Categories record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Categories record);
}