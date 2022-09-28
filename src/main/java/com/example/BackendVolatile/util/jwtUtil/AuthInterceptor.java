package com.example.BackendVolatile.util.jwtUtil;
import com.example.BackendVolatile.service.UserService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.PassToken;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.util.ThreadLocalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    UserService userService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前)");
        String token = request.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                    log.info("无token，请重新登录");
                    ThreadLocalUtils.set("valid",0L);
                    ThreadLocalUtils.set("userId", -1L);
                    return true;

                }

                // 验证 token
                boolean valid = jwtTokenUtil.validateToken(token);
                if(!valid){
                    log.info("token无效");
                    ThreadLocalUtils.set("valid", 0L);
                    ThreadLocalUtils.set("userId", -1L);
                    return true;
                }
                // 获取 token 中的 user id
                long userId;
                try {
                    userId = jwtTokenUtil.getIdFromToken(token);
                } catch (Exception e) {
                    log.info("拦截器获取userId出现异常");
                    ThreadLocalUtils.set("valid",0L);
                    ThreadLocalUtils.set("userId", -1L);
                    return true;
                }

                ThreadLocalUtils.set("valid",1L);
                ThreadLocalUtils.set("userId", userId);

                return true;
            }

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        ThreadLocalUtils.remove();
    }



}
