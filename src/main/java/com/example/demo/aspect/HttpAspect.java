package com.example.demo.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.example.demo.controller.HelloController.*(..))")
    public void log(){
    }

    @Before("log()")
    public void BeforeLog(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();

        //请求路径
        logger.info("url={}",request.getRequestURL());
        logger.info("method={}",request.getMethod());
        logger.info("-------before");
    }

    @After("log()")
    public void afterLog(){
        logger.info("-------After");
    }

    @AfterReturning("log()")
    public void AfterReturningLog(){
        logger.info("-------AfterReturning");
    }
}
