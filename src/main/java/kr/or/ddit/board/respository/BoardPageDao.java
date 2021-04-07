package kr.or.ddit.board.respository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.common.model.PageVo;

@Repository("boardPageDao")
public class BoardPageDao implements BoardPageDaoI {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<Board_PostVo> selectPagingBoard(PageVo pageVo) {
		return template.selectList("boards.selectPagingBoard", pageVo);
	}

	@Override
	public int selectAllBoardCnt(PageVo pageVo) {
		return template.selectOne("boards.selectAllBoardCnt", pageVo);
	}
	
	
	
	
	
}
