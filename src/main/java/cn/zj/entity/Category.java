package cn.zj.entity;

import lombok.Data;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Data
public class Category {
	private Long id;
	private String name;

	public Category() {
		this(null);
	}

	public Category(String name) {
		this.name = name;
	}
}
