package cn.noahcode.blog.controller.admin;

import cn.noahcode.blog.model.entity.Category;
import cn.noahcode.blog.service.CategoryService;
import cn.noahcode.blog.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/27
 * @description
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model) {
        List<Category> categories = categoryService.listCategory();
        PageHelper.startPage(1, 10);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryService.listCategory());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("categories", categories);
        return "/admin/categories";
    }

    @PostMapping("/categories/input")
    public String input(Category category, RedirectAttributes attributes, Model model) {
        Category c = categoryService.selectByName(category.getName());
        if (c != null) {
            attributes.addFlashAttribute("message", "该分类已存在");
            return "redirect:/admin/categories";
        }
        category.setCreateTime(DateUtils.now());
        category.setUpdateTime(DateUtils.now());
        int i = categoryService.insertSelective(category);
        if (i < 1) {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/list")
    public String listCategory(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                               Model model) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryService.listCategory());
        model.addAttribute("pageInfo", pageInfo);
        return "/admin/categories::categories-list";
    }

}
