package com.ecdevco.springbootrest.aop;

import com.ecdevco.springbootrest.entities.Tracker;
import com.ecdevco.springbootrest.services.TrackerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Aspect
@Configuration
public class StudentControllerAspect {

    Logger logger = LoggerFactory.getLogger(StudentControllerAspect.class);

    @Autowired
    private TrackerService loggerService;

//    @Before(value = "execution(* com.ecdevco.springbootrest.rest.controller.*.*(..))")
//    public void befor(JoinPoint joinPoint) {
//        saveLog("info", joinPoint, null, null);
//    }
//
//    @AfterReturning(value = "execution(* com.ecdevco.springbootrest.rest.controller.*.*(..))",
//            returning = "result")
//    public void afterReturning(JoinPoint joinPoint, ResponseEntity result) {
//        Gson gson = new GsonBuilder().create();
//        saveLog("info", joinPoint, gson.toJson(result.getBody()), result.getStatusCode().toString());
//    }

    @Around(value = "execution(* com.ecdevco.springbootrest.rest.controller.*.*(..))")
    public ResponseEntity aroundMessageLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Gson gson = new GsonBuilder().create();
        ResponseEntity result = (ResponseEntity) proceedingJoinPoint.proceed();
        logger.info(" tracker- info for ({}) method with request value: ({}) and response status: ({})"
                , proceedingJoinPoint.getSignature().getName(), gson.toJson(proceedingJoinPoint.getArgs()), result.getStatusCode().toString());
        saveLog("info", proceedingJoinPoint, gson.toJson(result.getBody()), result.getStatusCode().toString());
        return result;
    }

    @AfterThrowing(value = "execution(* com.ecdevco.springbootrest.rest.controller.*.*(..))", throwing = "e")
    public void afterThrowingException(JoinPoint joinPoint, Exception e) {
        logger.info(" tracker- execution for ({}) method with request value: ({})", joinPoint.getSignature().getName(), joinPoint.getArgs()[0]);
        saveLog("error", joinPoint, "", e.getCause().getMessage());
    }

    private void saveLog(String level, JoinPoint joinPoint, String result, String msg) {

        Tracker log = new Tracker();
//        log.setType(type);
        log.setLevel(level);

        Gson gson = new GsonBuilder().create();
        log.setRequestData(gson.toJson(joinPoint.getArgs()));
        log.setName(joinPoint.getSignature().getName());

        log.setResponseData(result);
        log.setMessage(msg);
        log.setServerDate(new Date());
        boolean res = loggerService.createLog(log);
        logger.info(" insert tracker result ({}) ", res);
    }


}
