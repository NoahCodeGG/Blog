package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Menu;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author NoahCode
 * @date 1/16/2021
 * @description
 */
@Mapper
public interface MenuMapper {
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
    int insert(Menu record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Menu record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Menu selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Menu record);

    List<Menu> listPages(@Param("team") String team);

    List<String> listTeams();

    int deleteByTeam(String team);
}