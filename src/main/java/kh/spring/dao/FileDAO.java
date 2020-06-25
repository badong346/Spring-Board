package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;

@Repository
public class FileDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	public int insert(BoardDTO bdto, FileDTO fdto) throws Exception{
		String sql = "insert into board_file values(board_file_seq.nextval, board_seq.CURRVAL, ?,?,?,default,?,default)";
		return jdbc.update(sql, fdto.getOriginal_file_name(),fdto.getStored_file_name(),fdto.getFile_size(),bdto.getId());
	}
	
	public List<FileDTO> selectByPSeq(int pseq) throws Exception{
		String sql = "select * from board_file where board_seq=?";
		return jdbc.query(sql, new Object[] {pseq}, new RowMapper<FileDTO>() {
			@Override
			public FileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileDTO fdto = new FileDTO();
				fdto.setSeq(rs.getInt("seq"));
				fdto.setBoard_seq(rs.getInt("board_seq"));
				fdto.setOriginal_file_name(rs.getString("original_file_name"));
				fdto.setStored_file_name(rs.getString("stored_file_name"));
				fdto.setFile_size(rs.getInt("file_size"));
				fdto.setUpload_date(rs.getTimestamp("upload_date"));
				fdto.setUploader_id(rs.getString("uploader_id"));
				fdto.setDEL_GB(rs.getString("DEL_GB"));
				return fdto;
			}
		});
	}
	public FileDTO selectBySeq(int seq) throws Exception{
		String sql = "select * from board_file where seq=?";
		return jdbc.queryForObject(sql, new Object[] {seq}, new RowMapper<FileDTO>() {
			@Override
			public FileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileDTO fdto = new FileDTO();
				fdto.setSeq(rs.getInt("seq"));
				fdto.setBoard_seq(rs.getInt("board_seq"));
				fdto.setOriginal_file_name(rs.getString("original_file_name"));
				fdto.setStored_file_name(rs.getString("stored_file_name"));
				fdto.setFile_size(rs.getInt("file_size"));
				fdto.setUpload_date(rs.getTimestamp("upload_date"));
				fdto.setUploader_id(rs.getString("uploader_id"));
				fdto.setDEL_GB(rs.getString("DEL_GB"));
				return fdto;
			}
		});
	}
	
	
}
