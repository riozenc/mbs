/**
 * Author : chizf
 * Date : 2020年3月2日 下午5:31:55
 * Title : org.mbs.server.config.WebSocketConfig.java
 *
**/
package org.mbs.server.message.socket.config;

import org.mbs.server.message.socket.handler.WebSocketHandler;
import org.mbs.server.message.socket.interceptor.WebSocketInterceptor;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class WebSocketConfig implements WebSocketConfigurer {

	public WebSocketConfig() {
		System.out.println("WebSocketConfig");
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		System.out.println("asd");
		registry.addHandler(new WebSocketHandler(), "/websocket").addInterceptors(new WebSocketInterceptor())
				.setAllowedOrigins("*");

		// sockJs通道
		registry.addHandler(new WebSocketHandler(), "/sock-js").addInterceptors(new WebSocketInterceptor())
				.setAllowedOrigins("*")
				// 开启sockJs支持
				.withSockJS();
	}

}
