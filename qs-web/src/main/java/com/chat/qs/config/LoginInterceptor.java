package com.chat.qs.config;

/*
* 登录拦截器
* */

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    //在控制器执行前调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("执行preHandle方法-->01");
        //request.getRequestDispatcher("login.html").forward(request, response);

        /*HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            //request.getRequestDispatcher("/index/login").forward(request, response);
            response.sendRedirect("/login");
            return false;
        }else {
            return true;
        }*/
        return true;
    }
    //在后端控制器执行后调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("执行postHandle方法-->02");
        super.postHandle(request, response, handler, modelAndView);
    }
    //整个请求执行完成后调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("执行afterCompletion方法-->03");
        super.afterCompletion(request, response, handler, ex);
    }
}