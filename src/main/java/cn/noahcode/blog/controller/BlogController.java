package cn.noahcode.blog.controller;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.noahcode.blog.model.dto.CommentDTO;
import cn.noahcode.blog.model.dto.CommentWithChildrenDTO;
import cn.noahcode.blog.model.dto.post.PostDetailDTO;
import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.entity.Menu;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author NoahCode
 * @date 1/18/2021
 * @description
 */
@Controller
@RequestMapping("/blogs/{id}")
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String blog(@PathVariable Long id, Model model) {
        Post post = postService.selectByPrimaryKey(id);
        postService.addVisitor(id);
        PostDetailDTO postDetailDTO = new PostDetailDTO();
        BeanUtils.copyProperties(post, postDetailDTO);
        postDetailDTO.setCategories(categoryService.selectByPostId(id));
        postDetailDTO.setTags(tagService.selectByPostId(id));
        listMenus(model);
        listComments(id, model);
        model.addAttribute("blog", postDetailDTO);
        return "blogs";
    }

//    @PostMapping("/comment")
//    public String comment(@RequestParam CommentParam commentParam) {
//
//    }

    public void listComments(Long id, Model model) {
        List<Comment> commentList = commentService.selectBlogCommentsByPostId(id);
        if (commentList.size() == 0) {
            model.addAttribute("comments", null);
            return;
        }
        Map<Long, CommentWithChildrenDTO> comments = new LinkedHashMap<>();
        for (Comment comment : commentList) {
            UserAgent userAgent = UserAgentUtil.parse(comment.getUserAgent());
            CommentWithChildrenDTO commentWithChildrenDTO = new CommentWithChildrenDTO();
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentWithChildrenDTO);
            commentWithChildrenDTO.setUserAgent(userAgent.getBrowser().toString() + " " + userAgent.getVersion() + " in " + userAgent.getOs().toString());
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUserAgent(userAgent.getBrowser().toString() + " " + userAgent.getVersion() + " in " + userAgent.getOs().toString());
            if (comment.getParentId() == 0) {
                List<CommentDTO> children = new LinkedList<>();
                commentWithChildrenDTO.setHasChildren(false);
                commentWithChildrenDTO.setChildren(children);
                comments.put(comment.getId(), commentWithChildrenDTO);
            } else {
                CommentWithChildrenDTO parent = comments.get(comment.getParentId());
                parent.setHasChildren(true);
                List<CommentDTO> children = parent.getChildren();
                children.add(commentDTO);
            }
        }
        model.addAttribute("comments", comments);
    }

    public void listMenus(Model model) {
        String defaultMenuTeam = optionService.selectByOptionKey("defaultMenuTeam").getOptionValue();
        List<Menu> menus = menuService.listPages(defaultMenuTeam);
        model.addAttribute("menus", menus);
    }
}
