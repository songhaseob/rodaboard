package kr.or.ddit.board.respository;

import java.util.List;

import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.common.model.PageVo;

public interface BoardRepDaoI {

	int insertBorRep(Board_PostVo board_postVo);
	
	Board_PostVo boardDetail(Board_PostVo board_postVo);
	
	int insertComm(Board_PostVo board_postVo);
	
	int maxPostNo();
	
	int modifyBoard(Board_PostVo board_postVo);
	
	int modifydelBoard(Board_PostVo board_postVo);
	
	
}
