package cn.noahcode.blog.controller;

import cn.noahcode.blog.model.dto.UserDTO;
import cn.noahcode.blog.model.dto.post.PostDetailDTO;
import cn.noahcode.blog.model.entity.Menu;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.model.entity.User;
import cn.noahcode.blog.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/27
 * @description
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "9", name = "pageSize") Integer pageSize,
                        Model model) {
        listBlogs(pageNum, pageSize, model);
        listMenus(model);
        User user = userService.selectByPrimaryKey(1);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        model.addAttribute("user", userDTO);
        return "index";
    }

    public void listBlogs(Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        PageInfo<Post> pageInfo = new PageInfo<>(postService.listPublishedBlog());
        List<Post> postList = pageInfo.getList();
        List<PostDetailDTO> postDetailDTOS = new LinkedList<>();
        for (Post post : postList) {
            PostDetailDTO postDetailDTO = new PostDetailDTO();
            BeanUtils.copyProperties(post, postDetailDTO);
            postDetailDTO.setCategories(categoryService.selectByPostId(post.getId()));
            postDetailDTO.setCommentCount(commentService.selectByPostIdBlogComments(post.getId()));
            postDetailDTOS.add(postDetailDTO);
        }
        model.addAttribute("blogs", postDetailDTOS);
        model.addAttribute("pageInfo", pageInfo);
    }

    public void listMenus(Model model) {
        String defaultMenuTeam = optionService.selectByOptionKey("defaultMenuTeam").getOptionValue();
        List<Menu> menus = menuService.listPages(defaultMenuTeam);
        model.addAttribute("menus", menus);
    }

}
