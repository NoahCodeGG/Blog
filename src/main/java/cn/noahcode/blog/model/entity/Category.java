package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
@Data
public class Category {
    private Integer id;

    private String name;

    private String description;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;
}