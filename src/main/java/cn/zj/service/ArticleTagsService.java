package cn.zj.service;

import cn.zj.entity.ArticleTags;
import cn.zj.entity.Tags;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface ArticleTagsService extends BaseService<ArticleTags> {
	void deleteByArticleId(Long id);

	void deleteByTagsId(Long id);

	List<Tags> findByArticleId(Long id);

}
