package kr.or.ddit.board.respository;

import java.util.List;

import kr.or.ddit.board.model.Post_ComVo;

public interface PostComDaoI {

	int insertPostCom(Post_ComVo post_comVo);
	
	List<Post_ComVo> selectBoardComment(Post_ComVo post_comVo);
	
	int deleteComment(Post_ComVo post_comVo);
	
}
