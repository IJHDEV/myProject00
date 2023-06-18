package sample.chap18.service;

import org.springframework.stereotype.Service;

//핵심 비즈니스 로직이 구현된 메서드의 클래스
//AOP는 @Service 어노테이션이 명시된 클래스의 메서드에 대하여 동작합니다.
@Service
public class SampleServiceImpl implements SampleService{

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		System.out.println("SampleSerivce의 doAdd메서드입니다=========");
		return Integer.parseInt(str1) + Integer.parseInt(str2);
		
	}
	
//	private LogAdvice logAdvice ;
//	
//	public SampleServiceImpl(LogAdvice logAdvice) {
//		this.logAdvice = logAdvice ;
//	}
//	
//	@Override
//	public Integer doAdd(String str1, String str2) throws Exception {
//		
//		logAdvice.logBefore1();
//	
//		return Integer.parseInt(str1) + Integer.parseInt(str2);
//		
//	}
}
