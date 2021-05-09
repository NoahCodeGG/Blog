package cn.noahcode.blog.model.dto.post;

import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class PostDetailDTO extends PostSimpleDTO {

    private String originalContent;

    private String formatContent;

    private Long commentCount;
}
