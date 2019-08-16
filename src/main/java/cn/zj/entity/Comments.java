package cn.zj.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Data
public class Comments {
	private Long id;
	private Long p_id;
	private Long c_id;
	private String article_title;
	private Long article_id;
	private String author;
	private String author_id;
	private String email;
	private String content;
	private Date time;
	private String url;
	private String state;
	private Long sort;
}
