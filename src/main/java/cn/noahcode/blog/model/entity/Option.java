package cn.noahcode.blog.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * @author NoahCode
 * @date 12/28/2020
 * @description
 */
@Data
public class Option {
    private Integer id;

    private Integer type;

    private String optionKey;

    private String optionValue;

    private Date createTime;

    private Date updateTime;
}