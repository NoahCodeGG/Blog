package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.CategoryWithPostCountDTO;
import cn.noahcode.blog.model.entity.Category;
import cn.noahcode.blog.model.params.CategoryParam;
import cn.noahcode.blog.service.CategoryService;
import cn.noahcode.blog.service.PostsCategoryService;
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
 * @date 2020/9/27
 * @description
 */
@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostsCategoryService postsCategoryService;

    @GetMapping("")
    public String categories(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        listCategories(pageNum, model);
        return "admin/categories";
    }

    @PostMapping("/input")
    public String input(CategoryParam categoryParam, RedirectAttributes attributes) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryParam, category);
        Category c = categoryService.selectByName(categoryParam.getName());
        if (c != null) {
            attributes.addFlashAttribute("message", "该分类已存在");
            return "redirect:admin/categories";
        }
        int insertOrUpdateNum;
        category.setUpdateTime(DateUtil.date());
        if (category.getId() == null) {
            category.setCreateTime(DateUtil.date());
            insertOrUpdateNum = categoryService.insertSelective(category);
        } else {
            insertOrUpdateNum = categoryService.updateByPrimaryKeySelective(category);
        }
        if (insertOrUpdateNum < 1) {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/list")
    public String listCategory(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               Model model) {
        listCategories(pageNum, model);
        return "admin/categories::categories-list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         Model model) {
        postsCategoryService.deleteByCategoryId(id);
        categoryService.deleteByPrimaryKey(id);
        listCategories(pageNum, model);
        return "admin/categories::categories-list";
    }

    private void listCategories(Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 10);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryService.listCategory());
        List<Category> categories = pageInfo.getList();
        LinkedList<CategoryWithPostCountDTO> categoryList = new LinkedList<>();
        for (Category category : categories) {
            CategoryWithPostCountDTO categoryWithPostCountDTO = new CategoryWithPostCountDTO();
            BeanUtils.copyProperties(category, categoryWithPostCountDTO);
            categoryWithPostCountDTO.setBlogCount(postsCategoryService.countByCategoryId(category.getId()));
            categoryList.add(categoryWithPostCountDTO);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("categories", categoryList);
    }

}
