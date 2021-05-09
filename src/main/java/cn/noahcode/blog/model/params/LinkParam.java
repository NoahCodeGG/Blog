package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class LinkParam {
    private Integer id;

    private String name;

    private String url;

    private String logo;

    private String description;

    private Integer priority;
}
