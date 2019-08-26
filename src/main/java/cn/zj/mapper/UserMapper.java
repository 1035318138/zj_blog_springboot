package cn.zj.mapper;

import cn.zj.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/4.
 */
@Mapper
public interface UserMapper {
	//条件查询
	Page findByPage(User user);

	//根据id查询用户
	User findById(Long id);

	//添加用户信息
	void add(User user);

	//更新用户信息
	void update(User user);

	//删除用户
	void deleteById(Long id);

	//根据用户名查询用户
	User findByName(String username);

	//根据用户名查密码
	String getPassWordByName(String username);

	/**
	 * 查询总用户数
	 *
	 * @return
	 */
	Long findUserCount();

	List<User> findByBlurryName(String name);

	List<User> findAll();

	String findPasswordById(Long id);
}
