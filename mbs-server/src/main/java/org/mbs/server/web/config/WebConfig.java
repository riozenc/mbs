/**
 * Author : chizf
 * Date : 2020年3月2日 下午5:30:58
 * Title : org.mbs.server.config.WebConfig.java
 *
**/
package org.mbs.server.web.config;

import org.mbs.server.web.interceptor.WebInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	public WebConfig() {
		System.out.println("WebConfig");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("addInterceptors");
		registry.addInterceptor(new WebInterceptor()).addPathPatterns("/*");
	}
}
