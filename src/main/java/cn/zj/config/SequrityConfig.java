package cn.zj.config;

import cn.zj.dto.Result;
import cn.zj.entity.User;
import cn.zj.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Sept.05 on 2019/8/4.
 */
@Configuration
@EnableWebSecurity
public class SequrityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.csrf().disable()
				//			.csrf().ignoringAntMatchers("/druid/**").and()
				.authorizeRequests()
//				.antMatchers("/**").permitAll()
				.antMatchers("/resource/**", "/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/findByName").permitAll()
				.antMatchers("/api/user/**").hasRole("ADMIN")
//				.antMatchers(HttpMethod.POST, "/druid/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(HttpMethod.POST).hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT).hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//				.antMatchers("/druid/**").permitAll()
				.and()
				.cors().and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.formLogin()
				.usernameParameter("user")
				.passwordParameter("password")
//				.loginPage("/userLogin")
				.successForwardUrl("/success")
				.failureForwardUrl("/fail").and()

				.logout()
				.logoutSuccessUrl("/logoutSuccess");

		http.exceptionHandling().accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
			httpServletResponse.setHeader("Content-type", "application/json;charset=utf-8");
			Result responseBody = new Result(403, "无权访问");
			httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
		});

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
//		User user = userService.findById(1L);
//		System.out.println(user);

//		User user = userService.findByName();
//		auth.inMemoryAuthentication()
//				.withUser(user.getUsername())
//				.password(passwordEncoder().encode(user.getPassword()))
//				.roles("admin", "user");
		auth.userDetailsService(selfUserService());
	}

	@Bean
	public UserDetailsService selfUserService() {
		return new SelfUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 传统的方法是在认证通过后，创建sesstion，并给客户端返回cookie。
	 *
	 * 增加JWT认证功能
	 *
	 * 现在我们采用JWT来处理用户名密码的认证认证通过后，服务器生成一个token，将token返回给客户端，
	 * 客户端以后的所有请求都需要在http头中指定该token。
	 * 服务器接收的请求后，会对token的合法性进行验证。
	 *
	 * 验证的内容包括：
	 * 1、内容是一个正确的JWT格式
	 * 2、检查签名
	 * 3、检查claims
	 * 4、检查权限
	 *
	 */

	/**
	 * 实现：
	 * 1、创建一个类JWTLoginFilter，核心功能是在验证用户名密码正确后，生成一个token，并将token返回给客户端
	 * 2、授权验证
	 *      用户一旦登录成功后，会拿到token，后续的请求都会带着这个token，服务端会验证token的合法性。
	 *      创建JWTAuthenticationFilter类，我们在这个类中实现token的校验功能。
	 */
}
