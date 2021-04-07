package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@RequestMapping(path = "view", method = {RequestMethod.GET})
	public String view() {
		return "login";
	}
	
	@RequestMapping(path = "process", method = {RequestMethod.POST})
	public String process(UserVo userVo, HttpSession session, RedirectAttributes ra, Model model, PageVo pageVo) {
		
		UserVo user = userService.selectUser(userVo.getUserid());
		
		if(user != null && userVo.getPass().equals(user.getPass())) {
			session.setAttribute("S_USER", user);
			model.addAttribute("boardList", boardService.selectBoard());
			return "redirect:/login/main";
		}else {
			return "redirect:/login/view";
		}
	}
	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	
	
}
