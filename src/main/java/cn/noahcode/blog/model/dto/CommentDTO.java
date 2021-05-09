package cn.noahcode.blog.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 12/29/2020
 * @description
 */
@Data
public class CommentDTO {

    private Long id;

    private Integer type;

    private String author;

    private String email;

    private String ipAddress;

    private String authorUrl;

    private String content;

    private Integer status;

    private String userAgent;

    private Long parentId;

    private String pageTitle;

    private Long postId;

    private String postTitle;

    private Boolean isAdmin;

    private Boolean allowNotification;

    private Date createTime;

}
