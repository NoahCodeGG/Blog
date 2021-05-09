package cn.noahcode.blog.model.dto;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Data
public class JournalWithCommentCountDTO extends JournalDTO {

    private Long commentCount;

}