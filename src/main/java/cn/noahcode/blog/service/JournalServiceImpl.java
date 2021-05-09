package cn.noahcode.blog.service;

import cn.noahcode.blog.mapper.JournalMapper;
import cn.noahcode.blog.model.entity.Journal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Service
public class JournalServiceImpl implements JournalService{

    @Resource
    private JournalMapper journalMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return journalMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Journal record) {
        return journalMapper.insert(record);
    }

    @Override
    public int insertSelective(Journal record) {
        return journalMapper.insertSelective(record);
    }

    @Override
    public Journal selectByPrimaryKey(Integer id) {
        return journalMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Journal record) {
        return journalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Journal record) {
        return journalMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Journal> listJournal() {
        return journalMapper.listJournal();
    }

}
