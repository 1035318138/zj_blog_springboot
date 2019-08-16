package cn.zj.entity;

import lombok.Data;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Data
public class Tags {
	private Long id;
	private String name;

	public Tags() {
	}

	public Tags(String name) {
		this.name = name;
	}
}
