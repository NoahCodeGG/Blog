package cn.noahcode.blog.model.dto;

import lombok.Data;

/**
 * @author NoahCode
 * @date 1/20/2021
 * @description
 */
@Data
public class LinkDTO {

    private Integer id;

    private String name;

    private String url;

    private String logo;

    private String description;

    private Integer priority;

}