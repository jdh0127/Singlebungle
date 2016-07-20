package Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Whatdo.Comment;
import Whatdo.Whatdo;
import Whatdo.WhatdoDAO;
import Whatdo.WhatplaDAO;
import spring.FavVO;
import spring.PagingCount;

@Controller
public class WhatdoController {
	private WhatdoDAO whatdoDao;
	private WhatplaDAO whatplaDao;

	public void setWhatdoDao(WhatdoDAO whatdoDao) {
		this.whatdoDao = whatdoDao;
	}

	public void setWhatplaDao(WhatplaDAO whatplaDao) {
		this.whatplaDao = whatplaDao;
	}

	

	@RequestMapping("/whateat/list")
	public String list(@ModelAttribute("cmd") Whatdo whatdo, Errors errors, Model model, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return "Whatdo/res_boardList";
		}
		int pagelink =1;
		int startRcdNo = 1;
		// pagelink, startRcdNo �뙆�씪誘명꽣瑜� 諛쏅뒗�떎.諛쏆� 寃� �뾾�떎硫�, 留� 泥ロ럹�씠吏� 異쒕젰

		if(request.getParameter("pagelink")!=null){
			 pagelink = Integer.parseInt(request.getParameter("pagelink"));
		}else{
		}
		
		if(request.getParameter("startRcdNo")!=null){
			 startRcdNo=Integer.parseInt(request.getParameter("startRcdNo"));
		}else{
		}
		
		// �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔 �젙�쓽
		int rcdsinone = 5;
		
		// 寃뚯떆�뙋 �뀒�씠釉붿쓽 珥� �젅肄붾뱶媛쒖닔 援ы븯湲�
		int totrcds = whatdoDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;

		List<Whatdo> whatdos = whatdoDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("whatdos", whatdos);
		return "Whatdo/res_boardList";
	}

	@RequestMapping("/whateat/write")
	public String write() {

		return "Whatdo/res_boardUpload";
	}

	@RequestMapping(value = "/whateat/writedo", method = RequestMethod.POST)
	public String writedo(Whatdo whatdo) {
		Whatdo whatdo1 = new Whatdo(whatdo.getTitle(), whatdo.getNick(), whatdo.getContent(), whatdo.getTime(),
				whatdo.getLoc());
		whatdoDao.insert(whatdo1);
		return "redirect:/whateat/list";

	}

	@RequestMapping(value = "/whateat/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("cmd") Whatdo whatdo, Errors erros, Model model, HttpServletRequest request) {
		if (erros.hasErrors()) {
			return "Whatdo/res_boardList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Whatdo> whatdos = whatdoDao.search(search, sec);
		model.addAttribute("whatdos", whatdos);
		return "Whatdo/res_boardList";
	}

	@RequestMapping(value = "/whateat/view", method = RequestMethod.GET)
	public String view(@ModelAttribute("cmd") Whatdo whatdo, Model model, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = whatdoDao.updateHit(mid);
		if (updateCount != 0) {
			whatdo = whatdoDao.selectBoardByMid(mid);
			model.addAttribute("whatdos", whatdo);
		}
		return "Whatdo/res_boardView";
	}

	@RequestMapping("/whateat/update")
	public String update(Whatdo whatdo, Model model, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatdo = whatdoDao.selectBoardByMid(mid);
		model.addAttribute("whatdos", whatdo);
		return "Whatdo/res_boardUpdate";
	}

	@RequestMapping(value = "/whateat/updatedo", method = RequestMethod.POST)
	public String update(Whatdo whatdo, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatdo = whatdoDao.update(whatdo);
		return "redirect:/whateat/view?mid=" + mid;

	}

	@RequestMapping("/whateat/delete")
	public String delete(Whatdo whatdo, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatdoDao.delete(mid);
		return "redirect:/whateat/list";
	}
	
	
	// 의견보기
	@RequestMapping("/whateat/view/comment")
	@ResponseBody
	public Object comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatdoDao.getCommentListres_board(ref));
		return object;
	}
	//의견 쓰기
	@RequestMapping("/whateat/view/comment_insert")
	@ResponseBody
	public Object comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		String writer = request.getParameter("writer");
         Calendar date = Calendar.getInstance();
         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
         String date1 = today.format(date.getTime());
		comment = new Comment(ref, writer, content, date1);
		whatdoDao.insertComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",whatdoDao.getCommentListres_board(ref));
		return object;
	}
	//의견 삭제
	@RequestMapping("/whateat/view/comment_delete")
	@ResponseBody
	public Object comment_delete(@ModelAttribute("cmd") Whatdo whatdo, HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		whatdoDao.deleteComment(num);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatdoDao.getCommentListres_board(ref));
		return object;
	}
	//의견 수정
	@RequestMapping("/whateat/view/comment_update")
	@ResponseBody
	public Object comment_update(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		String cmt = request.getParameter("cmt");
		Comment comment = new Comment(num, cmt);
		whatdoDao.updateComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatdoDao.getCommentListres_board(ref));
		return object;
	}
	//좋아요
	@RequestMapping("/whateat/view/like")
	@ResponseBody
	public Object board_like(HttpServletRequest request, Whatdo whatdo, Model model){
		
		int mid = Integer.parseInt(request.getParameter("mid"));
		String nick = request.getParameter("user");
		
		FavVO fav = new FavVO(mid, nick);
		int success = whatdoDao.checkFav(fav);
		if(success == 0){
			whatdoDao.insertFav(fav);
			whatdoDao.updateFavpluse(mid);
		}
		if(success == 1){
			whatdoDao.deleteFav(fav);
			whatdoDao.updateFavcan(mid);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",whatdoDao.selectBoardByMid(mid));
		return object;
	}

	// 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌉쏙옙占쏙옙
	@RequestMapping("/whatpla/list")
	public String plalist(@ModelAttribute("cmd") Whatdo whatdo, Errors errors, Model model,
			HttpServletRequest request) {
		if (errors.hasErrors()) {
			return "Whatdo/pla_boardList";
		}
		int pagelink =1;
		int startRcdNo = 1;
		// pagelink, startRcdNo �뙆�씪誘명꽣瑜� 諛쏅뒗�떎.諛쏆� 寃� �뾾�떎硫�, 留� 泥ロ럹�씠吏� 異쒕젰

		if(request.getParameter("pagelink")!=null){
			 pagelink = Integer.parseInt(request.getParameter("pagelink"));
		}else{
		}
		
		if(request.getParameter("startRcdNo")!=null){
			 startRcdNo=Integer.parseInt(request.getParameter("startRcdNo"));
		}else{
		}
		
		// �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔 �젙�쓽
		int rcdsinone = 5;
		
		// 寃뚯떆�뙋 �뀒�씠釉붿쓽 珥� �젅肄붾뱶媛쒖닔 援ы븯湲�
		int totrcds = whatplaDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;

		List<Whatdo> whatdos = whatplaDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("whatdos", whatdos);
		return "Whatdo/pla_boardList";
	}

	@RequestMapping("/whatpla/write")
	public String plawrite() {

		return "Whatdo/pla_boardUpload";
	}

	@RequestMapping(value = "/whatpla/writedo", method = RequestMethod.POST)
	public String plawritedo(Whatdo whatdo) {
		Whatdo whatdo1 = new Whatdo(whatdo.getTitle(), whatdo.getNick(), whatdo.getContent(), whatdo.getTime(),
				whatdo.getLoc());
		whatplaDao.insert(whatdo1);
		return "redirect:/whatpla/list";

	}

	@RequestMapping(value = "/whatpla/search", method = RequestMethod.POST)
	public String plasearch(@ModelAttribute("cmd") Whatdo whatdo, Errors erros, Model model,
			HttpServletRequest request) {
		if (erros.hasErrors()) {
			return "Whatdo/pla_boardList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Whatdo> whatdos = whatplaDao.search(search, sec);
		model.addAttribute("whatdos", whatdos);
		return "Whatdo/pla_boardList";
	}

	@RequestMapping(value = "/whatpla/view", method = RequestMethod.GET)
	public String plaview(@ModelAttribute("cmd") Whatdo whatdo, Model model, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = whatplaDao.updateHit(mid);
		if (updateCount != 0) {
			whatdo = whatplaDao.selectBoardByMid(mid);
			model.addAttribute("whatdos", whatdo);

		}
		return "Whatdo/pla_boardView";
	}

	@RequestMapping("/whatpla/update")
	public String plaupdate(Whatdo whatdo, Model model, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatdo = whatplaDao.selectBoardByMid(mid);
		model.addAttribute("whatdos", whatdo);
		return "Whatdo/pla_boardUpdate";
	}

	@RequestMapping(value = "/whatpla/updatedo", method = RequestMethod.POST)
	public String plaupdate(Whatdo whatdo, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatdo = whatplaDao.update(whatdo);
		return "redirect:/whatpla/view?mid=" + mid;

	}

	@RequestMapping("/whatpla/delete")
	public String pladelete(Whatdo whatdo, HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatplaDao.delete(mid);
		return "redirect:/whatpla/list";
	}
	
	
	// 의견보기
	@RequestMapping("/whatpla/view/comment")
	@ResponseBody
	public Object pla_board_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatplaDao.getCommentListpla_board(ref));
		return object;
	}
	//의견 쓰기
	@RequestMapping("/whatpla/view/comment_insert")
	@ResponseBody
	public Object pla_board_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		String writer = request.getParameter("writer");
         Calendar date = Calendar.getInstance();
         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
         String date1 = today.format(date.getTime());
		comment = new Comment(ref, writer, content, date1);
		whatplaDao.insertComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",whatplaDao.getCommentListpla_board(ref));
		return object;
	}
	//의견 삭제
	@RequestMapping("/whatpla/view/comment_delete")
	@ResponseBody
	public Object pla_board_comment_delete(@ModelAttribute("cmd") Whatdo whatdo, HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		whatplaDao.deleteComment(num);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatplaDao.getCommentListpla_board(ref));
		return object;
	}
	//의견 수정
	@RequestMapping("/whatpla/view/comment_update")
	@ResponseBody
	public Object pla_board_comment_update(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		String cmt = request.getParameter("cmt");
		Comment comment = new Comment(num, cmt);
		whatplaDao.updateComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatplaDao.getCommentListpla_board(ref));
		return object;
	}
	//좋아요
	@RequestMapping("/whatpla/view/like")
	@ResponseBody
	public Object pla_board_like(HttpServletRequest request, Whatdo whatdo, Model model){
		
		int mid = Integer.parseInt(request.getParameter("mid"));
		String nick = request.getParameter("user");
		
		FavVO fav = new FavVO(mid, nick);
		int success = whatplaDao.checkFav(fav);
		if(success == 0){
			whatplaDao.insertFav(fav);
			whatplaDao.updateFavpluse(mid);
		}
		if(success == 1){
			whatplaDao.deleteFav(fav);
			whatplaDao.updateFavcan(mid);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",whatplaDao.selectBoardByMid(mid));
		return object;
	}
}
