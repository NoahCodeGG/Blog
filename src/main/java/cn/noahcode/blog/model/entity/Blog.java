package cn.noahcode.blog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Data
public class Blog {
    private Integer id;

    private String title;

    private Integer status;

    private Date editTime;

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

    private Date createTime;

    private Date updateTime;

}