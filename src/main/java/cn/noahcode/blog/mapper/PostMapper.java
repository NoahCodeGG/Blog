package cn.noahcode.blog.mapper;

import cn.noahcode.blog.model.entity.Post;
import cn.noahcode.blog.model.params.PostQuery;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

/**
 * @author NoahCode
 * @date 2021/2/6
 * @description
 */
@Mapper
public interface PostMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Post record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Post record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Post selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Post record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Post record);

    List<Post> listBlog();

    int toRecycle(Long id);

    int toReduction(Long id);

    int blogCount();

    int visitCount();

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