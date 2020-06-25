package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentsDTO;
@Repository
public class CommentsDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	public int insert(int parent_seq, String writer, String id, String contents) throws Exception{
		String sql = "insert into comments values(comments_seq.nextval,?,?,default,?,?)";
		return jdbc.update( sql, parent_seq , writer , id , contents );
	}
	
	public List<CommentsDTO> selectByPseq(int pseq) throws Exception {
		String sql = "select * from comments where parent_seq=?";
		return jdbc.query(sql, new Object[] {pseq}, new RowMapper<CommentsDTO>() {
			@Override
			public CommentsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommentsDTO cdto = new CommentsDTO();
				cdto.setSeq(rs.getInt("seq"));
				cdto.setParent_seq(rs.getInt("parent_seq"));
				cdto.setWriter(rs.getString("writer"));
				cdto.setWrite_date(rs.getTimestamp("write_date"));
				cdto.setId(rs.getString("id"));
				cdto.setContents(rs.getString("contents"));
				return cdto;
			}
		});
	}
	
	// 개수 구하기
	public int countComment(int pseq) throws Exception{
		String sql = "select count(*) from comments where parent_seq=?";
		return jdbc.queryForObject(sql, new Object[] {pseq}, Integer.class);
	}

}
