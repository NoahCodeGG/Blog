package cn.noahcode.blog.model.dto;

import cn.noahcode.blog.model.entity.BlogsCategory;
import cn.noahcode.blog.model.entity.Category;
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
public class BlogCategoryDTO {

    Map<Integer, Boolean> blogCategories;

    public BlogCategoryDTO(List<Category> categories, List<BlogsCategory> blogCategories) {
        HashMap<Integer, Boolean> blogCategoriesMap = new HashMap<>();
        for (Category category : categories) {
            loop:
            for (BlogsCategory blogsCategory : blogCategories) {
                if (category.getId().equals(blogsCategory.getCategoryId())) {
                    blogCategoriesMap.put(category.getId(), true);
                    break loop;
                } else {
                    blogCategoriesMap.put(category.getId(), false);
                }
            }
        }
        this.blogCategories = blogCategoriesMap;
    }

}
