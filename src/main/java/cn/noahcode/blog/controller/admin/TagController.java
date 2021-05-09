package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.entity.Tag;
import cn.noahcode.blog.service.PostsTagService;
import cn.noahcode.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private PostsTagService postsTagService;

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model) {
        listTags(model);
        return "admin/tags";
    }

    @PostMapping("/tags/input")
    public String input(Tag tag, RedirectAttributes attributes) {
        Tag t = tagService.selectByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("message", "该标签已存在");
            return "redirect:/admin/tags";
        }
        int insertOrUpdateNum = 0;
        tag.setUpdateTime(DateUtil.date());
        if (tag.getId() == null) {
            tag.setCreateTime(DateUtil.date());
            insertOrUpdateNum = tagService.insertSelective(tag);
        } else {
            insertOrUpdateNum = tagService.updateByPrimaryKeySelective(tag);
        }
        if (insertOrUpdateNum < 1) {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,
                         Model model) {
        postsTagService.deleteByTagId(id);
        tagService.deleteByPrimaryKey(id);
        listTags(model);
        return "admin/tags::tags";
    }

    private void listTags(Model model) {
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
    }
}
