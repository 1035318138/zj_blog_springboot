package cn.zj.mapper;

import cn.zj.entity.Comments;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Mapper
public interface CommentsMapper {
	//查询评论数
	Long findAllCount();

	//查询所有评论
	List<Comments> findAll();

	//条件查询
	Page findByPage(Comments comments);

	//根据id查询
	Comments findById(Long id);

	//分页查询指定文章的评论数据
	Page<Comments> findCommentsList(@Param("article_id") Long article_id, @Param("sort") Long sort);

	//查询所有父级文章留言信息
	Page<Comments> findAllId(@Param("article_id") Long article_id, @Param("sort") Long sort);

	//查询文章评论量
	Long findCountByArticleId(Long id);

	//新增评论
	void add(Comments comments);

	//更新评论
	void update(Comments comments);

	//删除
	void delete(Long id);
}
