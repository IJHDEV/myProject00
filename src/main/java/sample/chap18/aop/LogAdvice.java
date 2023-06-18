package sample.chap18.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;


//Advice(구체적으로 부가기능(횡단 관심사 ->관심사)이 정의된 메서드)
//Aspect(Advice + joinpoint)
@Aspect
@Component
@Log4j
public class LogAdvice {
	
//	@Before("execution(* sample.chap18.service.SampleServiceImpl.doAdd(String, String))")
//	public void logBefore1() {
//		log.info("LogAdvice(Before-부가기능 메서드)==============================") ;
//	}


/*	
	//위의 @Before("execution(* sample.chap18.service.SampleService*.*(..))")  설명
	- @Before (join-point)
	- 괄호 안의 첫 번째 * : 모든 접근제한자를 의미 
	- sample.chap18.service.SampleService* : 패키지이름.클래스접두어가 포함된 모든 클래스를 의미
	- 첫번째 점 다음의 * : 모든 메서드  
	- (..) : 메서드의 매개변수 갯수, 타입 상관없음

*/	
	
	
	
//	@After("execution(* sample.chap18.service.SampleServiceImpl.doAdd(String, String))")
//	public void logAfter1() {
//		
//		log.info("LogAdvice(After-부가기능 메서드)==============================") ;
//	}
	
	
//	@AfterThrowing(pointcut = "execution(* sample.chap18.service.SampleServiceImpl.doAdd(String, String))", 
//				   throwing = "myEx")
//	public void logException(Exception myEx) {
//		System.out.println("LogAdvice.logException 실행됨=========;;;;;;" );
//		System.out.println("발생된 예외: " + myEx);
//	}
	
	
	// Target 메서드 실행 전, 실행 후에 advice 메서드 실행
	@Around("execution(* sample.chap18.service.SampleService*.*(..)) && args(paramStr1, paramStr2)")
	public Object logTime(ProceedingJoinPoint pjp, String paramStr1, String paramStr2) {
		log.info("paramStr1: " + paramStr1);
		log.info("paramStr2: " + paramStr2);
		
		
		log.info("SampleServiceImpl.대상메소드 실행 전, logTime 메소드의 나머지 처음 부분 실행 시작");
			
		log.info("Target: " + pjp.getTarget()); // Target 메서드 정보를 가져옴
		log.info("Param: " + Arrays.toString(pjp.getArgs())); // Target 메서드 매개변수 정보를 가져옴
			
		Object result = null;
			
		log.info("SampleServiceImpl.대상메소드 실행 전, logTime 메소드의 나머지 처음 부분 실행 시작 완료");
			
		long start = System.currentTimeMillis();
		log.info("System.currentTimeMillis(): " + start);
		
		log.info("SampleServiceImpl.대상메소드 실행 시작");

		try {
		      //invoke Target method 
		      result = pjp.proceed();  //target을 실행.SampleServiceImpl.대상메소드가 실행
		} catch (Throwable e) {
		   e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		log.info("SampleServiceImpl.대상메소드 실행 완료 후, logTime 메소드의 나머지 실행 시작");
		log.info("실행 시간: "  + (end - start));
			
		return result;
	}

	
	//동시에 적을 수 있지만 Before만 적용, 나머지는 무시됨
////@After("execution(* sample.chap18.service.SampleService*.*(String, String)) && args(paramStr1, paramStr2)")
//	@Before("execution(* sample.chap18.service.SampleService*.*(String, String)) && args(paramStr1, paramStr2)")
//	public void logAfterWithParam(String paramStr1, String paramStr2) {
//	
//		System.out.println("LogAdvice.logAfterWithParam 실행:::::::::");
//		
//		log.info("paramStr1: " + paramStr1);
//		log.info("paramStr2: " + paramStr2);
//		
//	}
	
	


}
