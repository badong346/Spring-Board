package kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.CommentsDAO;
import kh.spring.dto.CommentsDTO;

@Service
public class CommentsService {
	@Autowired
	private CommentsDAO cdao;

//	public int commentsWrite(CommentsDTO cdto) {
//		return cdao.insert(cdto);
//	}
}
