package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/31/2021
 * @description
 */
@Data
public class CommentParam {

    private String author;

    private String email;

    private String authorUrl = null;

    private String content;

    private Long postId;

    private Long parentId = 0L;

    private Boolean allowNotification = true;

}
