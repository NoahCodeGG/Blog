package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/10/7
 * @description
 */
@Data
public class PasswordParam {

    private String oldPassword;

    private String newPassword;

}
