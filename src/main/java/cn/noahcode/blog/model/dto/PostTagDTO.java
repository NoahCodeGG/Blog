package cn.noahcode.blog.model.dto;

import cn.noahcode.blog.model.entity.PostsTag;
import cn.noahcode.blog.model.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/9
 * @description
 */
@Data
public class PostTagDTO {

    public String postTags;


    public PostTagDTO(List<Tag> tags, List<PostsTag> postsTags) {
        StringBuffer postTags = new StringBuffer();
        boolean flag = false;
        for (Tag tag : tags) {
            for (PostsTag postsTag : postsTags) {
                if (tag.getId().equals(postsTag.getTagId())) {
                    if (flag) {
                        postTags.append(",");
                    } else {
                        flag = true;
                    }
                    postTags.append(tag.getId());
                }
            }
        }
        this.postTags = postTags.toString();
    }
}
