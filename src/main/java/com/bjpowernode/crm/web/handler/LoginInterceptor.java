package com.bjpowernode.crm.web.handler;

import com.bjpowernode.crm.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器执行了");

        String path = request.getServletPath();

        if("/login.jsp".equals(path) || "/user/login.do".equals(path)){
            return true;
        }else{

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            //user不为空说明登陆过
            if(user != null){
                return true;
            }else{

                String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();

                //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){

                    //告诉ajax我是重定向
                    response.setHeader("SESSIONSTATUS", "TIMEOUT");
                    //告诉ajax我重定向的路径
                    response.setHeader("CONTEXTPATH", basePath+"/login.jsp");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                    return false;
                }else{
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return false;
                }

            }
        }


    }
}
