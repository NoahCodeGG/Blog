package cn.noahcode.blog.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.noahcode.blog.model.entity.Option;
import cn.noahcode.blog.model.enums.OptionType;
import cn.noahcode.blog.service.OptionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NoahCode
 * @date 12/26/2020
 * @description
 */
@Controller
@RequestMapping("/admin")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/options")
    public String options(Model model) {
        listOption(model);
        return "admin/options";
    }

    @PostMapping("/options/saving")
    public String savingOptions(@RequestBody JSONObject options, Model model) {
        for (Map.Entry<String, Object> entry : options.entrySet()) {
            Option option = new Option();
            option.setOptionKey(entry.getKey());
            option.setOptionValue((String) entry.getValue());
            option.setType(OptionType.INTERNAL.getValue());
            option.setUpdateTime(DateUtil.date());
            Option o = optionService.selectByOptionKey(option.getOptionKey());
            if (o != null) {
                optionService.updateByOptionKey(option);
            } else {
                option.setCreateTime(DateUtil.date());
                optionService.insertSelective(option);
            }
        }
        listOption(model);
        return "admin/options";
    }

    private void listOption(Model model) {
        List<Map<String, String>> optionList = optionService.listOption();
        Map<String, String> options = new HashMap<>();
        for (Map<String, String> option : optionList) {
            options.put(option.get("option_key"), option.get("option_value"));
        }
        model.addAttribute("options", options);
    }

}

