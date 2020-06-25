package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.service.BoardService;
import kh.spring.service.CommentsService;

@Controller
@RequestMapping("/comments/")
public class CommentsController {

	@Autowired
	private BoardService bservice;
	@Autowired
	private CommentsService cservice;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("writeComments")
	public String writeComments(int seq) {
		return "board/test";
	}
	
}
