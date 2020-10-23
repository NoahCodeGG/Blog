package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
@Mapper
public interface UserMapper {
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
    int insert(User record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(User record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    User selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(User record);

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updateUser(@Param("user") User user, @Param("id") Integer id);

    int updatePassword(@Param("newPassword") String newPassword, @Param("id") Integer id);
}