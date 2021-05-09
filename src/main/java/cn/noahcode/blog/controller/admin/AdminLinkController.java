package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.LinkDTO;
import cn.noahcode.blog.model.entity.Link;
import cn.noahcode.blog.model.entity.Option;
import cn.noahcode.blog.model.params.LinkParam;
import cn.noahcode.blog.service.LinkService;
import cn.noahcode.blog.service.OptionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Controller
@RequestMapping("/admin/links")
public class AdminLinkController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private LinkService linkService;

    @GetMapping("")
    public String links(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        Model model) {
        listLinks(model, pageNum);
        return "admin/links";
    }

    @PostMapping("/input")
    public String input(LinkParam linkParam, RedirectAttributes attributes) {
        Link link = new Link();
        BeanUtils.copyProperties(linkParam, link);
        Link l = linkService.selectByName(linkParam.getName());
        if (l != null) {
            attributes.addFlashAttribute("message", "该分类已存在");
            return "redirect:/admin/links";
        }
        int insertOrUpdateNum;
        link.setUpdateTime(DateUtil.date());
        if (link.getId() == null) {
            link.setCreateTime(DateUtil.date());
            insertOrUpdateNum = linkService.insertSelective(link);
        } else {
            insertOrUpdateNum = linkService.updateByPrimaryKeySelective(link);
        }
        if (insertOrUpdateNum < 1) {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/links";
    }

    @GetMapping("/list")
    public String listCategory(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               Model model) {
        listLinks(model, pageNum);
        return "admin/links::links-list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         Model model) {
        linkService.deleteByPrimaryKey(id);
        listLinks(model, pageNum);
        return "admin/links::links-list";
    }

    @GetMapping("/setting")
    public String editTitle(@RequestParam String linksTitle,
                            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                            Model model) {
        Option linksTitleOption = new Option();
        linksTitleOption.setOptionKey("linksTitle");
        linksTitleOption.setOptionValue(linksTitle);
        linksTitleOption.setUpdateTime(DateUtil.date());
        optionService.updateByOptionKey(linksTitleOption);
        listLinks(model, pageNum);
        return "redirect:/admin/links";
    }

    private void listLinks(Model model, Integer pageNum) {
        Option linksTitle = optionService.selectByOptionKey("linksTitle");
        PageHelper.startPage(pageNum, 10, "create_time desc");
        PageInfo<Link> pageInfo = new PageInfo<>(linkService.listLink());
        List<Link> links = pageInfo.getList();
        LinkedList<LinkDTO> linkedList = new LinkedList<>();
        for (Link link : links) {
            LinkDTO linkDTO = new LinkDTO();
            BeanUtils.copyProperties(link, linkDTO);
            linkedList.add(linkDTO);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("links", linkedList);
        model.addAttribute("linksTitle", linksTitle);
    }

}
