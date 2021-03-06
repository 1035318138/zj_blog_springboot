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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

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
				//.csrf().ignoringAntMatchers("/druid/**").and()
				.authorizeRequests()
//				.antMatchers("/**").permitAll()
				.antMatchers("/resource/**", "/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user").permitAll()
				.antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/article/updateEyeCount").permitAll()
				.antMatchers(HttpMethod.POST, "/api/comments").hasRole("USER")
				.antMatchers("/api/user/**").hasRole("ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST).hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT).hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//				.antMatchers("/druid/**").permitAll()
				.and()
//				.cors().and()

				.formLogin()
				.usernameParameter("user")
				.passwordParameter("password")
//				.loginPage("/login")
				.successForwardUrl("/success")
				.failureForwardUrl("/fail").and()

				.logout()
				.logoutSuccessUrl("/logoutSuccess");

		http.exceptionHandling().accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
			System.out.println(e);
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
}
