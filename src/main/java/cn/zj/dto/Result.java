package cn.zj.dto;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public class Result {
	private Integer code;//状态码
	private Object data;//返回的数据

	public Result() {
	}

	public Result(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
