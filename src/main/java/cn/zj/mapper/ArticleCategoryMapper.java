package cn.zj.mapper;

import cn.zj.entity.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Mapper
public interface ArticleCategoryMapper {
	//增
	void add(ArticleCategory articleCategory);

	//查询是否存在
	Boolean exists(@Param("article_id") Long article_id, @Param("category_id") Long category_id);

	//根据文章id撤销关联
	void deleteByArticleId(Long article_id);

	//根据分类id撤销关联
	void deleteByCategoryId(Long category_id);

	//根据文章id更新分类
	void updateByAritlceId(@Param("cid")Long category_id, @Param("aid") Long article_id);
}
