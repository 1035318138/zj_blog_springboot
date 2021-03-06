package cn.zj.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Data
public class Comments {
	private Long id;
	private Long p_id;
	private Long c_id;
	private String author_avatar;
	private Long article_id;
	private String author;
	private Long author_id;
	private String email;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	private String url;
	private String state;
	private Long sort;
}
