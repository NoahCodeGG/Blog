package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Data
public class Journal {
    private Integer id;

    private Integer type;

    private String content;

    private String sourceContent;

    private Long likes;

    private Date createTime;

    private Date updateTime;
}