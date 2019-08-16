package cn.zj.mapper;

import cn.zj.entity.Tags;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/5.
 */
@Mapper
public interface TagsMapper {
	//查询总数量
	Long findAllCount();

	//查询所有
	List<Tags> findAll();

	//条件查询
	Page findByPage(Tags tags);

	//根据id查询
	Tags findById(Long id);

	//增加标签
	void add(Tags tags);

	//查询是否存在该标签
	Boolean exists(String name);

	//更新标签
	void update(Tags tags);

	//移除标签
	void delete(Long id);

	//根据名字查询
	Tags findByName(String name);

	//根据文章id查询标签
	List<Tags> findByArticleId(Long id);
}
