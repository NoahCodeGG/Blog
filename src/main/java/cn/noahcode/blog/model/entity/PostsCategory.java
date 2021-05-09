package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class PostsCategory {
    private Long id;

    private Long categoryId;

    private Long postId;

    private Date createTime;

    private Date updateTime;
}