package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class PostsTag {
    private Long id;

    private Long postId;

    private Long tagId;

    private Date createTime;

    private Date updateTime;
}