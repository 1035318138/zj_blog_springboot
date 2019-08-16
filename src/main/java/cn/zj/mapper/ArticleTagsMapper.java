package cn.zj.mapper;

import cn.zj.entity.ArticleTags;
import cn.zj.entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Mapper
public interface ArticleTagsMapper {
	//新增 文章-标签 关联
	void add(ArticleTags articleTags);

	//查询是否存在
	Boolean exists(@Param("article_id") Long article_id, @Param("tags_id") Long tags_id);

	//根据文章id查询标签信息
	List<Tags> findByArticleId(Long id);

	//根据文章id取消关联
	void deleteByArticleId(Long id);

	//根据标签id取消关联
	void deleteByTagsId(Long id);
}
