package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class Link {
    private Integer id;

    private String name;

    private String url;

    private String logo;

    private String description;

    private String team;

    private Integer priority;

    private Date createTime;

    private Date updateTime;
}