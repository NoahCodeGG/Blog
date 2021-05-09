package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Mapper
public interface LinkMapper {
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
    int insert(Link record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Link record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Link selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Link record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Link record);

    List<Link> listLink();

    Link selectByName(String name);
}