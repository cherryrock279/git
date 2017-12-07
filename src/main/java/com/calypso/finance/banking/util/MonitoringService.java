package com.calypso.finance.banking.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.calypso.finance.banking.util.MonitoringService;
@Component
@Aspect
public class MonitoringService {
	Logger log=LoggerFactory.getLogger(MonitoringService.class);
	
	@Before("execution(* com..*Service.*(..))")//first star means any return value type
	public void monitorBeforeCall(JoinPoint point) {
		log.info("Target:{}",point.getTarget().toString());
		log.info("Before advice invoked{}",point.getSignature().getName());
}
	@After("execution(* com..*Service.*(..))")
	public void monitorAfterCall(JoinPoint point) {
		log.info("Target:{}",point.getTarget().toString());
		log.info("After advice invoked{}",point.getSignature().getName());
}
	
	@AfterThrowing(pointcut="execution(* com..*Service.*(..))",throwing="npe")
	public void monitorAfterThrowingCall(JoinPoint point,NullPointerException npe) {
		log.info("Exception:{}",npe.getLocalizedMessage());
		log.info("Method:{}",point.getSignature().getName());
}
}