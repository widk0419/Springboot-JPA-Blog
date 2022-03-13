package com.widk.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.widk.blog.model.Board;
import com.widk.blog.model.Reply;
import com.widk.blog.repository.BoardRepository;
import com.widk.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();
		// jackson 라이브러리 -> model의 getter를 호출.
		// 그럼 board model의 getReply를 호출하고 그럼 또 reply model에서 getBoard를 호출하고,
		// 또 board의 getReply를 호출하고 이런식으로 무한 참조가 발생......
	}

	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
}
