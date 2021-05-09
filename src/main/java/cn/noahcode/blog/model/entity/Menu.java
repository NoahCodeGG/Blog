package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/16/2021
 * @description
 */
@Data
public class Menu {
    /**
     *
     */
    private Long id;

    private String icon;

    private String name;

    private Integer parentId;

    private Integer priority;

    private String target;

    private String team;

    private String url;

    private Date createTime;

    private Date updateTime;
}