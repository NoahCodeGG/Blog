package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 2021/2/10
 * @description
 */
@Data
public class CommentQuery {

    /**
     * Keyword.
     */
    private String keyword;

    /**
     * Comment status.
     */
    private Integer status;
}
