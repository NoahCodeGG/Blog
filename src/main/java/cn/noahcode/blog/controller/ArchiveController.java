package cn.noahcode.blog.controller;

import cn.noahcode.blog.model.dto.post.PostSimpleDTO;
import cn.noahcode.blog.model.entity.Menu;
import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.service.MenuService;
import cn.noahcode.blog.service.OptionService;
import cn.noahcode.blog.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author NoahCode
 * @date 1/18/2021
 * @description
 */
@Controller
@RequestMapping("/archives")
public class ArchiveController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String archive(Model model) {
        Map<Integer, Map<Integer, List<PostSimpleDTO>>> yearArchives = new LinkedHashMap<>();
        Integer[] years = postService.listYear();
        for (Integer year : years) {
            Map<Integer, List<PostSimpleDTO>> monthArchives = new LinkedHashMap<>();
            Integer[] months = postService.listMouth(year);
            for (Integer month : months) {
                List<Post> mouthArchiveList = postService.listMouthArchive(year, month);
                List<PostSimpleDTO> monthDTOList = new LinkedList<>();
                for (Post mouthArchive : mouthArchiveList) {
                    PostSimpleDTO postSimpleDTO = new PostSimpleDTO();
                    BeanUtils.copyProperties(mouthArchive, postSimpleDTO);
                    monthDTOList.add(postSimpleDTO);
                }
                monthArchives.put(month, monthDTOList);
            }
            yearArchives.put(year, monthArchives);
            System.out.println(yearArchives);
        }
        listMenus(model);
        model.addAttribute("archives", yearArchives);
        return "archives";
    }

    public void listMenus(Model model) {
        String defaultMenuTeam = optionService.selectByOptionKey("defaultMenuTeam").getOptionValue();
        List<Menu> menus = menuService.listPages(defaultMenuTeam);
        model.addAttribute("menus", menus);
    }

}
