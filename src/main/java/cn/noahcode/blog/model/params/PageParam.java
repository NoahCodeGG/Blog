package cn.noahcode.blog.model.params;

import cn.noahcode.blog.model.enums.PostStatus;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class PageParam {
    private Long id;

    private String title;

    private String slug;

    private PostStatus status = PostStatus.DRAFT;

    private String originalContent;

    private String summary;

    private Boolean disallowComment = false;

}
