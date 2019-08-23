package cn.zj.mapper;

import cn.zj.entity.DateCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/21.
 */
@Mapper
public interface DateCountMapper {
	void add(DateCount dateCount);

	/**
	 * 返回最近一周每日文章总数
	 *
	 * @return
	 */
	List<Long> findArticleCount();

	/**
	 * 返回最近一周每日总观看量
	 *
	 * @return
	 */
	List<Long> findEyeCount();

	/**
	 * 最近一个星期
	 *
	 * @return
	 */
	List<String> findWeek();

	/**
	 * 返回最近一星期的评论数
	 *
	 * @return
	 */
	List<Long> findCommentCount();

	/**
	 * 查询最近一个星期用户总量
	 *
	 * @return
	 */
	List<Long> findUserCount();
}
