package employee.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* employee.service.EmployeeServiceImpl.*(..))")
	public void logBeforeMethod(JoinPoint joinPoint) {
		logger.info("Executing method: " + joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			logger.info("Argument passed: " + arg);
		}
	}

	@AfterReturning(pointcut = "execution(* employee.service.EmployeeServiceImpl.*(..))", returning = "result")
	public void logAfterMethod(JoinPoint joinPoint, Object result) {
		logger.info("Method executed: " + joinPoint.getSignature().getName());
		if (result != null) {
			logger.info("Method returned: " + result);
		}
	}

}
