package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class PageSettingParam {
    private String summary;

    private Boolean disallowComment = false;
}
