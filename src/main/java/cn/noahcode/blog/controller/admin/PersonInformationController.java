package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.entity.User;
import cn.noahcode.blog.model.params.PasswordParam;
import cn.noahcode.blog.model.params.UserParam;
import cn.noahcode.blog.service.*;
import cn.noahcode.blog.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Controller
@RequestMapping("/admin")
public class PersonInformationController {

    @Autowired
    private UserService userService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/personal-information")
    public String personalInformation(Model model) {
        int blogCounts = postService.blogCount();
        int categoryCounts = categoryService.categoryCount();
        int tagCounts = tagService.tagCount();
        int commentCounts = commentService.commentCount();
        int viewCounts = postService.visitCount();
        User user = userService.selectByPrimaryKey(1);
        String countDown = DateUtil.formatBetween(user.getCreateTime(), DateUtil.date(), BetweenFormatter.Level.DAY);
        model.addAttribute("url", optionService.selectByOptionKey("blogUrl").getOptionValue());
        model.addAttribute("user", user);
        model.addAttribute("countDown", countDown);
        model.addAttribute("blogCounts", blogCounts);
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("tagCounts", tagCounts);
        model.addAttribute("commentCounts", commentCounts);
        model.addAttribute("viewCounts", viewCounts);
        return "admin/personal-information";
    }

    @PostMapping("/personal-information")
    public String updatePersonalInformation(UserParam userParam, Model model) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setUpdateTime(DateUtil.date());
        userService.updateUser(user, 1);
        return "redirect:/admin/personal-information";
    }

    @PostMapping("/personal-information/password")
    public String updatePassword(PasswordParam passwordParam, RedirectAttributes attributes) {
        User user = userService.selectByPrimaryKey(1);
        if (user.getPassword().equals(MD5Utils.code(passwordParam.getOldPassword()))) {
            userService.updatePassword(MD5Utils.code(passwordParam.getNewPassword()), 1);
        } else {
            attributes.addFlashAttribute("message", "旧密码错误");
            attributes.addFlashAttribute("password", passwordParam);
        }
        return "redirect:/admin/personal-information#/password";
    }

}
