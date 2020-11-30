package cn.noahcode.blog.model.params;

import cn.noahcode.blog.model.enums.BlogStatus;
import lombok.Data;

import java.util.Set;

/**
 * @author NoahCode
 * @date 2020/10/5
 * @description
 */
@Data
public class BlogParam {

    private Integer id;

    private String title;

    private BlogStatus status = BlogStatus.DRAFT;

    private String originalContent;

    private String summary;

    private Boolean disallowComment = false;

    private String password;

    private boolean topPriority = false;

    private Set<Integer> tagIds;

    private Set<Integer> categoryIds;

}
