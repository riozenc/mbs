/**
 * Author : chizf
 * Date : 2020年3月2日 下午5:42:41
 * Title : org.mbs.server.message.socket.handler.WebSocketHandler.java
 *
**/
package org.mbs.server.message.socket.handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {
	private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
	private static Map<String, String> userMap = new ConcurrentHashMap<>();

	/**
	 * webSocket连接创建后调用
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		// 获取参数
		String user = String.valueOf(session.getAttributes().get("user"));
		userMap.put(user, session.getId());
		sessionMap.put(session.getId(), session);
	}

	/**
	 * 接收到消息会调用。消息分发。
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message instanceof TextMessage) {

		} else if (message instanceof BinaryMessage) {

		} else if (message instanceof PongMessage) {

		} else {
			System.out.println("Unexpected WebSocket message type: " + message);
		}
	}

	/**
	 * 连接出错会调用
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		sessionMap.remove(session.getId());
	}

	/**
	 * 连接关闭会调用
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		sessionMap.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 后端发送消息
	 */
	public void sendMessage(String user, String message) throws IOException {
		String sessionId = userMap.get(user);
		WebSocketSession session = sessionMap.get(sessionId);
		session.sendMessage(new TextMessage(message));
	}
}
