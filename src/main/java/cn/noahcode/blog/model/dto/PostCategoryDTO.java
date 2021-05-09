package cn.noahcode.blog.model.dto;

import cn.noahcode.blog.model.entity.Category;
import cn.noahcode.blog.model.entity.PostsCategory;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NoahCode
 * @date 2020/10/9
 * @description
 */
@Data
public class PostCategoryDTO {

    Map<Long, Boolean> postCategories;

    public PostCategoryDTO(List<Category> categories, List<PostsCategory> postsCategories) {
        HashMap<Long, Boolean> postCategoriesMap = new HashMap<>();
        for (Category category : categories) {
            loop:
            for (PostsCategory postsCategory : postsCategories) {
                if (category.getId().equals(postsCategory.getCategoryId())) {
                    postCategoriesMap.put(category.getId(), true);
                    break loop;
                } else {
                    postCategoriesMap.put(category.getId(), false);
                }
            }
        }
        this.postCategories = postCategoriesMap;
    }

}
