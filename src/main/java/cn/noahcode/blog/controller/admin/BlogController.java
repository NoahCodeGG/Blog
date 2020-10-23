package cn.noahcode.blog.controller.admin;

import cn.noahcode.blog.model.dto.BlogCategoryDTO;
import cn.noahcode.blog.model.dto.BlogTagDTO;
import cn.noahcode.blog.model.dto.blog.BlogDetailDTO;
import cn.noahcode.blog.model.dto.blog.BlogSimpleDTO;
import cn.noahcode.blog.model.entity.*;
import cn.noahcode.blog.model.enums.BlogStatus;
import cn.noahcode.blog.model.params.BlogParam;
import cn.noahcode.blog.service.*;
import cn.noahcode.blog.util.DateUtils;
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
import java.util.Set;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogsCategoryService blogsCategoryService;

    @Autowired
    private BlogsTagService blogsTagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                        Model model) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.listBlog());
        List<Blog> blogList = pageInfo.getList();
        List<BlogSimpleDTO> blogs = new LinkedList<>();
        for (Blog blog : blogList) {
            Integer blogId = blog.getId();
            BlogSimpleDTO blogSimpleDTO = new BlogSimpleDTO();
            BeanUtils.copyProperties(blog, blogSimpleDTO);
            blogSimpleDTO.setCategories(categoryService.selectByBlogId(blogId));
            blogSimpleDTO.setTags(tagService.selectByBlogId(blogId));
            blogs.add(blogSimpleDTO);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogs", blogs);
        return "/admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        List<Category> categories = categoryService.listCategory();
        model.addAttribute("categories", categories);
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        return "/admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Integer id, Model model) {
        //初始话blogDTO
        Blog blog = blogService.selectByPrimaryKey(id);
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO();
        BeanUtils.copyProperties(blog, blogDetailDTO);
        model.addAttribute("blog", blogDetailDTO);
        //初始话categoryDTO
        List<Category> categories = categoryService.listCategory();
        model.addAttribute("categories", categories);
        //初始话tagsDTO
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        //初始化blogCategoryDTO
        List<BlogsCategory> blogsCategories = blogsCategoryService.selectByBlogId(id);
        BlogCategoryDTO blogCategoryDTO = new BlogCategoryDTO(categories, blogsCategories);
        System.out.println(blogCategoryDTO.getBlogCategories());
        model.addAttribute("blogsCategories", blogCategoryDTO);
        //初始化blogTagDTO
        List<BlogsTag> blogsTags = blogsTagService.selectByBlogId(id);
        BlogTagDTO blogTagDTO = new BlogTagDTO(tags, blogsTags);
        model.addAttribute("blogsTags", blogTagDTO);
        return "/admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(@RequestBody BlogParam blogParam) {
        System.out.println(blogParam);
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogParam, blog);
        switch (blogParam.getStatus()) {
            case PUBLISHED:
                blog.setStatus(BlogStatus.PUBLISHED.getValue());
                break;
            case RECYCLE:
                blog.setStatus(BlogStatus.RECYCLE.getValue());
                break;
            case INTIMATE:
                blog.setStatus(BlogStatus.INTIMATE.getValue());
                break;
            default:
                blog.setStatus(BlogStatus.DRAFT.getValue());
        }
        blog.setFormatContent(MarkdownUtils.markdownToHtml(blog.getOriginalContent()));
        blog.setEditTime(DateUtils.now());
        blog.setCreateTime(DateUtils.now());
        blog.setUpdateTime(DateUtils.now());
        int count = blogService.insertSelective(blog);
        int blogId = blog.getId();
        if (count != 0) {
            if (blogParam.getCategoryIds() != null) {
                Set<Integer> categoryIds = blogParam.getCategoryIds();
                for (Integer categoryId : categoryIds) {
                    BlogsCategory blogsCategory = new BlogsCategory();
                    blogsCategory.setBlogId(blogId);
                    blogsCategory.setCategoryId(categoryId);
                    blogsCategory.setCreateTime(DateUtils.now());
                    blogsCategory.setUpdateTime(DateUtils.now());
                    blogsCategoryService.insertSelective(blogsCategory);
                }
            }
            if (blogParam.getTagIds() != null) {
                Set<Integer> tagIds = blogParam.getTagIds();
                for (Integer tagId : tagIds) {
                    BlogsTag blogsTag = new BlogsTag();
                    blogsTag.setBlogId(blogId);
                    blogsTag.setTagId(tagId);
                    blogsTag.setCreateTime(DateUtils.now());
                    blogsTag.setUpdateTime(DateUtils.now());
                    blogsTagService.insertSelective(blogsTag);
                }
            }
        }
        return "redirect:/admin/blogs";
    }

}
