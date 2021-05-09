package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.MenuDTO;
import cn.noahcode.blog.model.entity.Menu;
import cn.noahcode.blog.model.entity.Option;
import cn.noahcode.blog.model.params.MenuParam;
import cn.noahcode.blog.service.MenuService;
import cn.noahcode.blog.service.OptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/5/2021
 * @description
 */
@Controller
@RequestMapping("/admin/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private OptionService optionService;

    @GetMapping("")
    public String menu(Model model) {
        Option default_menu_team = optionService.selectByOptionKey("defaultMenuTeam");
        team(default_menu_team.getOptionValue(), model);
        List<String> teams = menuService.listTeams();
        model.addAttribute("defaultMenuTeam", default_menu_team.getOptionValue());
        model.addAttribute("teams", teams);
        return "admin/menus";
    }

    @GetMapping("/team")
    public String team(@RequestParam(name = "team") String team, Model model) {
        listPages(team, model);
        return "admin/menus::pageList";
    }

    @GetMapping("/team/{team}/delete")
    public String deleteTeam(@PathVariable String team) {
        Option defaultMenuTeamOption = optionService.selectByOptionKey("defaultMenuTeam");
        if (defaultMenuTeamOption.getOptionValue().equals(team)) {
            Option option = new Option();
            option.setOptionKey("defaultMenuTeam");
            option.setOptionValue("");
            option.setUpdateTime(DateUtil.date());
            optionService.updateByOptionKey(option);
        }
        menuService.deleteByTeam(team);
        return "admin/menus";
    }

    @PostMapping("/add")
    public String addPage(@RequestBody MenuParam menuParam, Model model) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuParam, menu);
        menu.setCreateTime(DateUtil.date());
        menu.setUpdateTime(DateUtil.date());
        menuService.insertSelective(menu);
        listPages(menu.getTeam(), model);
        return "admin/menus::pageList";
    }

    @PostMapping("/{id}/edit")
    public String editPage(@PathVariable Long id, @RequestBody MenuParam menuParam, Model model) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuParam, menu);
        menu.setId(id);
        menu.setUpdateTime(DateUtil.date());
        menuService.updateByPrimaryKeySelective(menu);
        listPages(menu.getTeam(), model);
        return "admin/menus::pageList";
    }

    @GetMapping("/page/{id}/delete")
    public String deletePage(@PathVariable Long id, Model model) {
        Menu menu = menuService.selectByPrimaryKey(id);
        menuService.deleteByPrimaryKey(id);
        listPages(menu.getTeam(), model);
        return "admin/menus::pageList";
    }

    public void listPages(String team, Model model) {
        List<Menu> menus = menuService.listPages(team);
        LinkedList<MenuDTO> pages = new LinkedList<>();
        for (Menu menu : menus) {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtils.copyProperties(menu, menuDTO);
            pages.add(menuDTO);
        }
        model.addAttribute("pages", pages);
    }

}
