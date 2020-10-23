package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.UserMapper;
import cn.noahcode.blog.model.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author NoahCode
 * @date 2020/9/27
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateUser(User user, Integer id) {
        return userMapper.updateUser(user, id);
    }

    @Override
    public int updatePassword(String newPassword, Integer id) {
        return userMapper.updatePassword(newPassword, id);
    }

}

