package kr.or.ddit.board.respository;

import java.util.List;

import kr.or.ddit.board.model.Board_InfoVo;
import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.common.model.PageVo;

public interface BoardPageDaoI {
	
	//페이징처리
	List<Board_PostVo> selectPagingBoard(PageVo pageVo);
	
	int selectAllBoardCnt(PageVo pageVo);
}
