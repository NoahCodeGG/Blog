package cn.noahcode.blog.service;

import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.model.params.PostQuery;

import java.util.List;

/**
 * @author NoahCode
 * @date 1/19/2021
 * @description
 */
public interface PostService {

    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    int toRecycle(Long id);

    int toReduction(Long id);

    int blogCount();

    int visitCount();

    List<Post> listBlog();

    List<Post> listNewBlog();

    List<Post> listPublishedBlog();

    int addVisitor(Long id);

    List<Post> listPage();

    Integer[] listYear();

    Integer[] listMouth(Integer year);

    List<Post> listMouthArchive(Integer year, Integer mouth);

    Post selectBySlug(String slug);

    List<Post> listQueryBlog(PostQuery postQuery);
}




