package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/10/6
 * @description
 */
@Data
public class BlogsTag {
    private Integer id;

    private Integer blogId;

    private Integer tagId;

    private Date createTime;

    private Date updateTime;
}