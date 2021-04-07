package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.board.respository.BoardRepDao;

@Service("boardRepService")
public class BoardRepService implements BoardRepServiceI{

	@Resource(name="boardRepDao")
	private BoardRepDao boardRepDao;
	
	@Override
	public int insertBorRep(Board_PostVo board_postVo) {
		return boardRepDao.insertBorRep(board_postVo);
	}

	@Override
	public Board_PostVo  boardDetail(Board_PostVo board_postVo) {
		return boardRepDao.boardDetail(board_postVo);
	}

	@Override
	public int insertComm(Board_PostVo board_postVo) {
		return boardRepDao.insertComm(board_postVo);
	}

	@Override
	public int maxPostNo() {
		return boardRepDao.maxPostNo();
	}

	@Override
	public int modifyBoard(Board_PostVo board_postVo) {
		return boardRepDao.modifyBoard(board_postVo);
	}

	@Override
	public int modifydelBoard(Board_PostVo board_postVo) {
		return boardRepDao.modifydelBoard(board_postVo);
	}



}
