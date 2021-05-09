package cn.noahcode.blog.controller;

import cn.noahcode.blog.model.dto.JournalDTO;
import cn.noahcode.blog.model.entity.Journal;
import cn.noahcode.blog.model.entity.Menu;
import cn.noahcode.blog.model.entity.User;
import cn.noahcode.blog.service.JournalService;
import cn.noahcode.blog.service.MenuService;
import cn.noahcode.blog.service.OptionService;
import cn.noahcode.blog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/29/2021
 * @description
 */
@Controller
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String journals(Model model) {
        User user = userService.selectByPrimaryKey(1);
        model.addAttribute("user", user);
        String journalsTitle = optionService.selectByOptionKey("journalsTitle").getOptionValue();
        model.addAttribute("journalsTitle", journalsTitle);
        listJournals(model);
        listMenus(model);
        return "journals";
    }

    public void listJournals(Model model) {
        List<Journal> journalList = journalService.listJournal();
        List<JournalDTO> journals = new LinkedList<>();
        for (Journal journal : journalList) {
            JournalDTO journalDTO = new JournalDTO();
            BeanUtils.copyProperties(journal, journalDTO);
            journals.add(journalDTO);
        }
        model.addAttribute("journals", journals);
    }

    public void listMenus(Model model) {
        String defaultMenuTeam = optionService.selectByOptionKey("defaultMenuTeam").getOptionValue();
        List<Menu> menus = menuService.listPages(defaultMenuTeam);
        model.addAttribute("menus", menus);
    }

}
