package kh.spring.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardService bservice;

	@Autowired
	private HttpSession session;

	@RequestMapping("test")
	public String test() {
		return "board/test";
	}


	@RequestMapping("toBoard")
	public String toBoard(HttpServletRequest request) throws Exception {
		return "board/boardListView";
	}


	@RequestMapping(value="boardListAjax",produces="application/json;charset=utf8")
	@ResponseBody
	public Object boardListAjax(HttpServletRequest request)  throws Exception {
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		//		System.out.println("boardListAjax 가 받은 cpage : " + cpage );
		List<BoardDTO> list = bservice.selectByPageNo(cpage);
		//		System.out.println("List<BoardDTO> list의 사이즈 : "+list.size());
		//클라이언트는 JSON으로 받는게 좋음 : List<BoardDTO>를 JSON으로
		String respArr = new Gson().toJson(list);
		//		System.out.println("String respArr : "+respArr);
		return respArr;
	}


	@RequestMapping("toBoardContents")
	public String toBoardContents(HttpServletRequest request) throws Exception {
		BoardDTO dto = new BoardDTO();
		dto.setSeq(Integer.parseInt(request.getParameter("seq")));
		List<BoardDTO> bdtoList = bservice.select(dto);
		request.setAttribute("bdtoList", bdtoList);
		
		List<FileDTO> fileList = bservice.selectFileByPSeq(dto.getSeq());
		request.setAttribute("fileList", fileList);
		return "board/boardContentsView";
	}
	@RequestMapping("toBoardWrite")
	public String toBoardWrite() throws Exception {
		return "board/boardWriteView";
	}


	@RequestMapping("boardWrite")
	public String boardWrite(HttpServletRequest request, MultipartFile[] files) throws Exception {
		MemberDTO loginInfo = (MemberDTO)request.getSession().getAttribute("loginInfo");
		String id = loginInfo.getId();
		System.out.println("게시판에 글 쓰는 id : "+id);
		String writer = loginInfo.getName();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String category = request.getParameter("category");
		String ip_address = request.getRemoteAddr();
		BoardDTO bdto = new BoardDTO(0,writer,Timestamp.valueOf(LocalDateTime.now()),0,title,category,id,contents,ip_address);
		bservice.boardWrite(bdto);
		
		String filePath = session.getServletContext().getRealPath("upload/"+id);
		 System.out.println("filePath : "+filePath);
		 
		 //fileDTO 대신 멀티파트파일로 받기
		for(MultipartFile file : files) {
			if(!file.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				File tempFilePath = new File(filePath);
				if(!tempFilePath.exists()) {
					System.out.println(tempFilePath+"가 없으므로 생성합니다.");
					tempFilePath.mkdirs();}
				String systemFileName = uuid.toString()+"_"+file.getOriginalFilename();
				File targetLoc = new File(filePath+"/"+systemFileName);
				System.out.println("targetLoc : "+targetLoc);
				file.transferTo(targetLoc);
				
				String original_file_name = file.getOriginalFilename();
				String stored_file_name = systemFileName;
				int file_size = (int) file.getSize();
				System.out.println("file_size : "+file_size);
				FileDTO fdto = new FileDTO(0,0,original_file_name,stored_file_name,file_size,Timestamp.valueOf(LocalDateTime.now()),id,"n");
				bservice.fileWrite(bdto, fdto);
			}
		}
		return "board/boardListView";
	}

	
	// 클릭시 파일 다운로드
	@RequestMapping("downLoadFile")
	public void downLoadFile(int seq, HttpServletResponse resp) throws Exception {
		FileDTO fdto = bservice.getFileBySeq(seq);
		String filePath = session.getServletContext().getRealPath("upload/"+fdto.getUploader_id());
		System.out.println("filePath : "+filePath);
		File target = new File(filePath+"/"+fdto.getStored_file_name());
		try(DataInputStream dis = new DataInputStream(new FileInputStream(target));
		ServletOutputStream sos = resp.getOutputStream();){
				
		String fileName = new String(fdto.getOriginal_file_name().getBytes("utf8"),"iso-8859-1");	
			
		resp.reset();
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-disposition", "attachment;filename="+fileName+";");
		
		byte[] fileContents = new byte[(int)target.length()];
		dis.readFully(fileContents);
		
		sos.write(fileContents);
		sos.flush();
		}
	}

}
