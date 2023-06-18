package sample.chap04.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

//이 프로젝트 모든 컨트롤러의 예외 이 클래스의 메서드로 처리 선언
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.error("MyException: " + ex.getMessage());
		model.addAttribute("myException", ex);
		log.error("model: " + model);
		
		return "sample/error_page";
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		
		return "sample/custom404";
		
	}
}
