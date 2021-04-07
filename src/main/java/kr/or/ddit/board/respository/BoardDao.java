package kr.or.ddit.board.respository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.Board_InfoVo;

@Repository("boardDao")
public class BoardDao implements BoardDaoI {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	//게시판 전체 조회
	@Override
	public List<Board_InfoVo> selectBoard() {
		return template.selectList("board.selectBoard");
	
	}
	
	//게시판 등록
	@Override
	public int insertBoard(Board_InfoVo board_infoVo) {
		return template.insert("board.insertBoard", board_infoVo);
	}

	@Override
	public int updateBoard(Board_InfoVo board_infoVo) {
		return template.update("board.updateBoard", board_infoVo);
	}



	
}
