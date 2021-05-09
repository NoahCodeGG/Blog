package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.OptionMapper;
import cn.noahcode.blog.model.entity.Option;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author NoahCode
 * @date 12/28/2020
 * @description
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Resource
    private OptionMapper optionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return optionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Option record) {
        return optionMapper.insert(record);
    }

    @Override
    public int insertSelective(Option record) {
        return optionMapper.insertSelective(record);
    }

    @Override
    public Option selectByPrimaryKey(Integer id) {
        return optionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Option record) {
        return optionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Option record) {
        return optionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, String>> listOption() {
        return optionMapper.listOption();
    }

    @Override
    public Option selectByOptionKey(String optionKey) {
        return optionMapper.selectByOptionKey(optionKey);
    }

    @Override
    public int updateByOptionKey(Option option) {
        return optionMapper.updateByOptionKey(option);
    }

}
