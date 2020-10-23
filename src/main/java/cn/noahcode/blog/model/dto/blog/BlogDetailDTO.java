package cn.noahcode.blog.model.dto.blog;

import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/9/30
 * @description
 */
@Data
public class BlogDetailDTO extends BlogSimpleDTO {

    private String originalContent;

    private String formatContent;

    private Long commentCount;

}
