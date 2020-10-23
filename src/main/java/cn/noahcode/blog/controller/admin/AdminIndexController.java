package cn.noahcode.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NoahCode
 * @date 2020/9/27
 * @description
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

}
