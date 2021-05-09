package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Journal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Mapper
public interface JournalMapper {
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
    int insert(Journal record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Journal record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Journal selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Journal record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Journal record);

    List<Journal> listJournal();
}