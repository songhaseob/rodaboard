package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface UserServiceI {
	
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
}
