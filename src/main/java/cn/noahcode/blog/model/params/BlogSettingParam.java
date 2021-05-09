package cn.noahcode.blog.model.params;

import lombok.Data;

import java.util.Set;

/**
 * @author NoahCode
 * @date 12/1/2020
 * @description
 */
@Data
public class BlogSettingParam {

    private String slug;

    private String summary;

    private Boolean disallowComment = false;

    private String password;

    private boolean topPriority = false;

    private Set<Long> tagIds;

    private Set<Long> categoryIds;

}
