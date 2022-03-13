package com.widk.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widk.blog.dto.ReplySaveRequestDto;
import com.widk.blog.model.Board;
import com.widk.blog.model.User;
import com.widk.blog.repository.BoardRepository;
import com.widk.blog.repository.ReplyRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	// 글 쓰기
	@Transactional
	public void 글쓰기(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	// 글 목록
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	// 글 상세보기
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}

	// 글 삭제하기
	@Transactional
	public void 글삭제(int id) {
		boardRepository.deleteById(id);
	}

	// 글 수정하기
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(service가 종료될 때) 트랜잭션이 종료됨. 이때, 더티체킹이 일어나면서 자동으로 업데이트가 됨.(db flush)
	}

	// 댓글 등록하기
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(),
				replySaveRequestDto.getContent());
		System.out.println("BoardService : " + result); // 오브젝트를 출력하면 자동으로 toString()이 호출됨!!
	}

}
