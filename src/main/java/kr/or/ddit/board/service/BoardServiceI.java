package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.Board_InfoVo;

public interface BoardServiceI {
	
	List<Board_InfoVo> selectBoard();
	
	int insertBoard(Board_InfoVo board_infoVo);
	
	int updateBoard(Board_InfoVo board_infoVo);
}
