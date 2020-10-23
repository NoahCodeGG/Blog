package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/9/29
 * @description
 */
@Data
public class Tag {
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;
}