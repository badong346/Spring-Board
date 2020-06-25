package kh.spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class TempDTO {
private String writer;
private String contents;
private MultipartFile file;



public TempDTO() {}
public TempDTO(String writer, String contents, MultipartFile file) {
	super();
	this.writer = writer;
	this.contents = contents;
	this.file = file;
}
public String getWriter() {
	return writer;
}
public void setWriter(String writer) {
	this.writer = writer;
}
public String getContents() {
	return contents;
}
public void setContents(String contents) {
	this.contents = contents;
}
public MultipartFile getFile() {
	return file;
}
public void setFile(MultipartFile file) {
	this.file = file;
}

}
