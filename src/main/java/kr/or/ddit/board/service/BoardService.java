package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.Board_InfoVo;
import kr.or.ddit.board.respository.BoardDao;

@Service("boardService")
public class BoardService implements BoardServiceI{

	@Resource(name="boardDao")
	private BoardDao boardDao;
			
	@Override
	public List<Board_InfoVo> selectBoard() {
		return boardDao.selectBoard();
	}

	@Override
	public int insertBoard(Board_InfoVo board_infoVo) {
		return boardDao.insertBoard(board_infoVo);
	}

	@Override
	public int updateBoard(Board_InfoVo board_infoVo) {
		return boardDao.updateBoard(board_infoVo);
	}
}
