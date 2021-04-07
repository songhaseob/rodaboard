package kr.or.ddit.board.service;

import kr.or.ddit.board.model.Board_PostVo;

public interface BoardRepServiceI {
	
	int insertBorRep(Board_PostVo board_postVo);
	
	Board_PostVo boardDetail(Board_PostVo board_postVo);
	
	int insertComm(Board_PostVo board_postVo);
	
	int maxPostNo();
	
	int modifyBoard(Board_PostVo board_postVo);

	int modifydelBoard(Board_PostVo board_postVo);
	
}
