package com.sing.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

@Aspect
@Component
public class EmployeeServiceAspect {

	@Before(value = "execution(* com.sing.service.EmployeeService.*(..)) and args(name,empId)")
	public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("Before method:" + joinPoint.getSignature());

		System.out.println("Creating Employee with name - " + name + " and id - " + empId);
	}

	@Before("@annotation(annotation)")
	public void annotationBefore(JoinPoint jointPoint, UserSafetyAgentPortalGuard annotation){
		String referencedId = (String) getParamValue(jointPoint, annotation.key());
		System.out.println(referencedId);
	}

	private Object getParamValue(JoinPoint joinPoint, String key) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = getMethod(signature);
		String[] parameters = signature.getParameterNames();
		Integer index = IntStream.range(0, parameters.length)
				.boxed()
				.filter(i -> parameters[i].equals(key))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Provided key=%s doesn't match any argument names in method with signature=%s", key, signature.toShortString())));
		return joinPoint.getArgs()[index];
	}

	private Method getMethod(JoinPoint jointPoint) {
		return ((MethodSignature) jointPoint.getSignature()).getMethod();
	}

	@After(value = "execution(* com.sing.service.EmployeeService.*(..)) and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("After method:" + joinPoint.getSignature());

		System.out.println("Successfully created Employee with name - " + name + " and id - " + empId);
	}
}
