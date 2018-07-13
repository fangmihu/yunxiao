package com.tangxigua.yunxiao.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

//    @Autowired
//    private HostHolder hostHolder;
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        if (hostHolder.getUser() == null) {
//            httpServletResponse.sendRedirect("/reglogin?next=" + httpServletRequest.getRequestURI());
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    }
}
