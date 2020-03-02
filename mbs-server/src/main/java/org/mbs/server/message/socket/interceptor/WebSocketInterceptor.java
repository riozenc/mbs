/**
 * Author : chizf
 * Date : 2020年3月2日 下午5:36:18
 * Title : org.mbs.server.web.interceptor.WebSocketInterceptor.java
 *
**/
package org.mbs.server.message.socket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketInterceptor implements HandshakeInterceptor {
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		System.out.println("beforeHandshake");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
			// 获取请求路径携带的参数
			String user = serverHttpRequest.getServletRequest().getParameter("user");
			attributes.put("user", user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		System.out.println("afterHandshake");
	}
}
