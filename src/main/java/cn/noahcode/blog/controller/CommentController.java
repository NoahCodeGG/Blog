package cn.noahcode.blog.controller;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.entity.User;
import cn.noahcode.blog.model.params.CommentParam;
import cn.noahcode.blog.service.CommentService;
import cn.noahcode.blog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NoahCode
 * @date 2021/2/6
 * @description
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/blog")
    public void blog(@RequestBody CommentParam commentParam, HttpServletRequest request) {
        User user = userService.selectByPrimaryKey(1);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParam, comment);
        if (comment.getAuthor().equals(user.getUsername()) && comment.getEmail().equals(user.getEmail())) {
            comment.setIsAdmin(true);
        }
        comment.setIpAddress(request.getRemoteHost());
        comment.setUserAgent(request.getHeader("User-Agent"));
        comment.setCreateTime(DateUtil.date());
        comment.setUpdateTime(DateUtil.date());
        commentService.insertSelective(comment);
    }

    @PostMapping("/page")
    public void pages(@RequestBody CommentParam commentParam, HttpServletRequest request) {
        User user = userService.selectByPrimaryKey(1);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParam, comment);
        if (comment.getAuthor().equals(user.getUsername()) && comment.getEmail().equals(user.getEmail())) {
            comment.setIsAdmin(true);
        }
        comment.setType(1);
        comment.setIpAddress(request.getRemoteHost());
        comment.setUserAgent(request.getHeader("User-Agent"));
        comment.setCreateTime(DateUtil.date());
        comment.setUpdateTime(DateUtil.date());
        commentService.insertSelective(comment);
    }

}
