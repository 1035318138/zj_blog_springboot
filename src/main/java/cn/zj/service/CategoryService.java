package cn.zj.service;

import cn.zj.entity.Article;
import cn.zj.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface CategoryService extends BaseService<Category>{
	Category findByName(String name);

	Category findCategoryByArticleId(Long id);

	Boolean exists(String name);

//	PageInfo<Article> findArticleByCategory(Long id, Integer pageNum, Integer pageSize);
}
