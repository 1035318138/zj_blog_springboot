package cn.zj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Sept.05 on 2019/8/8.
 * <p>
 * 前端交互
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	SelfUserDetailsService selfUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = (String) authentication.getPrincipal();       // 这个获取表单输入中返回的用户名;
		String password = (String) authentication.getCredentials();     // 这个是表单中输入的密码；

//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encode = passwordEncoder.encode(password);

		UserDetails userDetails = selfUserDetailsService.loadUserByUsername(userName);

		if (userDetails == null && !userDetails.getPassword().equals(password)) {
			throw new BadCredentialsException("用户名/密码不正确，请重新登录");
		}
		return new UsernamePasswordAuthenticationToken(userName, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
