package de.fi.webapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {


    @Before("execution(public * de.fi.webapp.presentation.controller.v1.PersonenQueryController.*(..))")
    public void doLog(JoinPoint joinPoint) {
        log.warn(joinPoint.getSignature().getName() + " wurde gerufen");
    }

    @AfterReturning(value = "execution(public * de.fi.webapp.presentation.controller.v1.PersonenQueryController.*(..))", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.warn(joinPoint.getSignature().getName() + " hat " + result + " geliefert");
    }

    @AfterThrowing(value = "execution(public * de.fi.webapp.presentation.controller.v1.PersonenQueryController.*(..))", throwing = "ex")
    public void doThrow(JoinPoint joinPoint, Throwable ex) {
        log.warn(joinPoint.getSignature().getName() + " hat " + ex + " geworfen");
    }

    @After(value = "execution(public * de.fi.webapp.presentation.controller.v1.PersonenQueryController.*(..))")
    public void doAfter(JoinPoint joinPoint) {
        log.warn(joinPoint.getSignature().getName() + " ist beendet");
    }

    @Around(value = "execution(public * de.fi.webapp.presentation.controller.v1.PersonenQueryController.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
       Object result = joinPoint.proceed();
       return result;
    }
}
