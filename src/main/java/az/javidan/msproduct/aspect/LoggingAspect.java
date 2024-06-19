package az.javidan.msproduct.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around(value = "within(@az.javidan.msproduct.annotation.Log *)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();

        log.info("ActionLog.start.{}", methodName);

        Object result = joinPoint.proceed();

        log.info("ActionLog.{}.end", methodName);

        return result;
    }
}
