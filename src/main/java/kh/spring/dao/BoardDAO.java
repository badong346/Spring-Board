package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentsDTO;
import kh.spring.statics.Configuration;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private SqlSessionTemplate mybatis;

	public int insert(BoardDTO dto) throws Exception{
//		String sql = "insert into board values(board_seq.nextval,?,default,default,?,?,?,?,?)";
//		return jdbc.update(sql, dto.getWriter() , dto.getTitle() , dto.getCategory() , dto.getId() , dto.getContents(), dto.getIp_address() );
		return mybatis.insert("Board.insert",dto);
	}

	public int delete(int seq) throws Exception{
//		String sql = "delete from board where seq=?";
//		return jdbc.update(sql, seq );
		return mybatis.delete("Board.delete", seq);
	}

	
	public int viewUp(int seq) throws Exception{
//		String sql = "update board set view_count=view_count+1 where seq=?";
//		return jdbc.update(sql, seq );
		return mybatis.update("Board.viewUp", seq);
	}

	
//	public BoardDTO select(BoardDTO dto) throws Exception{
//		String sql = "select * from board where seq=?";
//		return jdbc.queryForObject(sql, new Object[] {seq}, new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO bdto = new BoardDTO();
//				bdto.setSeq(seq);
//				bdto.setWriter(rs.getString("writer"));
//				bdto.setWrite_date(rs.getTimestamp("write_date"));
//				bdto.setView_count(rs.getInt("view_count"));
//				bdto.setTitle(rs.getString("title"));
//				bdto.setCategory(rs.getString("category"));
//				bdto.setId(rs.getString("id"));
//				bdto.setContents(rs.getString("contents"));
//				bdto.setIp_address(rs.getString("ip_address"));
//				return bdto;
//			}
//		});
//		return mybatis.selectOne("Board.select", dto);
//	}
	
	public List<BoardDTO> select(BoardDTO dto) throws Exception{
		return mybatis.selectList("Board.select", dto);
	}
	
//	public List<BoardDTO> selectAll() throws Exception{
//		String sql = "select * from board order by 1";
//		return jdbc.query(sql,new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO bdto = new BoardDTO();
//				bdto.setSeq(rs.getInt("seq"));
//				bdto.setWriter(rs.getString("writer"));
//				bdto.setWrite_date(rs.getTimestamp("write_date"));
//				bdto.setView_count(rs.getInt("view_count"));
//				bdto.setTitle(rs.getString("title"));
//				bdto.setCategory(rs.getString("category"));
//				bdto.setId(rs.getString("id"));
//				bdto.setContents(rs.getString("contents"));
//
//				bdto.setIp_address(rs.getString("ip_address"));
//				return bdto;
//			}
//		});
//		return mybatis.selectList("Board.selectAll");
//	}

	public List<BoardDTO> selectAllDesc() throws Exception{
//		String sql = "select * from board order by 1 desc";
//		return jdbc.query(sql,new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO bdto = new BoardDTO();
//				bdto.setSeq(rs.getInt("seq"));
//				bdto.setWriter(rs.getString("writer"));
//				bdto.setWrite_date(rs.getTimestamp("write_date"));
//				bdto.setView_count(rs.getInt("view_count"));
//				bdto.setTitle(rs.getString("title"));
//				bdto.setCategory(rs.getString("category"));
//				bdto.setId(rs.getString("id"));
//				bdto.setContents(rs.getString("contents"));
//				bdto.setIp_address(rs.getString("ip_address"));
//				return bdto;
//			}
//		});
		return mybatis.selectList("Board.selectAllDesc");

	}	

	public List<BoardDTO> selectByPageNo(Map<String,Integer> param) throws Exception{
//		String sql = "select * from (select board.*, row_number() over(order by seq desc) as rnum from board) where rnum between ? and ?";

//		return jdbc.query(sql,new Object[] {start,end},new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO bdto = new BoardDTO();
//				bdto.setSeq(rs.getInt("seq"));
//				bdto.setWriter(rs.getString("writer"));
//				bdto.setWrite_date(rs.getTimestamp("write_date"));
//				bdto.setView_count(rs.getInt("view_count"));
//				bdto.setTitle(rs.getString("title"));
//				bdto.setCategory(rs.getString("category"));
//				bdto.setId(rs.getString("id"));
//				bdto.setContents(rs.getString("contents"));
//
//				bdto.setIp_address(rs.getString("ip_address"));
//				return bdto;
//			}
//		});
		return mybatis.selectList("Board.selectByPageNo",param);

	}

	public List<BoardDTO> selectByView(Map<String,Integer> param) throws Exception{
//		String sql = "select * from (select board.*, row_number() over(order by view_count desc, seq desc) as rnum from board) where rnum between ? and ?";

//		return jdbc.query(sql,new Object[] {start,end},new RowMapper<BoardDTO>() {
//			@Override
//			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardDTO bdto = new BoardDTO();
//				bdto.setSeq(rs.getInt("seq"));
//				bdto.setWriter(rs.getString("writer"));
//				bdto.setWrite_date(rs.getTimestamp("write_date"));
//				bdto.setView_count(rs.getInt("view_count"));
//				bdto.setTitle(rs.getString("title"));
//				bdto.setCategory(rs.getString("category"));
//				bdto.setId(rs.getString("id"));
//				bdto.setContents(rs.getString("contents"));
//
//				bdto.setIp_address(rs.getString("ip_address"));
//				return bdto;
//			}
//		});
		return mybatis.selectList("Board.selectByView",param);

	}
	
	public int getArticleCount() throws Exception{
//		String sql = "select count(*) from board";
//		return jdbc.update(sql);
		return mybatis.selectOne("Board.selectCount");
	}

	//	public String getPageNaviBySearch(int currentPage, String category, String title) throws Exception{
	//		int recordTotalCount = this.getArticleCountBySearch(category, title); 
	//		int pageTotalCount = 0;
	//		if(recordTotalCount % Configuration.recordCountPerPage > 0) {
	//			pageTotalCount = recordTotalCount / Configuration.recordCountPerPage + 1;	
	//		}else {
	//			pageTotalCount = recordTotalCount / Configuration.recordCountPerPage;
	//		}
	//		if(currentPage < 1) {
	//			currentPage = 1;
	//		}else if(currentPage > pageTotalCount) {
	//			currentPage = pageTotalCount;
	//		}
	//		int startNavi = (currentPage - 1) / Configuration.naviCountPerPage * Configuration.naviCountPerPage + 1;
	//		int endNavi = startNavi + Configuration.naviCountPerPage - 1;
	//		if(endNavi > pageTotalCount) {
	//			endNavi = pageTotalCount;
	//		}
	//		boolean needPrev = true;
	//		boolean needNext = true;
	//		if(startNavi == 1) {needPrev = false;}
	//		if(endNavi == pageTotalCount) {needNext = false;}
	//
	//		StringBuilder sb = new StringBuilder();
	//		if(needPrev) {sb.append("<li class='page-item'><a class='page-link' href='board.req?cpage="+(startNavi-1)+"&sort='><i class='fas fa-chevron-left'></i></a></li>");}
	//		for(int i = startNavi;i <= endNavi;i++) {
	//			sb.append("<li class='page-item'><a class='page-link' href='board.req?cpage="+i+"&sort='>" + i + "</a></li>");
	//		}
	//		if(needNext) {sb.append("<li class='page-item'><a class='page-link' href='board.req?cpage="+(endNavi+1)+"&sort='><i class='fas fa-chevron-right'></i></a></li>");}
	//		return sb.toString();
	//	}

	//	public List<RequestDTO> searchByView(int cpage, String category, String keyword) throws Exception{
	//		String sql = "select * from (select request_board.*, row_number() over(order by view_count desc, seq desc) as rnum from request_board where category=? and REGEXP_LIKE(title,?)) where rnum between ? and ?";
	//		int start = cpage * Configuration.recordCountPerPage - (Configuration.recordCountPerPage-1);
	//		int end = start + (Configuration.recordCountPerPage-1);
	//		List<RequestDTO> list = new ArrayList<>();
	//
	//		try(Connection con = this.getConnection();
	//				PreparedStatement pstat = con.prepareStatement(sql);){
	//			pstat.setString(1, category);
	//			pstat.setString(2, "[" + keyword + "]");
	//			pstat.setInt(3, start);
	//			pstat.setInt(4, end);
	//			try(ResultSet rs = pstat.executeQuery();){
	//				while(rs.next()) {
	//					int seq = rs.getInt(1);
	//					String writer = rs.getString(2);
	//					Timestamp write_date = rs.getTimestamp(3);
	//					int view_count = rs.getInt(4);
	//					String title = rs.getString(5);
	//					String category2 = rs.getString(6);
	//					String id = rs.getString(7);
	//					String contents = rs.getString(8);
	//					list.add(new RequestDTO(seq,writer,write_date,view_count,title,category2,id,contents));
	//				}
	//				return list;
	//			}
	//		}
	//	}

	//	public List<RequestDTO> search(int cpage, String category, String keyword) throws Exception{
	//		String sql = null;
	//		if(category.contentEquals("")&&keyword.contentEquals("")) { // category, keyword가 비어있는 경우
	//			sql = "select * from (select request_board.*, row_number() over(order by seq desc) as rnum from request_board) where rnum between ? and ?";
	//		}else if(category.contentEquals("")&&!keyword.contentEquals("")) { // keyword만 입력한 경우
	//			sql = "select * from (select request_board.*, row_number() over(order by seq desc) as rnum from request_board where REGEXP_LIKE(title,?)) where rnum between ? and ?";
	//		}else if(!category.contentEquals("")&&keyword.contentEquals("")) { // category만 입력한 경우
	//			sql = "select * from (select request_board.*, row_number() over(order by seq desc) as rnum from request_board where category=?) where rnum between ? and ?";
	//		}else { // 둘 다 정상적으로 입력한 경우
	//			sql = "select * from (select request_board.*, row_number() over(order by seq desc) as rnum from request_board where category=? and REGEXP_LIKE(title,?)) where rnum between ? and ?";
	//		}
	//		int start = cpage * Configuration.recordCountPerPage - (Configuration.recordCountPerPage-1);
	//		int end = start + (Configuration.recordCountPerPage-1);
	//		List<RequestDTO> list = new ArrayList<>();
	//		try(Connection con = this.getConnection();
	//				PreparedStatement pstat = con.prepareStatement(sql);){
	//			if(category.contentEquals("")&&keyword.contentEquals("")) { // category, keyword가 비어있는 경우
	//				pstat.setInt(1, start);
	//				pstat.setInt(2, end);
	//			}else if(category.contentEquals("")&&!keyword.contentEquals("")) { // keyword만 입력한 경우
	//				pstat.setString(1, "[" + keyword + "]");
	//				pstat.setInt(2, start);
	//				pstat.setInt(3, end);
	//			}else if(!category.contentEquals("")&&keyword.contentEquals("")) { // category만 입력한 경우
	//				pstat.setString(1, category);
	//				pstat.setInt(2, start);
	//				pstat.setInt(3, end);
	//			}else { // 둘 다 정상적으로 입력한 경우
	//				pstat.setString(1, category);
	//				pstat.setString(2, "[" + keyword + "]");
	//				pstat.setInt(3, start);
	//				pstat.setInt(4, end);
	//			}
	//			try(ResultSet rs = pstat.executeQuery();){
	//				while(rs.next()) {
	//					int seq = rs.getInt(1);
	//					String writer = rs.getString(2);
	//					Timestamp write_date = rs.getTimestamp(3);
	//					int view_count = rs.getInt(4);
	//					String title = rs.getString(5);
	//					String category2 = rs.getString(6);
	//					String id = rs.getString(7);
	//					String contents = rs.getString(8);
	//					list.add(new RequestDTO(seq,writer,write_date,view_count,title,category2,id,contents));
	//				}
	//				return list;
	//			}
	//		}
	//	}

	//	public int getArticleCountBySearch(String category, String keyword) throws Exception{
	//		String sql = null;
	//		if(category.contentEquals("")&&keyword.contentEquals("")) { // category, keyword가 비어있는 경우
	//			sql = "select count(*) from request_board";
	//		}else if(category.contentEquals("")&&!keyword.contentEquals("")) { // keyword만 입력한 경우
	//			sql = "select count(*) from request_board where REGEXP_LIKE(title,?)";
	//		}else if(!category.contentEquals("")&&keyword.contentEquals("")) { // category만 입력한 경우
	//			sql = "select count(*) from request_board where category=?";
	//		}else { // 둘 다 정상적으로 입력한 경우
	//			sql = "select count(*) from request_board where category=? and REGEXP_LIKE(title,?)";
	//		}
	//		try(Connection con = this.getConnection();
	//				PreparedStatement pstat = con.prepareStatement(sql);){
	//			if(category.contentEquals("")&&keyword.contentEquals("")) { // category, keyword가 비어있는 경우
	//
	//			}else if(category.contentEquals("")&&!keyword.contentEquals("")) { // keyword만 입력한 경우
	//				pstat.setString(1, "[" + keyword + "]");
	//			}else if(!category.contentEquals("")&&keyword.contentEquals("")) { // category만 입력한 경우
	//				pstat.setString(1, category);
	//			}else { // 둘 다 정상적으로 입력한 경우
	//				pstat.setString(1, category);	
	//				pstat.setString(2, "[" + keyword + "]");
	//			}
	//			try(ResultSet rs = pstat.executeQuery();){
	//				rs.next();
	//				return rs.getInt(1);				
	//			}
	//		}
	//	}
}
