package kh.spring.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	private Integer seq;
	private String writer;
	private Timestamp write_date;
	private Integer view_count;
	private String title;
	private String category;
	private String id;
	private String contents;
	private String sdate;
	private String ip_address;
	
	
	public BoardDTO() {}
	
	public BoardDTO(int seq, String writer, Timestamp write_date, int view_count, String title, String category,
			String id, String contents, String ip_address) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.write_date = write_date;
		this.view_count = view_count;
		this.title = title;
		this.category = category;
		this.id = id;
		this.contents = contents;
		this.ip_address = ip_address;
	}



	public BoardDTO(Integer seq, String writer, Timestamp write_date, Integer view_count, String title, String category,
			String id, String contents, String sdate, String ip_address) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.write_date = write_date;
		this.view_count = view_count;
		this.title = title;
		this.category = category;
		this.id = id;
		this.contents = contents;
		this.sdate = sdate;
		this.ip_address = ip_address;
	}

	public BoardDTO(int seq, String writer, Timestamp write_date, int view_count, String title, String category,
			String id, String contents) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.write_date = write_date;
		this.view_count = view_count;
		this.title = title;
		this.category = category;
		this.id = id;
		this.contents = contents;
		this.sdate = new SimpleDateFormat("YYYY-MM-dd").format(write_date);
	}


	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}


	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}

	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getSdate() {
		long write_date = this.write_date.getTime(); // 글 작성 시점
		long current_date = System.currentTimeMillis(); // 현재 시점
		long gapTime = (current_date - write_date)/1000; // ms 로 0.001 초까지 나오기 때문에 1000으로 나눠야 함

		if(gapTime < 60) {
			return "방금 전";
		}else if(gapTime < 300) {
			return "5분 이내";
		}else if(gapTime < 3600) {
			return "1시간 이내";
		}else if(gapTime < 86400) {
			return "24시간 이내";
		}else {
			return sdate;
		}
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
}
