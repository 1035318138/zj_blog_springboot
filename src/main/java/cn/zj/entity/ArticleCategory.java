package cn.zj.entity;

import lombok.Data;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Data
public class ArticleCategory {
	private Long id;
	private Long article_id;
	private Long category_id;

	public ArticleCategory() {
		this(null, null, null);
	}

	public ArticleCategory(Long article_id, Long category_id) {
		this(null, article_id, category_id);
	}

	public ArticleCategory(Long id, Long article_id, Long category_id) {
		this.id = id;
		this.article_id = article_id;
		this.category_id = category_id;
	}
}
