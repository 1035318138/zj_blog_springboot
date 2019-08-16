package cn.zj.mapper;

import cn.zj.entity.Article;
import cn.zj.entity.Category;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Mapper
public interface CategoryMapper {
	//查询所有分类
	List<Category> findAll();

	//条件查询
	Page<Category> findByPage(Category category);

	//根据id查询分类
	Category findById(Long id);

	//增加分类
	void add(Category category);

	//根据名字查询分类是否存在
	Category findByName(String name);

	//更新
	void update(Category category);

	//删除
	void delete(Long id);

	//根据文章id查询分类
	List<Category> findCategoryByArticleId(Long id);

	//是否存在
	Boolean exists(String name);

	//根据分类查询文章列表
	List<Article> findArticleByCategory(Long id);
}
