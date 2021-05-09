package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.dto.PageDTO;
import cn.noahcode.blog.model.dto.post.PostDetailDTO;
import cn.noahcode.blog.model.entity.Option;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.model.enums.PostStatus;
import cn.noahcode.blog.model.params.PageParam;
import cn.noahcode.blog.model.params.PageSettingParam;
import cn.noahcode.blog.service.CommentService;
import cn.noahcode.blog.service.OptionService;
import cn.noahcode.blog.service.PostService;
import cn.noahcode.blog.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
@Controller
@RequestMapping("/admin/pages")
public class PageController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String page(@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                       @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                       Model model) {
        listPages(model, pageNum, pageSize);
        return "admin/pages";
    }

    @GetMapping("/input")
    public String input() {
        return "admin/pages-input";
    }

    @GetMapping("/{id}/input")
    public String editPage(@PathVariable Long id, Model model) {
        Post post = postService.selectByPrimaryKey(id);
        PostDetailDTO page = new PostDetailDTO();
        BeanUtils.copyProperties(post, page);
        model.addAttribute("page", page);
        return "admin/pages-input";
    }

    @PostMapping("")
    public String post(@RequestBody PageParam pageParam) {
        Post post = new Post();
        BeanUtils.copyProperties(pageParam, post);
        Long postId = post.getId();
        switch (pageParam.getStatus()) {
            case PUBLISHED:
                post.setStatus(PostStatus.PUBLISHED.getValue());
                break;
            case RECYCLE:
                post.setStatus(PostStatus.RECYCLE.getValue());
                break;
            case INTIMATE:
                post.setStatus(PostStatus.INTIMATE.getValue());
                break;
            default:
                post.setStatus(PostStatus.DRAFT.getValue());
        }
        post.setFormatContent(MarkdownUtils.markdownToHtml(post.getOriginalContent()));
        if (postId == null) {
            post.setType(1);
            post.setCreateTime(DateUtil.date());
            post.setEditTime(DateUtil.date());
            post.setUpdateTime(DateUtil.date());
            postService.insertSelective(post);
        } else {
            post.setEditTime(DateUtil.date());
            post.setUpdateTime(DateUtil.date());
            postService.updateByPrimaryKeySelective(post);
        }
        return "redirect:/admin/pages";
    }

    @GetMapping("/{id}/recycle")
    public String recycled(@PathVariable Long id,
                           @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                           Model model) {
        postService.toRecycle(id);
        listPages(model, pageNum, pageSize);
        return "admin/pages::pages";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                         Model model) {
        commentService.deleteByPostId(id);
        postService.deleteByPrimaryKey(id);
        listPages(model, pageNum, pageSize);
        return "admin/pages::pages";
    }

    @GetMapping("/{id}/reduction")
    public String reduction(@PathVariable Long id,
                            @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                            Model model) {
        postService.toReduction(id);
        listPages(model, pageNum, pageSize);
        return "admin/pages::pages";
    }

    @PostMapping("/{id}/setting")
    public String setting(@PathVariable Long id,
                          @RequestBody PageSettingParam pageSettingParam,
                          @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                          @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize,
                          Model model) {
        Post post = new Post();
        BeanUtils.copyProperties(pageSettingParam, post);
        post.setId(id);
        post.setEditTime(DateUtil.date());
        post.setUpdateTime(DateUtil.date());
        postService.updateByPrimaryKeySelective(post);
        listPages(model, pageNum, pageSize);
        return "admin/pages::pages";
    }

    private void listPages(Model model, Integer pageNum, Integer pageSize) {
        String blogUrl = optionService.selectByOptionKey("blogUrl").getOptionValue();
        LinkedList<PageDTO> independents = new LinkedList<>();
//        PageDTO photos = new PageDTO();
//        Option photosTitle = optionService.selectByOptionKey("photosTitle");
//        photos.setTitle(photosTitle.getOptionValue());
//        Option photosPageSize = optionService.selectByOptionKey("photosPrefix");
//        photos.setUrl(photosPageSize.getOptionValue());
//        independents.add(photos);

        PageDTO journals = new PageDTO();
        Option journalsTitle = optionService.selectByOptionKey("journalsTitle");
        journals.setTitle(journalsTitle.getOptionValue());
        Option journalsPrefix = optionService.selectByOptionKey("journalsPrefix");
        journals.setUrl(journalsPrefix.getOptionValue());
        independents.add(journals);

        PageDTO links = new PageDTO();
        Option linksTitle = optionService.selectByOptionKey("linksTitle");
        links.setTitle(linksTitle.getOptionValue());
        Option linksPrefix = optionService.selectByOptionKey("linksPrefix");
        links.setUrl(linksPrefix.getOptionValue());
        independents.add(links);

        model.addAttribute("independents", independents);
        PageHelper.startPage(pageNum, pageSize, "edit_time desc");
        PageInfo<Post> pageInfo = new PageInfo<>(postService.listPage());
        List<Post> posts = pageInfo.getList();
        List<PostDetailDTO> customizes = new LinkedList<>();
        for (Post post : posts) {
            PostDetailDTO postDetailDTO = new PostDetailDTO();
            BeanUtils.copyProperties(post, postDetailDTO);
            postDetailDTO.setCommentCount(commentService.selectByPostIdBlogComments(post.getId()));
            customizes.add(postDetailDTO);
        }
        model.addAttribute("customizes", customizes);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogUrl", blogUrl);
    }
}
