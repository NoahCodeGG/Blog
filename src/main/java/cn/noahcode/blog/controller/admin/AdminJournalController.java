package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.JournalDTO;
import cn.noahcode.blog.model.entity.Journal;
import cn.noahcode.blog.model.entity.Option;
import cn.noahcode.blog.model.enums.JournalType;
import cn.noahcode.blog.model.params.JournalParam;
import cn.noahcode.blog.service.JournalService;
import cn.noahcode.blog.service.OptionService;
import cn.noahcode.blog.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Controller
@RequestMapping("/admin/journals")
public class AdminJournalController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private JournalService journalService;

    @GetMapping("")
    public String journal(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                          @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                          Model model) {
        listJournals(model, pageNum, pageSize);
        return "admin/journals";
    }

    @GetMapping("/setting")
    public String setting(@RequestParam String journalsTitle,
                          @RequestParam String journalsPageSize,
                          @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                          @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                          Model model) {
        Option journalsTitleOption = new Option();
        journalsTitleOption.setOptionKey("journalsTitle");
        journalsTitleOption.setOptionValue(journalsTitle);
        journalsTitleOption.setUpdateTime(DateUtil.date());
        optionService.updateByOptionKey(journalsTitleOption);
        Option journalsPageSizeOption = new Option();
        journalsPageSizeOption.setOptionKey("journalsPageSize");
        journalsPageSizeOption.setOptionValue(journalsPageSize);
        journalsPageSizeOption.setUpdateTime(DateUtil.date());
        optionService.updateByOptionKey(journalsPageSizeOption);
        listJournals(model, pageNum, pageSize);
        return "redirect:/admin/journals";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                         Model model) {
        journalService.deleteByPrimaryKey(id);
        listJournals(model, pageNum, pageSize);
        return "admin/journals::journalList";
    }

    @PostMapping("/add")
    public String addPage(@RequestBody JournalParam journalParam,
                          @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                          Model model) {
        Journal journal = new Journal();
        BeanUtils.copyProperties(journalParam, journal);
        journal.setCreateTime(DateUtil.date());
        journal.setUpdateTime(DateUtil.date());
        journal.setContent(MarkdownUtils.markdownToHtml(journal.getSourceContent()));
        if (journalParam.getType().equals(JournalType.INTIMATE)) {
            journal.setType(JournalType.INTIMATE.getValue());
        } else {
            journal.setType(JournalType.PUBLIC.getValue());
        }
        journalService.insertSelective(journal);
        listJournals(model, 1, pageSize);
        return "admin/journals::journalsList";
    }

    @PostMapping("/{id}/edit")
    public String editPage(@PathVariable Integer id, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                           @RequestBody JournalParam journalParam,
                           Model model) {
        Journal journal = new Journal();
        BeanUtils.copyProperties(journalParam, journal);
        System.out.println(journal);
        journal.setId(id);
        journal.setUpdateTime(DateUtil.date());
        journal.setContent(MarkdownUtils.markdownToHtml(journal.getSourceContent()));
        if (journalParam.getType().equals(JournalType.INTIMATE)) {
            journal.setType(JournalType.INTIMATE.getValue());
        } else {
            journal.setType(JournalType.PUBLIC.getValue());
        }
        journalService.updateByPrimaryKeySelective(journal);
        listJournals(model, pageNum, pageSize);
        return "admin/journals";
    }

    private void listJournals(Model model, Integer pageNum, Integer pageSize) {
        Option journalsTitle = optionService.selectByOptionKey("journalsTitle");
        Option journalsPageSize = optionService.selectByOptionKey("journalsPageSize");
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        PageInfo<Journal> pageInfo = new PageInfo<>(journalService.listJournal());
        List<Journal> journals = pageInfo.getList();
        LinkedList<JournalDTO> journalList = new LinkedList<>();
        for (Journal journal : journals) {
            JournalDTO journalDTO = new JournalDTO();
            BeanUtils.copyProperties(journal, journalDTO);
            if (journal.getType().equals(JournalType.INTIMATE.getValue())) {
                journalDTO.setType(JournalType.INTIMATE);
            } else {
                journalDTO.setType(JournalType.PUBLIC);
            }
            journalList.add(journalDTO);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("journals", journalList);
        model.addAttribute("journalsTitle", journalsTitle);
        model.addAttribute("journalsPageSize", journalsPageSize);
    }

}
