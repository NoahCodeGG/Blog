package cn.noahcode.blog.model.params;

import cn.noahcode.blog.model.enums.PostStatus;
import lombok.Data;

import java.util.Set;

/**
 * @author NoahCode
 * @date 2020/10/5
 * @description
 */
@Data
public class BlogParam {

    private Long id;

    private String title;

    private PostStatus status = PostStatus.DRAFT;

    private String slug;

    private String originalContent;

    private String summary;

    private Boolean disallowComment = false;

    private String password;

    private boolean topPriority = false;

    private Set<Long> tagIds;

    private Set<Long> categoryIds;

}
