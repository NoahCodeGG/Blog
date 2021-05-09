package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/4/2021
 * @description
 */
@Data
public class ReplyParam {
    private Long postId;

    private String content;
}
