package cn.noahcode.blog.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/30/2021
 * @description
 */
@Data
public class CommentWithChildrenDTO extends CommentDTO {
    Boolean hasChildren;
    List<CommentDTO> children;
}
