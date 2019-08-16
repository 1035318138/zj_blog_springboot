package cn.zj.service;

import cn.zj.entity.ArticleCategory;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface ArticleCategoryService extends BaseService<ArticleCategory>{
	void deleteByArticleId(Long article_id);

	void deleteByCategoryId(Long category_id);
}
