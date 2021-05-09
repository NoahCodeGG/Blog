package cn.noahcode.blog.controller;

import cn.noahcode.blog.model.dto.CommentDTO;
import cn.noahcode.blog.model.dto.CommentWithChildrenDTO;
import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.service.CommentService;
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
 * @date 1/30/2021
 * @description
 */
@Controller
@RequestMapping("/comments")
public class PostCommentController {

    @Autowired
    private CommentService commentService;



}
