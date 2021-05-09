package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class CategoryParam {
    private Integer id;

    private String name;

    private String description;

    private Integer parentId;

}
