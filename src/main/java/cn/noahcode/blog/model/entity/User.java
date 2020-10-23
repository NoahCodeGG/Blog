package cn.noahcode.blog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String avatar;

    private String description;

    private Date createTime;

    private Date updateTime;
}