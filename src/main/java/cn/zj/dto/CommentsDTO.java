package cn.zj.dto;

import cn.zj.entity.Comments;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 *
 * 封装评论信息
 */
public class CommentsDTO {
	private Comments parents;//父级留言信息
	private List<Comments> childList;//子留言信息

	public CommentsDTO() {
	}

	public CommentsDTO(Comments parents, List<Comments> childList) {
		this.parents = parents;
		this.childList = childList;
	}

	public Comments getParents() {
		return parents;
	}

	public void setParents(Comments parents) {
		this.parents = parents;
	}

	public List<Comments> getChildList() {
		return childList;
	}

	public void setChildList(List<Comments> childList) {
		this.childList = childList;
	}

	@Override
	public String toString() {
		return "CommentsDTO{" +
				"parents=" + parents +
				", childList=" + childList +
				'}';
	}
}
