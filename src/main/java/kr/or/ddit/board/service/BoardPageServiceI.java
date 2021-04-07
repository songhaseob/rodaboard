package kr.or.ddit.board.service;

import java.util.Map;

import kr.or.ddit.common.model.PageVo;

public interface BoardPageServiceI {
	
	//사용자 페이징 조회
	Map<String, Object> selectPagingBoard(PageVo pageVo);
	
}
