package cn.zj.service;

import cn.zj.dto.ArticleArchives;
import cn.zj.dto.PageBean;
import cn.zj.entity.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface ArticleService extends BaseService<Article> {
	/**
	 * 分页查询(为前端服务)
	 *
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageInfo<Article> findByPage(Integer pageCode, Integer pageSize);


	/**
	 * 根据分类名称查询文章列表
	 *
	 * @param category
	 * @return
	 */
	List<Article> findByCategory(String category);

	/**
	 * 查询按照时间归档的整合数据
	 * 格式: [{"2019-01-01", [{article1}, {article2}, ...]}, {"2019-02-01", [{article1}, {article2}, ...]}]
	 *
	 * @return
	 */
	List<ArticleArchives> findArchives();

	List<String> findArchivesDates();

	/**
	 * 根据文章标题查询文章(模糊查询)
	 *
	 * @param title
	 * @return
	 */
	List<Article> findFuzzyByTitle(String title);

	/**
	 * 增加浏览量
	 */
	void addEyeCount(Long id);
}
