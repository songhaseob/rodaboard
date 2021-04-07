package kr.or.ddit.board.respository;

import java.util.List;

import kr.or.ddit.board.model.Board_InfoVo;

public interface BoardDaoI {
	List<Board_InfoVo> selectBoard();
	
	int insertBoard(Board_InfoVo board_infoVo);
	
	int updateBoard(Board_InfoVo board_infoVo);
	
}
