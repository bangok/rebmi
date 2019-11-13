package com.maamcare.rebmi.component;

import com.maamcare.rebmi.annotation.Access;
import com.maamcare.rebmi.exception.MyException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


/**
 * 全局拦截器
 *  使用拦截器方式完成权鉴
 * */
@Component
public class GlobalHandlerInterceptor implements HandlerInterceptor {
    /**
     * 使用注解方式拦截
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Access access = method.getAnnotation(Access.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        //执行权限判断逻辑
        if (access.authorities().length > 0) {
            String[] authorities = access.authorities();
            for(int i=0;i<authorities.length;i++){
                //遍历权限
                if(authorities[i].equals("LOGIN")){
                    //执行判断登录逻辑
                    Object user = request.getSession().getAttribute("loginUser");
                    if(user==null){
                        throw new MyException(-1,"用户未登录");
                    }
                }
                //如果加入其它权限，同样处理
            }
        }
        // 拦截之后应该返回公共结果, 这里没做处理
        return true;
    }



    /**
     * 使用接管模式拦截
     * */
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//
//        Object user = request.getSession().getAttribute("loginUser");
//        if(user == null){
//            //用户未登录
//            try {
//                //response.getWriter().print(Result.builder().status(0).err(new ErrMap(-1,"用户未登录")).data(null).build());
//                //response.sendRedirect(request.getContextPath()+"/user/noLogin");
//                returnJson(response,"{\"status\":0,\"err\":{\"code\":-1,\"msg\":\"用户未登录\"}\"data\":\"\"}");
//            }catch (Exception e){
//                System.out.println(e);
//            }
//            return false;
//        }
//        return true;
//    }

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
