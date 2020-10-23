package cn.noahcode.blog.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String nickname;

    private String email;

    private String avatar;

    private String description;

    private Date createTime;

    private Date updateTime;

}
