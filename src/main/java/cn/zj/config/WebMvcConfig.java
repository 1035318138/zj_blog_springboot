package cn.zj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Sept-05 on 2019/9/2.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Value("${uploadFile.resourceHandler}")
	private String resourceHandler;

	@Value("${uploadFile.location}")
	private String location;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourceHandler).addResourceLocations("file:///" + location);
	}
}
