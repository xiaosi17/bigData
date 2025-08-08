package com.example.ecommerce.aspect;


import com.example.ecommerce.config.BusinessException;
import com.example.ecommerce.config.ErrorCode;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAuthAspect {
    private final UserService userService;

    @Pointcut("@annotation(com.example.ecommerce.aspect.RequireLogin)")
    public void serviceLayer() {}
    @Around("serviceLayer()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();

        // 从请求头获取token
        String token= request.getHeader("UserId");
        User user = userService.getUserById(Long.valueOf (token));

        // 校验token是否有效
        if (user == null || !validateToken(token)) {
            throw new BusinessException(ErrorCode.AUTH_FAILED.getCode(),"用户不存在");
        }

        // 执行目标方法
        return joinPoint.proceed();
    }

    private boolean validateToken(String token) {
        // 这里实现实际的token验证逻辑
        // 可以调用用户服务验证token有效性
        return true; // 示例代码直接返回true
    }

    @Before("serviceLayer()")
    public void logBefore(JoinPoint jp) {
        // 前置通知逻辑
        System.out.println("前置通知: " + jp.getSignature());
    }
}

