package kh.spring.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
@Service
public class MemberService {
	@Autowired
	private MemberDAO mdao;
	@Autowired
	private BoardDAO bdao;
	
    private JavaMailSender mailSender;
    
    
    //회원가입 메소드, Map과 dto를 같이 넘김
    public void join(Map<String, Object>map,MemberDTO dto) {
    	mdao.join(map,dto);
 
    }
 
    //로그인 관련 메소드 (세션에 아이디와 비밀번호를 저장)
    public boolean loginCheck(MemberDTO dto, HttpSession session) {
        
        boolean result = mdao.loginCheck(dto);
        if(result) {    //로그인 성공
            session.setAttribute("user_id", dto.getId());
            session.setAttribute("member_pass", dto.getPw());
            System.out.println(session.getAttribute("user_id"));
            System.out.println(session.getAttribute("member_pass"));
        }
        
        return result;
    }
 
    //아이디 찾기
    public String find_idCheck(MemberDTO dto) {
        String id = mdao.find_idCheck(dto);
        
        return id;
    }
 
    //비밀번호 찾기
    public String find_passCheck(MemberDTO dto) {
        String pass = mdao.find_passCheck(dto);
        return pass;
    }
 
 
    public void authentication(MemberDTO dto) {
        
    	mdao.authentication(dto);
    }
 
 
    public void pass_change(Map<String, Object> map, MemberDTO dto) throws Exception {
        
        
    	mdao.pass_change(map,dto);
    }
 
 
    //이메일 중복 확인
    public boolean email_check(String e_mail) throws Exception{
        
        boolean result = mdao.email_check(e_mail);
        
        return result;
        
    }
 
    //아이디 중복 확인
    public boolean join_id_check(String user_id) throws Exception {
    
        boolean result = mdao.join_id_check(user_id);
        
        return result;
    }
 
 
    //자신의 프로필을 볼 수 있게 하는 메소드
    public List<MemberDTO> member_profile(String user_id) throws Exception{
        
        return mdao.member_profile(user_id);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

	public int signup(MemberDTO dto) throws Exception {
		int result = mdao.signup(dto);
		return result;
	}

	public MemberDTO login(Map<String,String> param) throws Exception {
		MemberDTO mdto = mdao.login(param);
		return mdto;
	}
	public boolean duplCheck(String id) throws Exception {
		boolean result = mdao.duplCheck(id);
		return result;
	}
	
	public int deleteById(String id) throws Exception {
		return mdao.deleteById(id);
	}
	public int updateByDTO(MemberDTO mdto) throws Exception {
		int result = mdao.updateByDTO(mdto);
		return result;
	}
	
	public static String getSHA512(String input){
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	
	
}
