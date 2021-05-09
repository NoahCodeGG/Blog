package cn.noahcode.blog.model.dto;

import cn.noahcode.blog.model.enums.JournalType;
import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Data
public class JournalDTO {

    private Integer id;

    private String sourceContent;

    private String content;

    private Long likes;

    private Date createTime;

    private JournalType type;
}