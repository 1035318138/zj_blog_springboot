package cn.zj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/11.
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig extends CorsFilter{
	public CorsConfig() {
		super(configurationSource());
	}

	private static UrlBasedCorsConfigurationSource configurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		List<String> allowedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
		List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
		List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
		List<String> allowedOrigins = Arrays.asList("http://localhost:8888");
		corsConfig.setAllowedHeaders(allowedHeaders);
		corsConfig.setAllowedMethods(allowedMethods);
		corsConfig.setAllowedOrigins(allowedOrigins);
		corsConfig.setExposedHeaders(exposedHeaders);
		corsConfig.setMaxAge(36000L);
		corsConfig.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		// 设置你要允许的网站域名，如果全允许则设为 *
//		config.addAllowedOrigin("http://localhost:8888");
//		// 如果要限制 HEADER 或 METHOD 请自行更改
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//		// 这个顺序很重要哦，为避免麻烦请设置在最前
//		bean.setOrder(0);
//		return bean;
//	}
}
