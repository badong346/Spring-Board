package kh.spring.dto;

import java.sql.Timestamp;

public class FileDTO {
	
	
	private int seq;
	private int board_seq; 
	private String original_file_name;
	private String stored_file_name;
	private int file_size; 
	private Timestamp upload_date;
	private String uploader_id;
	private String DEL_GB;
	
	
	public FileDTO() {}
	public FileDTO(int seq, int board_seq, String original_file_name, String stored_file_name, int file_size,
			Timestamp upload_date, String uploader_id, String dEL_GB) {
		super();
		this.seq = seq;
		this.board_seq = board_seq;
		this.original_file_name = original_file_name;
		this.stored_file_name = stored_file_name;
		this.file_size = file_size;
		this.upload_date = upload_date;
		this.uploader_id = uploader_id;
		DEL_GB = dEL_GB;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getOriginal_file_name() {
		return original_file_name;
	}
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public Timestamp getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}
	public String getUploader_id() {
		return uploader_id;
	}
	public void setUploader_id(String uploader_id) {
		this.uploader_id = uploader_id;
	}
	public String getDEL_GB() {
		return DEL_GB;
	}
	public void setDEL_GB(String dEL_GB) {
		DEL_GB = dEL_GB;
	}
	
}
