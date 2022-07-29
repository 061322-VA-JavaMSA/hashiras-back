
package com.revature.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CorsFix implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:4200")
			.allowedHeaders("*")
			.exposedHeaders("Authorization")
			.allowedMethods("GET", "OPTIONS", "PUT", "POST", "DELETE");
	}
}