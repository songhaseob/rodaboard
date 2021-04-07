package kr.or.ddit.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.model.Board_InfoVo;
import kr.or.ddit.board.model.Board_PostVo;
import kr.or.ddit.board.model.Post_ComVo;
import kr.or.ddit.board.service.BoardPageService;
import kr.or.ddit.board.service.BoardRepService;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.PostComService;
import kr.or.ddit.common.model.PageVo;

@Controller
@RequestMapping("board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Resource(name="boardService")
	private BoardService boardService;

	@Resource(name="boardPageService")
	private BoardPageService boardPageService;
	
	@Resource(name="boardRepService")
	private BoardRepService boardRepService;
	
	@Resource(name="postComService")
	private PostComService postcomService;
	
	
	@RequestMapping(path="insertBoard", method = RequestMethod.GET)
	public String insertBoard(Model model) {
		model.addAttribute("boardIList", boardService.selectBoard());
		
		return "board/insertBoard";
	}
	
	
	@RequestMapping(path="insertBoard", method = {RequestMethod.POST})
	public String insertBoard(HttpServletRequest req, RedirectAttributes ra) {
		
		String bor_Name = req.getParameter("bor_name");
		String bor_act = req.getParameter("bor_act");
		
		int flag = 0;
		
		if(bor_act.equals("t")) {
			flag = 1;
		}
		
		Board_InfoVo boardInfo = new Board_InfoVo();
		
		boardInfo.setBor_act(flag);
		boardInfo.setBor_name(bor_Name);
		
		
		int insertCnt = 0;
		
		try {
			insertCnt = boardService.insertBoard(boardInfo);
		} catch (Exception e) {
			insertCnt = 0;
		}
		if(insertCnt == 1) {
			return "redirect:/board/insertBoard";
		}else {
			return "main";
		}
	}
	
	
	@RequestMapping("updateBoard")
	public String updateBoard(HttpServletRequest req, RedirectAttributes ra) {
		String bor_Name = req.getParameter("bor_name");
		String bor_act = req.getParameter("bor_act");
		
		int folg = 0;
		
		if(bor_act.equals("t")) {
			folg = 1;
		}
		
		Board_InfoVo board_infoVo = new Board_InfoVo();
		
		board_infoVo.setBor_name(bor_Name);
		board_infoVo.setBor_act(folg);
		
		int updateCnt = 0;
		
		try {
			updateCnt = boardService.updateBoard(board_infoVo);
		} catch (Exception e) {
			updateCnt = 0;
		}
		
		if(updateCnt == 1 ) {
			return "redirect:/board/insertBoard";
		}else {
			return "main";
		}
	}
	
	
	@RequestMapping("pagingBoard")
	public String pagingBoard(@RequestParam(defaultValue ="1") int page,
							  @RequestParam(defaultValue="10") int pageSize,
							  HttpServletRequest req) {
		
		String pageParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pageSize");
		
		int bor_no = Integer.parseInt(req.getParameter("bor_no"));
		
		page = 0;
		pageSize = 0;
		
		if(pageParam == null || pageParam.equals("")) {
			page = 1;
		}else {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		if(pageSizeParam == null || pageSizeParam.equals("")) {
			pageSize = 10;
		}else {
			pageSize = Integer.parseInt(req.getParameter("pageSize"));
		}
		
		PageVo pageVo = new PageVo(page, pageSize, bor_no);

		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		pageVo.setPageNum(bor_no);
		
		Map<String, Object> map = (Map<String, Object>) boardPageService.selectPagingBoard(pageVo);
		
		List<Board_PostVo> pagingList = (List<Board_PostVo>) map.get("boardList");
		int boardCnt = (int)map.get("boardCnt");
		int pagination = (int) Math.ceil((double) boardCnt / pageSize);
		
		List<Board_InfoVo> board_info_List = boardService.selectBoard();
		
		req.setAttribute("boardIList", board_info_List);

		logger.debug("paginList : {}",pagingList);
		
		req.setAttribute("borno", bor_no);
		req.setAttribute("pagingList", pagingList);
		req.setAttribute("pagination", pagination);
		req.setAttribute("pageVo",pageVo);
		
		return "board/pagingBoard";
	}
	
	
	@RequestMapping(path="insertBoardRep", method = {RequestMethod.GET})
	public String insertBoardRep(int bor_no, Model model) {
		
		model.addAttribute("boardIList", boardService.selectBoard());
		model.addAttribute("bor_no", bor_no);
		
		return "board/insertBoardRep";
		
	}
	
	
	@RequestMapping(path="insertBoardRep", method = {RequestMethod.POST})
	public String insertBoardRep(int bor_no, String user_id, String title, String cont, Model model) {
		Board_PostVo borPostVo = new Board_PostVo();
		
		borPostVo.setBor_no(bor_no);
		borPostVo.setUser_id(user_id);
		borPostVo.setTitle(title);
		borPostVo.setCont(cont);
		
		int insertCnt = 0;
		
		try {
			insertCnt = boardRepService.insertBorRep(borPostVo);
		} catch (Exception e) {
			e.printStackTrace();
			insertCnt = 0;
		}
		
		if(insertCnt == 1) {
			return "redirect:/board/pagingBoard?bor_no="+bor_no+"&user_id="+user_id;
		}else {
			return "board/insertBoardRep";
		}
	}
	
	@RequestMapping(path="boardDetail", method = {RequestMethod.GET})
	public String boardDetail(int bor_no, int post_no, String user_id, Model model) {
		
		Board_PostVo borList = new Board_PostVo();
		borList.setBor_no(bor_no);
		borList.setPost_no(post_no);
		borList.setUser_id(user_id);
		
		Post_ComVo post_comVo = new Post_ComVo();
		post_comVo.setBor_no(bor_no);
		post_comVo.setPost_no(post_no);
		post_comVo.setUser_id(user_id);
		
		
		
		model.addAttribute("boardIList", boardService.selectBoard());

		List<Post_ComVo> post_comList = postcomService.selectBoardComment(post_comVo);
		model.addAttribute("post_comList", post_comList);
		
		Board_PostVo boardDetail = boardRepService.boardDetail(borList);
		
		model.addAttribute("boardDetail", boardDetail);
		
		return "board/board";
	}
	
	
	@RequestMapping(path="modifyBoard", method = RequestMethod.GET)
	public String modifyBoard(int bor_no, int post_no, String user_id,Model model) {
	
		Board_PostVo board_postVo = new Board_PostVo();
		board_postVo.setBor_no(bor_no);
		board_postVo.setPost_no(post_no);
		board_postVo.setUser_id(user_id);
		
		Board_PostVo boardListVo = boardRepService.boardDetail(board_postVo);
		
		model.addAttribute("boardIList", boardService.selectBoard());
		
		model.addAttribute("board_postVo", board_postVo);
		model.addAttribute("boardListVo", boardListVo);
		
		return "board/boardModify";
	}
	
	@RequestMapping(path="modifyBoard", method = RequestMethod.POST)
	public String modifyBoard(int bor_no, int post_no, String user_id, String title, String cont) {
		
		Board_PostVo board_postVo = new Board_PostVo();
		
		board_postVo.setBor_no(bor_no);
		board_postVo.setPost_no(post_no);
		board_postVo.setUser_id(user_id);
		board_postVo.setTitle(title);
		board_postVo.setCont(cont);
		
		int updateCnt = 0;
		try {
			updateCnt = boardRepService.modifyBoard(board_postVo);
		} catch (Exception e) {
			updateCnt = 0;
			e.printStackTrace();
		}
		
		if(updateCnt == 1) {
			return "redirect:/board/boardDetail?bor_no=" +bor_no+ "&post_no="+post_no+ "&user_id="+user_id;
		}else {
			return "board/boardModify";
		}
	}
	
	
	@RequestMapping(path="modifydelBoard", method = RequestMethod.POST)
	public String modifydelBoard(int bor_no, int post_no, String user_id) {
		Board_PostVo board_postVo = new Board_PostVo();
		
		board_postVo.setBor_no(bor_no);
		board_postVo.setPost_no(post_no);
		board_postVo.setUser_id(user_id);
		
		int updatedelCnt = 0;
		try {
			updatedelCnt = boardRepService.modifydelBoard(board_postVo);
		} catch (Exception e) {
			updatedelCnt = 0;
		}
		
		if(updatedelCnt == 1) {
			return "redirect:/board/pagingBoard?bor_no=" + bor_no + "&post_no="+post_no+"&user_id="+user_id;
		}else {
			return "board/board";
		}
	}
	
	
	@RequestMapping(path="insertComm", method = RequestMethod.GET)
	public String insertComm(int bor_no, int post_no, String user_id, Model model) {
		Board_PostVo borList = new Board_PostVo();
		
		borList.setBor_no(bor_no);
		borList.setPost_no(post_no);
		borList.setUser_id(user_id);
		
		Board_PostVo boardListVo = boardRepService.boardDetail(borList);
		
		model.addAttribute("boardIList", boardService.selectBoard());
		model.addAttribute("boardListVo", boardListVo);
		
		return "board/insertComm";
	}
	
	@RequestMapping(path="insertComm", method = RequestMethod.POST)
	public String insertComm(String user_id, String title, String cont, String rep_user_id,
							int bor_no, int post_no) {
		
		Board_PostVo boardVo = new Board_PostVo();
		
		boardVo.setBor_no(bor_no);
		boardVo.setUser_id(user_id);
		boardVo.setTitle(title);
		boardVo.setCont(cont);
		
		
		boardVo.setBor_c_nm(bor_no);
		boardVo.setPost_c_no(post_no);
		boardVo.setRep_user_id(rep_user_id);
		
		int insetContCnt = boardRepService.insertComm(boardVo);
		
		if( insetContCnt == 1) {
			
			int postMaxNo = boardRepService.maxPostNo();
			return "redirect:/board/boardDetail?bor_no="+bor_no+"&user_id="+user_id +"&post_no="+postMaxNo;
		}
		else {
			return "board/insertComm";
		}
	}
	
	
	@RequestMapping("registComent")
	public String registComent(int bor_no, int post_no, String user_id, 
								String com_user_id,	String com_con, Model model,RedirectAttributes ra) {
		
		com_con = com_con.replaceAll("\n","<br>");
		
		Post_ComVo post_comVo = new Post_ComVo();
		
		post_comVo.setBor_no(bor_no);
		post_comVo.setPost_no(post_no);
		post_comVo.setCom_con(com_con);
		post_comVo.setCom_user_id(user_id);
		post_comVo.setUser_id(com_user_id);
		
		postcomService.insertPostCom(post_comVo);
		
		ra.addAttribute("bor_no", bor_no);
		ra.addAttribute("post_no", post_no);
		ra.addAttribute("user_id", com_user_id);
		
		return "redirect:/board/boardDetail";
	}
	
	
	@RequestMapping("deleteComment")
	public String deleteComment(int com_no, int bor_no, int post_no, String ruser_id,
									String postcom, Model model,RedirectAttributes ra) {
		Post_ComVo post_comVo = new Post_ComVo();
		post_comVo.setCom_no(com_no);
		
		postcomService.deleteComment(post_comVo);
		
		ra.addAttribute("bor_no", bor_no);
		ra.addAttribute("post_no", post_no);
		ra.addAttribute("user_id", ruser_id);
		
		return "redirect:/board/boardDetail";
	}
	
}
