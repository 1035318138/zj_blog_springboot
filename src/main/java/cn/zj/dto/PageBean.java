package cn.zj.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */

public class PageBean<T> implements Serializable{
	//当前页
	private long total;
	//当前页记录
	private List<T> rows;

	public PageBean(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
