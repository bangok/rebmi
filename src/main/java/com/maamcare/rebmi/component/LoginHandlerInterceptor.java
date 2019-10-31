package com.maamcare.rebmi.component;

import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.Result;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 登录拦截器，使用sessionId存储
 *
 * */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //用户未登录
            try {
                //response.getWriter().print(Result.builder().status(0).err(new ErrMap(-1,"用户未登录")).data(null).build());
                //response.sendRedirect(request.getContextPath()+"/user/noLogin");
                returnJson(response,"{\"status\":0,\"err\":{\"code\":-1,\"msg\":\"用户未登录\"}\"data\":\"\"}");
            }catch (Exception e){
                System.out.println(e);
            }
            return false;
        }
        return true;
    }

    /*返回客户端数据*/
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
