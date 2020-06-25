package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private SqlSessionTemplate mybatis;
	@Inject
	SqlSession sqlSession;

    // 회원가입 관련 메소드
	public void join(Map<String, Object>map, MemberDTO dto) {

		map.get("user_id");
		map.get("member_pass");
		map.get("e_mail");
		sqlSession.insert("member.insertUser",map);        
	}

    //로그인관련 메소드
    public boolean loginCheck(MemberDTO dto) {
        String name
            =sqlSession.selectOne("member.login_check", dto);
        
        //조건식 ? true일때의 값 : false일때의 값
        return (name==null) ? false : true;
    }


    //아이디 찾기 관련 메소드
    public String find_idCheck(MemberDTO dto) {
        String id = sqlSession.selectOne("member.find_id_check", dto);
        return id;
        
    }
 
    //비밀번호 찾기 관련 메소드
    public String find_passCheck(MemberDTO dto) {
        String pass = sqlSession.selectOne("member.find_pass_check", dto);
        return pass;
    }


    //회원 인증 관련 메소드
    //버튼을 클릭한 회원의 정보를 회원 테이블에 저장해서 사용할 수 있게 함
    public void authentication(MemberDTO dto) {
        
        sqlSession.insert("member.authentication", dto);
        
    }
    
    
    public void pass_change(Map<String, Object> map, MemberDTO dto)throws Exception{
        
        map.get("member_pass");
        map.get("e_mail");
 
        sqlSession.update("member.pass_change", map);
    }
    
    
    public boolean email_check(String e_mail) throws Exception {
        String email
        =sqlSession.selectOne("member.email_check", e_mail);
    
        //조건식 ? true일때의 값 : false일때의 값
        return (email==null) ? true : false;
        
    }
 
 
    public boolean join_id_check(String user_id) throws Exception {
        String user_id1
        =sqlSession.selectOne("member.join_id_check", user_id);
    
        //조건식 ? true일때의 값 : false일때의 값
        return (user_id1==null) ? true : false;
    }
 
    
    //회원의 프로필 정보를 리턴한다.
    public List<MemberDTO> member_profile(String user_id) throws Exception {
        
        return sqlSession.selectList("member.member_profile", user_id);
    }
    
    








	public int signup(MemberDTO dto) throws Exception{
		//		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		//		return jdbc.update(sql, dto.getId(),dto.getPw(),dto.getName(),dto.getPhone(),dto.getEmail(),dto.getZipcode(),dto.getAddress1(),dto.getAddress2());
		return mybatis.insert("Member.insert",dto);
	}

	public MemberDTO login(Map<String,String> param) throws Exception{
		//		String sql = "select * from member where id=? and pw=?";
		//		return jdbc.queryForObject(sql, new Object[] {id,pw}, new RowMapper<MemberDTO>() {
		//			@Override
		//			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		//				MemberDTO mdto = new MemberDTO();
		//				mdto.setId(rs.getString("id"));
		//				mdto.setPw(rs.getString("pw"));
		//				mdto.setName(rs.getString("name"));
		//				mdto.setPhone(rs.getString("phone"));
		//				mdto.setEmail(rs.getString("email"));
		//				mdto.setZipcode(rs.getString("zipcode"));
		//				mdto.setAddress1(rs.getString("address1"));
		//				mdto.setAddress2(rs.getString("address2"));
		//				return mdto;
		//			}
		//		});
		return mybatis.selectOne("Member.login",param);

	}


	public boolean duplCheck(String id) throws Exception{
		//		String sql = "select * from member where id=?";
		//		int result = jdbc.queryForObject(sql, new Object[]{id}, Integer.class);
		//		if(result > 0) return true;
		//		else return false;
		int result = mybatis.selectOne("Member.duplCheck", id);
		if(result > 0) return true;
		else return false;
	}

	public int deleteById(String id) throws Exception{
		//		String sql = "delete from member where id=?";
		//		return jdbc.update(sql, id);
		return mybatis.delete("Member.delete", id);
	}



	public int updateByDTO(MemberDTO mdto) throws Exception{

		//		String sql = "update member set pw=?, name=?, phone=?, email=?, zipcode=?, address1=?, address2=? where id =?";
		//		return jdbc.update(sql, mdto.getPw(),mdto.getName(),mdto.getPhone(),mdto.getEmail(), mdto.getZipcode(), mdto.getAddress1(), mdto.getAddress2(), mdto.getId() );
		return mybatis.update("Member.update", mdto);
	}

}
