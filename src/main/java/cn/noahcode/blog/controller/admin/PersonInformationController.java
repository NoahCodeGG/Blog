package cn.noahcode.blog.controller.admin;

import cn.noahcode.blog.config.ServerConfig;
import cn.noahcode.blog.model.entity.User;
import cn.noahcode.blog.model.params.PasswordParam;
import cn.noahcode.blog.model.params.UserParam;
import cn.noahcode.blog.service.UserService;
import cn.noahcode.blog.util.CountDownUtils;
import cn.noahcode.blog.util.DateUtils;
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
    private ServerConfig serverConfig;

    @Autowired
    private UserService userService;

    @GetMapping("/personal-information")
    public String personalInformation(Model model) {
        User user = userService.selectByPrimaryKey(1);
        String countDown = CountDownUtils.getCountDown(user.getCreateTime(), DateUtils.now());
        model.addAttribute("url", serverConfig.getUrl());
        model.addAttribute("user", user);
        model.addAttribute("countDown", countDown);
        return "/admin/personal-information";
    }

    @PostMapping("/personal-information")
    public String updatePersonalInformation(UserParam userParam, Model model) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setUpdateTime(DateUtils.now());
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
