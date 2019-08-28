package cn.zj.service.impl;

import cn.zj.dto.PageBean;
import cn.zj.entity.User;
import cn.zj.mapper.UserMapper;
import cn.zj.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Sept.05 on 2019/8/4.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

//	@Override
//	public List<User> users() {
//		return userMapper.findByPage();
//	}

	@Override
	public User findByName(String username) {
		User user = userMapper.findByName(username);
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encode = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encode);
		return user;
	}

	@Override
	public String getPassWordByName(String username) {
		return userMapper.getPassWordByName(username);
	}

	@Override
	public List<User> findByBlurryName(String name) {
		return userMapper.findByBlurryName(name);
	}

	@Override
	public Boolean check(Long id, String password) {
		return userMapper.findPasswordById(id).equals(password);
	}

	@Override
	public Long findAllCount() {
		return null;
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	public PageBean findByPage(Integer pageCode, Integer pageSize) {
		return null;
	}

	public PageBean findByPage(User user, Integer pageCode, Integer pageSize) {
		PageHelper.startPage(pageCode, pageSize);
		Page page = userMapper.findByPage(user);
		return new PageBean(page.getTotal(), page.getResult());
	}

	@Override
	public User findById(Long id) {
		return userMapper.findById(id);
	}

	@Override
	public void add(User user) {
		add(user, false);
	}

	@Override
	public void add(User user, Boolean IS_ADMIN) {
		if (IS_ADMIN)
			user.setRole("ROLE_ADMIN");
		if (user.getExperience() == null)
			user.setExperience("0");
		if (user.getNickname() == null)
			user.setNickname(UUID.randomUUID().toString());
		if (user.getAvatar() == null)
			user.setAvatar("http://localhost:8080/default_avatar/user_avatar0" +
							randomAvatar() + ".png");
		userMapper.add(user);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void delete(Long... ids) {
		for (Long id : ids) {
			userMapper.deleteById(id);
		}
	}

	private String randomAvatar(){
		Integer num = new Random().nextInt(12) + 1;
		return num > 10 ? num.toString() : "0" + num.toString();
	}
}
