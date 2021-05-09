package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.CommentDTO;
import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.service.CommentService;
import cn.noahcode.blog.service.MenuService;
import cn.noahcode.blog.service.OptionService;
import cn.noahcode.blog.service.PostService;
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
 * @date 2020/9/27
 * @description
 */
@Controller
@RequestMapping("/admin/index")
public class AdminIndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public String index(Model model) {
        int blogCounts = postService.blogCount();
        int commentCounts = commentService.commentCount();
        int viewCounts = postService.visitCount();
        String creatDays = DateUtil.formatBetween(DateUtil.date(Long.valueOf(optionService.selectByOptionKey("birthday").getOptionValue())), DateUtil.date(), BetweenFormatter.Level.DAY);
        List<Post> newBlogs = postService.listNewBlog();
        List<Comment> blogComments = commentService.listNewBlogComment();
        LinkedList<CommentDTO> newBlogComments = new LinkedList<>();
        if (blogComments.size() > 0) {
            for (Comment blogComment : blogComments) {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(blogComment, commentDTO);
                commentDTO.setPostTitle(postService.selectByPrimaryKey(blogComment.getPostId()).getTitle());
                newBlogComments.add(commentDTO);
            }
        }
        List<Comment> pageComments = commentService.listNewPageComment();
        LinkedList<CommentDTO> newPageComments = new LinkedList<>();
        if (pageComments.size() > 0) {
            for (Comment pageComment : pageComments) {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(pageComment, commentDTO);
                commentDTO.setPageTitle(postService.selectByPrimaryKey(pageComment.getPostId()).getTitle());
                newPageComments.add(commentDTO);
            }
        }
        model.addAttribute("blogCounts", blogCounts);
        model.addAttribute("commentCounts", commentCounts);
        model.addAttribute("viewCounts", viewCounts);
        model.addAttribute("creatDays", creatDays);
        model.addAttribute("newBlogs", newBlogs);
        model.addAttribute("newBlogComments", newBlogComments);
        model.addAttribute("newPageComments", newPageComments);
        return "admin/index";
    }

}
