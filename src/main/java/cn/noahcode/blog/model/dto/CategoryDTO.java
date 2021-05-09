package cn.noahcode.blog.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class CategoryDTO {

    private Long id;

    private String name;

    private String description;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;

}
