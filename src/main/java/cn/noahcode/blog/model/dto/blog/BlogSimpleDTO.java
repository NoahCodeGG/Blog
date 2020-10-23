package cn.noahcode.blog.model.dto.blog;

import cn.noahcode.blog.model.entity.Category;
import cn.noahcode.blog.model.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class BlogSimpleDTO extends BlogMinimalDTO {

    private String summary;

    private Long visits;

    private Boolean disallowComment;

    private String password;

    private Boolean topPriority;

    private Long likes;

    private Long wordCount;

    private List<Category> categories;

    private List<Tag> tags;

}
