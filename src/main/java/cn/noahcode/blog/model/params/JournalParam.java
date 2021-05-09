package cn.noahcode.blog.model.params;

import cn.noahcode.blog.model.enums.JournalType;
import lombok.Data;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
@Data
public class JournalParam {

    private String sourceContent;

    private JournalType type = JournalType.PUBLIC;
}