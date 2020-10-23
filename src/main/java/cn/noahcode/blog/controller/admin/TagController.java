package cn.noahcode.blog.controller.admin;

import cn.noahcode.blog.model.entity.Tag;
import cn.noahcode.blog.service.TagService;
import cn.noahcode.blog.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model) {
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        return "/admin/tags";
    }

    @PostMapping("/tags/input")
    public String input(Tag tag, RedirectAttributes attributes, Model model) {
        Tag t = tagService.selectByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("message", "该标签已存在");
            return "redirect:/admin/tags";
        }
        tag.setCreateTime(DateUtils.now());
        tag.setUpdateTime(DateUtils.now());
        int i = tagService.insertSelective(tag);
        if (i < 1) {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/tags";
    }

}
