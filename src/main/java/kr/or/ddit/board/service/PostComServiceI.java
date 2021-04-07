package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.board.model.Post_ComVo;


public interface PostComServiceI {
	
	int insertPostCom(Post_ComVo post_comVo);
	
	List<Post_ComVo> selectBoardComment(Post_ComVo post_comVo);
	
	int deleteComment(Post_ComVo post_comVo);
	
}
