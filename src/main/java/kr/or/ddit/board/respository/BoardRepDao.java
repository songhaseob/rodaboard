package kr.or.ddit.board.respository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.Board_PostVo;

@Repository("boardRepDao")
public class BoardRepDao implements BoardRepDaoI{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public int insertBorRep(Board_PostVo board_postVo) {
		return template.insert("boardrep.insertBorRep", board_postVo);
	}

	@Override
	public Board_PostVo boardDetail(Board_PostVo board_postVo) {
		return template.selectOne("boardrep.boardDetail", board_postVo);
	}

	@Override
	public int insertComm(Board_PostVo board_postVo) {
		return template.insert("boardrep.insertComm", board_postVo);
	}

	@Override
	public int maxPostNo() {
		return template.selectOne("boardrep.maxPostNo");
	}

	@Override
	public int modifyBoard(Board_PostVo board_postVo) {
		return template.update("boardrep.modifyBoard", board_postVo);
	}

	@Override
	public int modifydelBoard(Board_PostVo board_postVo) {
		return template.update("boardrep.modifydelBoard", board_postVo);
	}

}
