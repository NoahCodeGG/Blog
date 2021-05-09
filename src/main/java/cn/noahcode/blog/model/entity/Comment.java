package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/4/2021
 * @description
 */
@Data
public class Comment {
    private Long id;

    private Integer type;

    private String author;

    private String email;

    private String ipAddress;

    private String authorUrl;

    private String content;

    private Integer status;

    private String userAgent;

    private Boolean isAdmin;

    private Boolean allowNotification;

    private Boolean topPriority;

    private Long parentId;

    private Long postId;

    private Date createTime;

    private Date updateTime;
}