package cn.zj.config;

import cn.zj.entity.User;
import cn.zj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sept.05 on 2019/8/8.
 * <p>
 * 用户认证、权限
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userService.findByName(s);
//		System.out.println(s);
//		System.out.println(userService == null);
		if (user != null) {
			user.setPassword(userService.getPassWordByName(s));
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encode = passwordEncoder.encode(user.getPassword());
			user.setPassword(encode);

			Set authoritiesSet = new HashSet();
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			if ("ROLE_ADMIN".equals(user.getRole()))
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
			for (GrantedAuthority grantedAuthority : grantedAuthorities) {
				authoritiesSet.add(grantedAuthority);
			}
			user.setAuthorities(authoritiesSet);
		}

		System.out.println(user);

		return user;
	}
}
