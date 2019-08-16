package cn.zj.enums;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public enum  ResultEnums {
	SUCCESS("操作成功"),
	ERROR("操作失败"),
	INNER_ERROR("操作失败"),
	INPUT_ERROR("操作失败"),
	LOGIN_SUCCESS("操作失败"),
	LOGIN_UNKONW("操作失败"),
	LOGIN_ERROR("操作失败"),
	LOGIN_CHECK_ERROR("操作失败"),
	PARAM_ERROR("操作失败");

	private String info;

	public String getInfo() {
		return info;
	}

	ResultEnums(String info) {
		this.info = info;
	}
}
