package kh.spring.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentsDTO {
	private int seq;
	private int parent_seq;
	private String writer;
	private Timestamp write_date;
	private String id;
	private String contents;
	private String sdate;
	
	
	public CommentsDTO() {}
	
	
	public CommentsDTO(int seq, int parent_seq, String writer, Timestamp write_date, String id, String contents) {
		super();
		this.seq = seq;
		this.parent_seq = parent_seq;
		this.writer = writer;
		this.write_date = write_date;
		this.id = id;
		this.contents = contents;
		this.sdate = new SimpleDateFormat("YYYY-MM-dd").format(write_date);
	}


	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
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
