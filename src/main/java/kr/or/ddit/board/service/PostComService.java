package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.Post_ComVo;
import kr.or.ddit.board.respository.PostComDao;

@Service("postComService")
public class PostComService implements PostComServiceI{

	@Resource(name="postComDao")
	private PostComDao postComDao;

	@Override
	public int insertPostCom(Post_ComVo post_comVo) {
		return postComDao.insertPostCom(post_comVo);
	}

	@Override
	public List<Post_ComVo> selectBoardComment(Post_ComVo post_comVo) {
		return postComDao.selectBoardComment(post_comVo);
	}

	@Override
	public int deleteComment(Post_ComVo post_comVo) {
		return postComDao.deleteComment(post_comVo);
	}
			
}
