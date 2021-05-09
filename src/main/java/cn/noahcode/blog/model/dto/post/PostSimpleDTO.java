package cn.noahcode.blog.model.dto.post;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/16/2021
 * @description
 */
@Data
public class PostSimpleDTO extends PostMinimalDTO {
    private String summary;

    private String thumbnail;

    private Long visits;

    private Boolean disallowComment;

    private String password;

    private String template;

    private Boolean topPriority;

    private Long likes;

    private Long wordCount;

}
