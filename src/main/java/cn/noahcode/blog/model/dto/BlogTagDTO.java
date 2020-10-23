package cn.noahcode.blog.model.dto;

import cn.noahcode.blog.model.entity.BlogsTag;
import cn.noahcode.blog.model.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author NoahCode
 * @date 2020/10/9
 * @description
 */
@Data
public class BlogTagDTO {

    public String blogTags;


    public BlogTagDTO(List<Tag> tags, List<BlogsTag> blogsTags) {
        StringBuffer blogTags = new StringBuffer();
        boolean flag = false;
        for (Tag tag : tags) {
            for (BlogsTag blogsTag : blogsTags) {
                if (tag.getId().equals(blogsTag.getTagId())) {
                    if (flag) {
                        blogTags.append(",");
                    } else {
                        flag = true;
                    }
                    blogTags.append(tag.getId());
                }
            }
        }
        this.blogTags = blogTags.toString();
    }
}
