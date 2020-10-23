package cn.noahcode.blog.model.params;

import lombok.Data;

/**
 * @author NoahCode
 * @date 2020/10/7
 * @description
 */
@Data
public class UserParam {

    private String username;

    private String nickname;

    private String email;

    private String password;

    private String avatar;

    private String description;

}
