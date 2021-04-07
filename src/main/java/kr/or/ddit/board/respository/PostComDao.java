package kr.or.ddit.board.respository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.Post_ComVo;

@Repository("postComDao")
public class PostComDao implements PostComDaoI{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public int insertPostCom(Post_ComVo post_comVo) {
		return template.insert("postcom.insertPostCom", post_comVo);
	}

	@Override
	public List<Post_ComVo> selectBoardComment(Post_ComVo post_comVo) {
		return template.selectList("postcom.selectBoardComment", post_comVo);
	}

	@Override
	public int deleteComment(Post_ComVo post_comVo) {
		return template.delete("postcom.deleteComment", post_comVo);
	}
	
	




}
