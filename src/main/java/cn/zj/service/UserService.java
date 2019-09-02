package cn.zj.service;

import cn.zj.entity.User;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/4.
 */
public interface UserService extends BaseService<User> {
//	List<User> users();

	void add(User user, Boolean IS_ADMIN) throws UnknownHostException;

	User findByName(String username);

	String getPassWordByName(String username);

	List<User> findByBlurryName(String name);

	Boolean check(Long id, String password);
}
