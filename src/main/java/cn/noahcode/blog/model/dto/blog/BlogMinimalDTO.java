package cn.noahcode.blog.model.dto.blog;

import lombok.Data;

import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class BlogMinimalDTO {

    private Integer id;

    private String title;

    private Integer status;

    private Date editTime;

    private Date createTime;

    private Date updateTime;
}
