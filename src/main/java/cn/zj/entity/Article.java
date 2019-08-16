package cn.zj.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Sept.05 on 2019/8/4.
 */
@Data
public class Article {
	private Long id;
	private String title;
	private String title_pic;
	private String author;
	private String content;
	private String leave_message;
	private String origin;
	private String state;
	private Integer eye_count;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date publish_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date edit_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	//非数据库字段属性
	private String category;//分类
	private String tags;//标签
}
