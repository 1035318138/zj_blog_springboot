package cn.zj.mapper;

import cn.zj.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Mapper
public interface ArticleMapper {
	//查询所有文章
	List<Article> findAll();

	//查询文章总数量
	Long findAllCount();

	//条件查询
	Page<Article> findByPage(Integer page, Integer size);

	//前台查询条件=过滤查询
	Page<Article> findByPageForSite();

	//根据id查询文章
	Article findById(Long id);

	//新增文章
	void add(Article article);

	//更新文章
	void update(Article article);

	//删除
	void deleteById(Long id);

	//查询最后一条记录的id
	Long getLastId();

	//指定发布日期的文章
	List<Article> findArchivesByDate(String date);

	List<String> findArchivesDates();

	//标题模糊查询文章列表
	List<Article> findFuzzyByTitle(String title);

	//更新浏览数
	void addEyeCount(Long id);

	//根据分类名称 查询文章列表
	List<Article> findByCategory(String category);

	/**
	 * 查询所有文章的浏览量
	 *
	 * @return
	 */
	Long findAllEyeCount();
}
