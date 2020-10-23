package cn.noahcode.blog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Data
public class BlogsCategory {

    private Integer id;

    private Integer categoryId;

    private Integer blogId;

    private Date createTime;

    private Date updateTime;

}