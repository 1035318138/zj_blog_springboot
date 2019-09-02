package cn.zj.service;

import cn.zj.dto.PageBean;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
public interface BaseService<T> {
	/**
	 * 查询总数
	 *
	 * @return
	 */
	Long findAllCount();

	/**
	 * 查询所有
	 *
	 * @return
	 */
	List<T> findAll();

//	/**
//	 * 分页查询
//	 *
//	 * @param t        查询条件
//	 * @param pageCode 当前页
//	 * @param pageSize 每页的记录数
//	 * @return
//	 */
//	PageBean findByPage(Integer pageCode, Integer pageSize);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	T findById(Long id);

	/**
	 * 增加
	 *
	 * @param t
	 */
	void add(T t) throws UnknownHostException;

	/**
	 * 修改
	 *
	 * @param t
	 */
	void update(T t);

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	void delete(Long... ids);
}
