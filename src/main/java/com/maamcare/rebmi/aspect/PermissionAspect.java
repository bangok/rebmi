package com.maamcare.rebmi.aspect;


import com.maamcare.rebmi.annotation.Access;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;


/**
 * 基于AOP注解方式的权鉴
 * */
@Component
@Aspect
public class PermissionAspect {

    private final String POINT_CUT = "execution(public * com.maamcare.rebmi.controller.*.*(..))";

    @Pointcut(POINT_CUT)
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        //通过以下方式获取Request等信息
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        request.getSession().getAttribute("loginUser");
        //通过以下方式获取方法上的注解
        joinPoint.getTarget().getClass().getAnnotation(Access.class);
        //通过以下方式按顺序获取参数
        Object[] o = joinPoint.getArgs();
        for(int i =0;i<o.length;i++){
            System.out.println(o[i]);
        }
    }
}
