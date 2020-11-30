package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 11/29/2020
 * @description
 */
@Data
public class Categories {
    private Integer id;

    private String name;

    private String description;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;
}