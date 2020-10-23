package cn.noahcode.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author NoahCode
 * @date 2020/9/27
 * @description
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
