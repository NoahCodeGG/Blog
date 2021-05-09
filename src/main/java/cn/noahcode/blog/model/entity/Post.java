package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 2021/2/6
 * @description
 */
@Data
public class Post {
    private Long id;

    private Integer type;

    private String title;

    private Integer status;

    private String slug;

    /**
     * 原始内容
     */
    private String originalContent;

    /**
     * 转换内容
     */
    private String formatContent;

    /**
     * 概要
     */
    private String summary;

    private Long visits;

    private Boolean disallowComment;

    private String password;

    private Boolean topPriority;

    private Long likes;

    private Long wordCount;

    private Date editTime;

    private Date createTime;

    private Date updateTime;
}