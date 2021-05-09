package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.CommentDTO;
import cn.noahcode.blog.model.entity.Comment;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.model.entity.User;
import cn.noahcode.blog.model.enums.CommentStatus;
import cn.noahcode.blog.model.params.CommentQuery;
import cn.noahcode.blog.service.CommentService;
import cn.noahcode.blog.service.PostService;
import cn.noahcode.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 12/29/2020
 * @description
 */
@Controller
@RequestMapping("/admin/comments")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String comments(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                           Model model) {
        listComments(pageNum, pageSize, model);
        return "admin/comments";
    }

    @GetMapping("/{type}/{id}/adopt")
    public String adoptBlogComment(@PathVariable Long id,
                                   @PathVariable String type,
                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                   @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                                   Model model) {
        commentService.toAdopt(id);
        listComments(pageNum, pageSize, model);
        if (type.equals("blog")) {
            return "admin/comments::blogComments";
        } else {
            return "admin/comments::pageComments";
        }
    }

    @PostMapping("/{type}/{id}/reply")
    public String replyBlogComment(@PathVariable Long id,
                                   @PathVariable String type,
                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                   @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                                   @RequestParam(name = "postId") Long postId,
                                   @RequestParam(name = "content") String content,
                                   HttpServletRequest request,
                                   Model model) {
        Comment comment = new Comment();
        User user = userService.selectByPrimaryKey(1);
        comment.setContent(content);
        comment.setAuthor(user.getUsername());
        comment.setEmail(user.getEmail());
        comment.setIpAddress(request.getHeader("Host"));
        comment.setStatus(CommentStatus.PUBLISHED.getValue());
        comment.setUserAgent(request.getHeader("User-Agent"));
        comment.setIsAdmin(true);
        comment.setPostId(postId);
        comment.setParentId(id);
        comment.setCreateTime(DateUtil.date());
        comment.setUpdateTime(DateUtil.date());
        if (type.equals("blog")) {
            comment.setType(0);
        } else {
            comment.setType(1);
        }
        commentService.insertSelective(comment);
        listComments(pageNum, pageSize, model);
        return "admin/comments";
    }

    @GetMapping("/{type}/{id}/recycle")
    public String recycleBlogComment(@PathVariable Long id,
                                     @PathVariable String type,
                                     @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                                     Model model) {
        commentService.toRecycle(id);
        if (type.equals("blog")) {
            listBlogComments(pageNum, pageSize, model);
            return "admin/comments::blogComments";
        } else {
            listPageComments(pageNum, pageSize, model);
            return "admin/comments::pageComments";
        }
    }

    @GetMapping("/{type}/{id}/reduction")
    public String reductionBlogComment(@PathVariable Long id,
                                       @PathVariable String type,
                                       @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                       @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                                       Model model) {
        commentService.toReduction(id);
        if (type.equals("blog")) {
            listBlogComments(pageNum, pageSize, model);
            return "admin/comments::blogComments";
        } else {
            listPageComments(pageNum, pageSize, model);
            return "admin/comments::pageComments";
        }
    }

    @GetMapping("/{type}/{id}/delete")
    public String deleteBlogComment(@PathVariable Long id,
                                    @PathVariable String type,
                                    @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                    @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                                    Model model) {
        commentService.deleteByPrimaryKey(id);
        if (type.equals("blog")) {
            listBlogComments(pageNum, pageSize, model);
            return "admin/comments::blogComments";
        } else {
            listPageComments(pageNum, pageSize, model);
            return "admin/comments::pageComments";
        }
    }

    @GetMapping("/{type}/search")
    public String select(@PathVariable String type,
                         @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(value = "status", required = false) String status,
                         Model model) {
        CommentQuery commentQuery = new CommentQuery();
        commentQuery.setKeyword(keyword);
        if (status != null) {
            switch (status) {
                case "PUBLISHED":
                    commentQuery.setStatus(CommentStatus.PUBLISHED.getValue());
                    break;
                case "AUDITING":
                    commentQuery.setStatus(CommentStatus.AUDITING.getValue());
                    break;
                case "RECYCLE":
                    commentQuery.setStatus(CommentStatus.RECYCLE.getValue());
                    break;
            }
        }
        if (type.equals("blog")) {
            listQueryBlogComments(commentQuery, pageNum, pageSize, model);
            return "admin/comments::blogComments";
        } else {
            listQueryPageComments(commentQuery, pageNum, pageSize, model);
            return "admin/comments::pageComments";
        }
    }

    private void listComments(Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        PageInfo<Comment> blogCommentPageInfo = new PageInfo<>(commentService.listBlogComments());
        List<Comment> blogCommentList = blogCommentPageInfo.getList();
        List<CommentDTO> blogComments = new LinkedList<>();
        if (blogCommentList.size() > 0) {
            for (Comment comment : blogCommentList) {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment, commentDTO);
                if (comment.getPostId() != null) {
                    Post post = postService.selectByPrimaryKey(comment.getPostId());
                    commentDTO.setPostTitle(post.getTitle());
                }
                blogComments.add(commentDTO);
            }
        }

        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        PageInfo<Comment> pageCommentPageInfo = new PageInfo<>(commentService.listPageComments());
        List<Comment> pageCommentList = pageCommentPageInfo.getList();
        List<CommentDTO> pageComments = new LinkedList<>();
        if (pageCommentList.size() > 0) {
            for (Comment comment : pageCommentList) {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment, commentDTO);
                if (comment.getPostId() != null) {
                    Post post = postService.selectByPrimaryKey(comment.getPostId());
                    commentDTO.setPageTitle(post.getTitle());
                }
                pageComments.add(commentDTO);
            }
        }
        model.addAttribute("blogComments", blogComments);
        model.addAttribute("blogCommentPageInfo", blogCommentPageInfo);
        model.addAttribute("pageComments", pageComments);
        model.addAttribute("pageCommentPageInfo", pageCommentPageInfo);
    }

    private void listBlogComments(Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        PageInfo<Comment> blogCommentPageInfo = new PageInfo<>(commentService.listBlogComments());
        List<Comment> blogCommentList = blogCommentPageInfo.getList();
        List<CommentDTO> blogComments = new LinkedList<>();
        for (Comment comment : blogCommentList) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            if (comment.getPostId() != null) {
                Post post = postService.selectByPrimaryKey(comment.getPostId());
                commentDTO.setPostTitle(post.getTitle());
            }
            blogComments.add(commentDTO);
        }
        model.addAttribute("blogComments", blogComments);
        model.addAttribute("blogCommentPageInfo", blogCommentPageInfo);
    }

    private void listPageComments(Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        PageInfo<Comment> pageCommentPageInfo = new PageInfo<>(commentService.listPageComments());
        List<Comment> pageCommentList = pageCommentPageInfo.getList();
        List<CommentDTO> pageComments = new LinkedList<>();
        for (Comment comment : pageCommentList) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            if (comment.getPostId() != null) {
                Post post = postService.selectByPrimaryKey(comment.getPostId());
                commentDTO.setPageTitle(post.getTitle());
            }
            pageComments.add(commentDTO);
        }
        model.addAttribute("pageComments", pageComments);
        model.addAttribute("pageCommentPageInfo", pageCommentPageInfo);
    }

    private void listQueryBlogComments(CommentQuery commentQuery, Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentService.listQueryBlogComments(commentQuery));
        List<Comment> blogCommentList = pageInfo.getList();
        List<CommentDTO> blogComments = new LinkedList<>();
        for (Comment comment : blogCommentList) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            if (comment.getPostId() != null) {
                Post post = postService.selectByPrimaryKey(comment.getPostId());
                commentDTO.setPostTitle(post.getTitle());
            }
            blogComments.add(commentDTO);
        }
        model.addAttribute("blogCommentPageInfo", pageInfo);
        model.addAttribute("blogComments", blogComments);
    }

    private void listQueryPageComments(CommentQuery commentQuery, Integer pageNum, Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentService.listQueryPageComments(commentQuery));
        List<Comment> pageCommentList = pageInfo.getList();
        List<CommentDTO> pageComments = new LinkedList<>();
        for (Comment comment : pageCommentList) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            if (comment.getPostId() != null) {
                Post post = postService.selectByPrimaryKey(comment.getPostId());
                commentDTO.setPageTitle(post.getTitle());
            }
            pageComments.add(commentDTO);
        }
        model.addAttribute("pageCommentPageInfo", pageInfo);
        model.addAttribute("pageComments", pageComments);
    }

//    @PostMapping("/comments/page/{id}/reply")
//    public String replyPageComment(@PathVariable Long id,
//                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                                   @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
//                                   @RequestParam(name = "content") String content,
//                                   @RequestParam(name = "postId") Long postId,
//                                   HttpServletRequest request,
//                                   Model model) {
//        User user = userService.selectByPrimaryKey(1);
//        Comment comment = new Comment();
//        comment.setType(1);
//        comment.setAuthor(user.getUsername());
//        comment.setEmail(user.getEmail());
//        comment.setIpAddress(request.getHeader("Host"));
//        comment.setContent(content);
//        comment.setStatus(CommentStatus.PUBLISHED.getValue());
//        comment.setUserAgent(request.getHeader("User-Agent"));
//        comment.setIsAdmin(true);
//        comment.setParentId(id);
//        comment.setPostId(postId);
//        comment.setCreateTime(DateUtil.date());
//        comment.setUpdateTime(DateUtil.date());
//        commentService.insertSelective(comment);
//        listComments(pageNum, pageSize, model);
//        return "/admin/comments";

//    }
//    @GetMapping("/comments/page/{id}/adopt")
//    public String adoptPageComment(@PathVariable Long id,
//                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                                   @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
//                                   Model model) {
//        commentService.toAdopt(id);
//        listComments(pageNum, pageSize, model);
//        return "/admin/comments::pageComments";

//    }
//    @GetMapping("/comments/page/{id}/recycle")
//    public String recyclePageComment(@PathVariable Long id,
//                                     @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                                     @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
//                                     Model model) {
//        commentService.toRecycle(id);
//        listPageComments(pageNum, pageSize, model);
//        return "/admin/comments::pageComments";

//    }
//    @GetMapping("/comments/page/{id}/reduction")
//    public String reductionPageComment(@PathVariable Long id,
//                                       @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                                       @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
//                                       Model model) {
//        commentService.toReduction(id);
//        listPageComments(pageNum, pageSize, model);
//        return "/admin/comments::pageComments";

//    }
//    @GetMapping("/comments/page/{id}/delete")
//    public String deletePageComment(@PathVariable Long id,
//                                    @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                                    @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
//                                    Model model) {
//        commentService.deleteByPrimaryKey(id);
//        listPageComments(pageNum, pageSize, model);
//        return "/admin/comments::pageComments";

//    }

}
