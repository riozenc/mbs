/**
 * Author : chizf
 * Date : 2020年3月2日 下午5:34:50
 * Title : org.mbs.server.web.interceptor.WebInterceptor.java
 *
**/
package org.mbs.server.web.interceptor;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebInterceptor extends HandlerInterceptorAdapter {
	private static final Log logger = LogFactory.getLog(WebInterceptor.class);
	// 参数中的Object handler是下一个拦截器。

	// 最后执行，可用于释放资源
	// 在afterCompletion中，可以根据e是否为null判断是否发生了异常，进行日志记录

	private void executeException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, Exception exception) throws Exception {

		exception.printStackTrace();
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, Exception exception) throws Exception {
		// TODO Auto-generated method stub

		if (null != exception) {

			executeException(httpServletRequest, httpServletResponse, object, exception);

		}

	}

	// Action之后,生成视图之前执行
	// 在postHandle中，有机会修改ModelAndView
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		if (null != modelAndView) {

		}
		System.out.println("postHandler");
	}

	// Action之前执行
	// 在preHandle中，可以进行编码、安全控制等处理

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		// TODO Auto-generated method stub
		logger.info("[" + LocalDateTime.now() + "]{" + httpServletRequest.getRemoteAddr() + "} 执行"
				+ getClassMethod(object) + "[" + httpServletRequest.getMethod() + "]");
		return true;
	}

	protected String getClassMethod(Object object) {
		if (object instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) object;
			Class<?> clazz = handlerMethod.getBeanType();
			Method method = handlerMethod.getMethod();

			return clazz.getName() + "." + method.getName();
		} else {
			return "执行未知操作:" + object.getClass();
		}
	}
}
