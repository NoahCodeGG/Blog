package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.PostCategoryDTO;
import cn.noahcode.blog.model.dto.PostTagDTO;
import cn.noahcode.blog.model.dto.post.PostDetailDTO;
import cn.noahcode.blog.model.entity.*;
import cn.noahcode.blog.model.enums.PostStatus;
import cn.noahcode.blog.model.params.BlogParam;
import cn.noahcode.blog.model.params.BlogSettingParam;
import cn.noahcode.blog.model.params.PostQuery;
import cn.noahcode.blog.service.*;
import cn.noahcode.blog.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PostsCategoryService postsCategoryService;

    @Autowired
    private PostsTagService postsTagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String blogs(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                        Model model) {
        listBlogs(pageNum, pageSize, model);
        List<Category> categories = categoryService.listCategory();
        model.addAttribute("categories", categories);
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        return "admin/blogs";
    }

    @GetMapping("/input")
    public String input(Model model) {
        List<Category> categories = categoryService.listCategory();
        model.addAttribute("categories", categories);
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        return "admin/blogs-input";
    }

    @GetMapping("/{id}/input")
    public String editBlog(@PathVariable Long id, Model model) {
        //初始话blogDTO
        Post post = postService.selectByPrimaryKey(id);
        PostDetailDTO blog = new PostDetailDTO();
        BeanUtils.copyProperties(post, blog);
        model.addAttribute("blog", blog);
        //初始话categoryDTO
        List<Category> categories = categoryService.listCategory();
        model.addAttribute("categories", categories);
        //初始话tagsDTO
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        //初始化blogCategoryDTO
        List<PostsCategory> postsCategories = postsCategoryService.selectByBlogId(id);
        PostCategoryDTO blogsCategories = new PostCategoryDTO(categories, postsCategories);
        model.addAttribute("blogsCategories", blogsCategories);
        //初始化blogTagDTO
        List<PostsTag> postsTags = postsTagService.selectByBlogId(id);
        PostTagDTO blogsTags = new PostTagDTO(tags, postsTags);
        model.addAttribute("blogsTags", blogsTags);
        return "admin/blogs-input";
    }

    @PostMapping("")
    public String post(@RequestBody BlogParam blogParam) {
        Post post = new Post();
        BeanUtils.copyProperties(blogParam, post);
        Long postId = post.getId();
        switch (blogParam.getStatus()) {
            case PUBLISHED:
                post.setStatus(PostStatus.PUBLISHED.getValue());
                break;
            case RECYCLE:
                post.setStatus(PostStatus.RECYCLE.getValue());
                break;
            case INTIMATE:
                post.setStatus(PostStatus.INTIMATE.getValue());
                break;
            default:
                post.setStatus(PostStatus.DRAFT.getValue());
        }
        post.setFormatContent(MarkdownUtils.markdownToHtml(post.getOriginalContent()));
        if (postId == null) {
            post.setCreateTime(DateUtil.date());
            post.setEditTime(DateUtil.date());
            post.setUpdateTime(DateUtil.date());
            int insertCount = postService.insertSelective(post);
            postId = post.getId();
            if (insertCount != 0) {
                if (blogParam.getCategoryIds() != null) {
                    Set<Long> categoryIds = blogParam.getCategoryIds();
                    for (Long categoryId : categoryIds) {
                        PostsCategory postsCategory = new PostsCategory();
                        postsCategory.setPostId(postId);
                        postsCategory.setCategoryId(categoryId);
                        postsCategory.setCreateTime(DateUtil.date());
                        postsCategory.setUpdateTime(DateUtil.date());
                        postsCategoryService.insertSelective(postsCategory);
                    }
                }
                if (blogParam.getTagIds() != null) {
                    Set<Long> tagIds = blogParam.getTagIds();
                    for (Long tagId : tagIds) {
                        PostsTag postsTag = new PostsTag();
                        postsTag.setPostId(postId);
                        postsTag.setTagId(tagId);
                        postsTag.setCreateTime(DateUtil.date());
                        postsTag.setUpdateTime(DateUtil.date());
                        postsTagService.insertSelective(postsTag);
                    }
                }
            }
        } else {
            post.setEditTime(DateUtil.date());
            post.setUpdateTime(DateUtil.date());
            int updateCount = postService.updateByPrimaryKeySelective(post);
            if (updateCount != 0) {
                if (blogParam.getCategoryIds() != null) {
                    postsCategoryService.deleteByBlogId(postId);
                    Set<Long> categoryIds = blogParam.getCategoryIds();
                    for (Long categoryId : categoryIds) {
                        PostsCategory postsCategory = new PostsCategory();
                        postsCategory.setPostId(postId);
                        postsCategory.setCategoryId(categoryId);
                        postsCategory.setCreateTime(DateUtil.date());
                        postsCategory.setUpdateTime(DateUtil.date());
                        postsCategoryService.insertSelective(postsCategory);
                    }
                }
                if (blogParam.getTagIds() != null) {
                    postsTagService.deleteByBlogId(postId);
                    Set<Long> tagIds = blogParam.getTagIds();
                    for (Long tagId : tagIds) {
                        PostsTag postsTag = new PostsTag();
                        postsTag.setPostId(postId);
                        postsTag.setTagId(tagId);
                        postsTag.setCreateTime(DateUtil.date());
                        postsTag.setUpdateTime(DateUtil.date());
                        postsTagService.insertSelective(postsTag);
                    }
                }
            }
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/{id}/recycle")
    public String recycled(@PathVariable Long id,
                           @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                           Model model) {
        postService.toRecycle(id);
        listBlogs(pageNum, pageSize, model);
        return "admin/blogs::blogs";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                         Model model) {
        postsCategoryService.deleteByBlogId(id);
        postsTagService.deleteByBlogId(id);
        commentService.deleteByPostId(id);
        postService.deleteByPrimaryKey(id);
        listBlogs(pageNum, pageSize, model);
        return "admin/blogs::blogs";
    }

    @GetMapping("/{id}/reduction")
    public String reduction(@PathVariable Long id,
                            @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                            Model model) {
        postService.toReduction(id);
        listBlogs(pageNum, pageSize, model);
        return "admin/blogs::blogs";
    }

    @GetMapping("/search")
    public String select(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(value = "categoryId", required = false) Long categoryId,
                         @RequestParam(value = "status", required = false) String status,
                         Model model) {
        PostQuery postQuery = new PostQuery();
        postQuery.setKeyword(keyword);
        if (status != null) {
            switch (status) {
                case "PUBLISHED":
                    postQuery.setStatus(PostStatus.PUBLISHED.getValue());
                    break;
                case "DRAFT":
                    postQuery.setStatus(PostStatus.DRAFT.getValue());
                    break;
                case "RECYCLE":
                    postQuery.setStatus(PostStatus.RECYCLE.getValue());
                    break;
                case "INTIMATE":
                    postQuery.setStatus(PostStatus.INTIMATE.getValue());
                    break;
            }
        }
        if (categoryId != null) {
            postQuery.setCategoryId(categoryId);
        }
        listQueryBlogs(postQuery, pageNum, pageSize, model);
        return "admin/blogs::blogs";
    }

//    @GetMapping("/{id}/setting")
//    public String setting(@PathVariable Long id,
//                          @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                          @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
//                          Model model) {
//        listBlogs(pageNum, pageSize, model);
//        Post post = postService.selectByPrimaryKey(id);
//        PostSimpleDTO setting = new PostSimpleDTO();
//        BeanUtils.copyProperties(post, setting);
//        model.addAttribute("setting", setting);
//        List<Category> categories = categoryService.listCategory();
//        model.addAttribute("categories", categories);
//        List<Tag> tags = tagService.listTags();
//        model.addAttribute("tags", tags);
//        List<PostsCategory> postsCategories = postsCategoryService.selectByBlogId(id);
//        PostCategoryDTO blogsCategories = new PostCategoryDTO(categories, postsCategories);
//        model.addAttribute("blogsCategories", blogsCategories);
//        List<PostsTag> postsTags = postsTagService.selectByBlogId(id);
//        PostTagDTO blogsTags = new PostTagDTO(tags, postsTags);
//        model.addAttribute("blogsTags", blogsTags);
//        return "/admin/blogs::setting";
//    }

    @PostMapping("/{id}/setting")
    public String editSetting(@PathVariable Long id,
                              @RequestBody BlogSettingParam blogSettingParam,
                              @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                              Model model) {
        Post post = new Post();
        BeanUtils.copyProperties(blogSettingParam, post);
        post.setId(id);
        post.setEditTime(DateUtil.date());
        post.setUpdateTime(DateUtil.date());
        int updateCount = postService.updateByPrimaryKeySelective(post);
        if (updateCount != 0) {
            if (blogSettingParam.getCategoryIds() != null) {
                postsCategoryService.deleteByBlogId(id);
                Set<Long> categoryIds = blogSettingParam.getCategoryIds();
                for (Long categoryId : categoryIds) {
                    PostsCategory postsCategory = new PostsCategory();
                    postsCategory.setPostId(id);
                    postsCategory.setCategoryId(categoryId);
                    postsCategory.setCreateTime(DateUtil.date());
                    postsCategory.setUpdateTime(DateUtil.date());
                    postsCategoryService.insertSelective(postsCategory);
                }
            }
            if (blogSettingParam.getTagIds() != null) {
                postsTagService.deleteByBlogId(id);
                Set<Long> tagIds = blogSettingParam.getTagIds();
                for (Long tagId : tagIds) {
                    PostsTag postsTag = new PostsTag();
                    postsTag.setPostId(id);
                    postsTag.setTagId(tagId);
                    postsTag.setCreateTime(DateUtil.date());
                    postsTag.setUpdateTime(DateUtil.date());
                    postsTagService.insertSelective(postsTag);
                }
            }
        }
        listBlogs(pageNum, pageSize, model);
        return "admin/blogs::blogs";
    }


    private void listBlogs(Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize, "edit_time desc");
        PageInfo<Post> pageInfo = new PageInfo<>(postService.listBlog());
        List<Post> postList = pageInfo.getList();
        List<PostDetailDTO> blogs = new LinkedList<>();
        for (Post post : postList) {
            Long blogId = post.getId();
            PostDetailDTO postDetailDTO = new PostDetailDTO();
            BeanUtils.copyProperties(post, postDetailDTO);
            postDetailDTO.setCategories(categoryService.selectByPostId(blogId));
            postDetailDTO.setTags(tagService.selectByPostId(blogId));
            postDetailDTO.setCommentCount(commentService.selectByPostIdBlogComments(blogId));
            blogs.add(postDetailDTO);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogs", blogs);
    }

    private void listQueryBlogs(PostQuery postQuery, Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Post> posts = postService.listQueryBlog(postQuery);
        if (postQuery.getCategoryId() != null) {
            Iterator<Post> iterator = posts.iterator();
            post:
            while (iterator.hasNext()) {
                List<PostsCategory> postsCategories = postsCategoryService.selectByBlogId(iterator.next().getId());
                for (PostsCategory postsCategory : postsCategories) {
                    if (postsCategory.getCategoryId().equals(postQuery.getCategoryId())) {
                        continue post;
                    }
                }
                iterator.remove();
                continue post;
            }
        }
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        List<PostDetailDTO> blogs = new LinkedList<>();
        for (Post post : posts) {
            Long blogId = post.getId();
            PostDetailDTO postDetailDTO = new PostDetailDTO();
            BeanUtils.copyProperties(post, postDetailDTO);
            postDetailDTO.setCategories(categoryService.selectByPostId(blogId));
            postDetailDTO.setTags(tagService.selectByPostId(blogId));
            postDetailDTO.setCommentCount(commentService.selectByPostIdBlogComments(blogId));
            blogs.add(postDetailDTO);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogs", blogs);
    }

}


