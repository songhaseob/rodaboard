package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.respository.UserDao;
import kr.or.ddit.user.respository.UserDaoI;

@Service("userService")
public class UserService implements UserServiceI{
	
	@Resource(name="userDao")
	private UserDao userDao;

	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	
}
