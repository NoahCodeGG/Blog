package cn.noahcode.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NoahCode
 * @date 12/29/2020
 * @description
 */
@Controller
@RequestMapping("/admin/")
public class AdminAboutController {
    @GetMapping("/about")
    public String about() {
        return "admin/about";
    }
}
