package cn.noahcode.blog.model.params;

import cn.noahcode.blog.model.enums.OptionType;
import lombok.Data;

/**
 * @author NoahCode
 * @date 12/26/2020
 * @description
 */
@Data
public class OptionParam {

    private String key;

    private String value;

    private OptionType type;

}
