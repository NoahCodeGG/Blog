package cn.noahcode.blog.controller;

import cn.noahcode.blog.model.entity.Link;
import cn.noahcode.blog.model.entity.Menu;
import cn.noahcode.blog.service.LinkService;
import cn.noahcode.blog.service.MenuService;
import cn.noahcode.blog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/29/2021
 * @description
 */
@Controller
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public String links(Model model) {
        String linksTitle = optionService.selectByOptionKey("linksTitle").getOptionValue();
        model.addAttribute("linksTitle", linksTitle);
        listLinks(model);
        listMenus(model);
        return "links";
    }

    public void listLinks(Model model) {
        List<Link> links = linkService.listLink();
        model.addAttribute("links", links);
    }

    public void listMenus(Model model) {
        String defaultMenuTeam = optionService.selectByOptionKey("defaultMenuTeam").getOptionValue();
        List<Menu> menus = menuService.listPages(defaultMenuTeam);
        model.addAttribute("menus", menus);
    }

}
