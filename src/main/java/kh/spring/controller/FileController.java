package kh.spring.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.FileDTO;
import kh.spring.dto.TempDTO;
import kh.spring.service.BoardService;

@Controller
public class FileController {
	@Autowired
	private HttpSession session;
	@Autowired
	private BoardService bservice;
	
	@RequestMapping("fileUpload")
	public String fileUpload(TempDTO dto) throws Exception{

		String filePath = session.getServletContext().getRealPath("upload");
		File tempFilePath = new File(filePath);
		if(!tempFilePath.exists()) {tempFilePath.mkdir();}
		String systemFileName = System.currentTimeMillis()+"_"+dto.getFile().getOriginalFilename();
		File targetLoc = new File(filePath+"/"+systemFileName);
		dto.getFile().transferTo(targetLoc);
		return "home";
	}

	@RequestMapping("filesUpload")  //fileDTO 대신 MultipartFile로 받기
	public String filesUpload(MultipartFile[] files) throws Exception{
		String filePath = session.getServletContext().getRealPath("upload");
		for(MultipartFile file : files) {
			if(!file.isEmpty()) {
				System.out.println("filesUpload : "+file.getOriginalFilename());
				//file.transferTo(new File());
				String systemFileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
				File targetLoc = new File(filePath+"/"+systemFileName);
				file.transferTo(targetLoc);
			}
		}
		return "home";
	}
	
	


}
