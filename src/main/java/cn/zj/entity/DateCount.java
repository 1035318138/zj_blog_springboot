package cn.zj.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Sept.05 on 2019/8/21.
 */
@Data
public class DateCount {
	private Long id;
	private Date today;
	private Long article_count;
	private Long eye_count;
	private Long comment_count;
	private Long user_count;

	public DateCount() {
	}

	public DateCount(Long id, Date today, Long article_count, Long eye_count, Long comment_count, Long user_count) {
		this.id = id;
		this.today = today;
		this.article_count = article_count;
		this.eye_count = eye_count;
		this.comment_count = comment_count;
		this.user_count = user_count;
	}
}
