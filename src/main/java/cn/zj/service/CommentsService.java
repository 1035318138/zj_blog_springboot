package cn.zj.service;

import cn.zj.dto.PageBean;
import cn.zj.entity.Comments;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface CommentsService extends BaseService<Comments> {
	/**
	 * 分页查询并过滤留言数据
	 *
	 * @param pageCode  当前页
	 * @param pageSize  每页显示的记录数
	 * @param articleId 当前访问的文章id
	 * @param sort      sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
	 * @return
	 */
	PageBean findCommentsList(Integer pageCode, Integer pageSize, Long articleId, Long sort);

	/**
	 * 查询指定文章下的评论数
	 *
	 * @param articleId
	 * @return
	 */
	Long findCountByArticle(Long articleId);

	/**
	 * 根据文章id查询评论列表
	 *
	 * @param id
	 * @return
	 */
	List<Comments> findByArticleId(Long id);

	/**
	 * 最近5条留言记录
	 *
	 * @return
	 */
	List<Comments> findByRecent();

	PageInfo<Comments> findByPage(Integer pageNum, Integer pageSize);

	void updateStatus(Comments comments);
}
