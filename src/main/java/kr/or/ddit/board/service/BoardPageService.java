package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.board.respository.BoardPageDao;
import kr.or.ddit.common.model.PageVo;

@Service("boardPageService")
public class BoardPageService implements BoardPageServiceI{

	@Resource(name="boardPageDao")
	private BoardPageDao boardPageDao;

	@Override
	public Map<String, Object> selectPagingBoard(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Board_PostVo> boardList = boardPageDao.selectPagingBoard(pageVo);
		
		int boardCnt = boardPageDao.selectAllBoardCnt(pageVo);
		map.put("boardList", boardList);
		map.put("boardCnt", boardCnt);
		
		return map;
	}
	
	
}
