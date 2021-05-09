package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 12/31/2020
 * @description
 */
@Data
public class Tag {
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;
}