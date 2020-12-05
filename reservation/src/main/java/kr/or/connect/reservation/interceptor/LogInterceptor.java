package kr.or.connect.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String handlerStr = handler!=null?handler.toString():null;
		logger.debug("호출: {}", handlerStr!=null?handlerStr:"알 수 없는 핸들러");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		String handlerStr = handler!=null?handler.toString():null;
		String viewNameStr = modelAndView!=null?modelAndView.getViewName():null;
		logger.debug("ViewName: {}, 종료: {}", viewNameStr!=null?viewNameStr:"알 수 없는 뷰", handlerStr!=null?handlerStr:"알 수 없는 핸들러");
	}
}
