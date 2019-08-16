package cn.zj.service;

import cn.zj.entity.Tags;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface TagsService extends BaseService<Tags>{
	Tags findByName(String name);

	List<Tags> findByArticleId(Long id);

	Boolean exists(String name);
}
