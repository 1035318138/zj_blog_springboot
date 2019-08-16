package cn.zj.entity;

import lombok.Data;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Data
public class ArticleTags {
	private Long id;
	private Long article_id;
	private Long tags_id;

	public ArticleTags() {
	}

	public ArticleTags(Long article_id, Long tags_id) {
		this.article_id = article_id;
		this.tags_id = tags_id;
	}
}
