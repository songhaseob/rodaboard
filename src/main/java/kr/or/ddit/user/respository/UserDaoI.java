package kr.or.ddit.user.respository;

import kr.or.ddit.user.model.UserVo;

public interface UserDaoI {

	UserVo selectUser(String userid);

}
