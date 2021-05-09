package cn.noahcode.blog.model.dto.post;

import cn.noahcode.blog.model.entity.Category;
import cn.noahcode.blog.model.entity.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class PostMinimalDTO {

    private Long id;

    private String title;

    private Integer status;

    private String slug;

    private Date editTime;

    private Date createTime;

    private Date updateTime;

    private List<Category> categories;

    private List<Tag> tags;
}
