package kh.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("Exception Handler : 에러가 발생했습니다");
		return "error";
	}
	
	@ExceptionHandler
	public String exceptionHandler(NumberFormatException e) {
		e.printStackTrace();
		System.out.println("NFException Handler : 에러가 발생했습니다");
		return "error";
	}
	
	@ExceptionHandler
	public String exceptionHandler(NullPointerException e) {
		e.printStackTrace();
		System.out.println("NULLException Handler : 에러가 발생했습니다");
		return "error";
	}
	
}
