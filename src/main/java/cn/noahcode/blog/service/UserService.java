package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.User;

/**
 * @author NoahCode
 * @date 2020/9/26
 * @description
 */
public interface UserService {

    User checkUser(String username, String password);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateUser(User user, Integer id);

    int updatePassword(String newPassword, Integer id);
}


