package kh.spring.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;


public class LogAdvisor {

	@Autowired
	private HttpSession session;

//	// advice
//	public void aopTest(JoinPoint jp) {
//		Signature sign = jp.getSignature();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//		String currentTime = sdf.format(System.currentTimeMillis());
//		
//		Object[] args = jp.getArgs();
//		HttpServletRequest req = (HttpServletRequest)args[0];
//		req.setAttribute("request", "Hello AOP");
//
//		System.out.println("===============");
//		System.out.println(currentTime);
//		System.out.println(jp.getTarget()); // Advice가 적용되어 실행되는 메서드의 클래스
//		System.out.println(sign.getName());
//		System.out.println("===============");
////		System.out.println(session.getAttribute("loginId"));
//	}
	
	
	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		String returnValue = (String)pjp.proceed();
		long endTime = System.currentTimeMillis();
		System.out.println("aroundTest : "+(endTime-startTime));
		return returnValue;
	}
	
	
	public Object loginCheck(ProceedingJoinPoint pjp) throws Throwable {
		MemberDTO mdto = (MemberDTO) session.getAttribute("loginInfo");
		if(mdto == null) {
			System.out.println("loginCheck 가 로그인 되지않은 사용자를 메인화면으로 리다이렉트 합니다!");
			return "redirect:/";
		}		
		String returnValue = (String)pjp.proceed();
		return returnValue;
	}	
	
}
