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
import Whatsell.Whatsell;
import Whatsell.WhatsellDAO;
import Whatsell.WhatshareDAO;
import spring.FavVO;
import spring.PagingCount;

@Controller
public class WhatsellController {
	private WhatsellDAO whatsellDao;
	private WhatshareDAO whatshareDao;
	
	public void setWhatsellDao(WhatsellDAO whatsellDao){
		this.whatsellDao = whatsellDao;
	}
	public void setWhatshareDao(WhatshareDAO whatshareDao){
		this.whatshareDao = whatshareDao;
	}
	
	//whatsell
	@RequestMapping("/whatsale/list")
	public String list(@ModelAttribute("cmd") Whatsell whatsell, Errors errors, Model model, HttpServletRequest request){
		if(errors.hasErrors()){
			return "Whatsell/sale_boardList";
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
		int totrcds = whatsellDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;

		List<Whatsell> whatsells = whatsellDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("whatsells", whatsells);
		return "Whatsell/sale_boardList";
	}
	
	@RequestMapping("/whatsale/write")
	public String write(){
		
		return "Whatsell/sale_boardUpload";
	}
	
	@RequestMapping(value="/whatsale/writedo", method=RequestMethod.POST)
	public String writedo(Whatsell whatsell){
		Whatsell whatsell1 = new Whatsell(whatsell.getTitle(), whatsell.getNick(), whatsell.getContent(), whatsell.getTime(), whatsell.getLoc());
		whatsellDao.insert(whatsell1);
				return "redirect:/whatsale/list";
		
	}
	
	@RequestMapping(value="/whatsale/search", method=RequestMethod.POST)
	public String search(@ModelAttribute("cmd") Whatsell whatsell, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Whatsell/sale_boardList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Whatsell> whatsells = whatsellDao.search(search, sec);
		model.addAttribute("whatsells", whatsells);
		return "Whatsell/sale_boardList";
	}


	
	@RequestMapping(value="/whatsale/view", method=RequestMethod.GET)
	public String view(@ModelAttribute("cmd") Whatsell whatsell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = whatsellDao.updateHit(mid);
		if(updateCount != 0){
			whatsell = whatsellDao.selectBoardByMid(mid);
			model.addAttribute("whatsells", whatsell);
			
		}
		return "Whatsell/sale_boardView";
	}
	
	@RequestMapping("/whatsale/update")
	public String update(Whatsell whatsell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatsell = whatsellDao.selectBoardByMid(mid);
		model.addAttribute("whatsells", whatsell);
		return "Whatsell/sale_boardUpdate";
	}
	
	@RequestMapping(value="/whatsale/updatedo", method=RequestMethod.POST)
	public String update(Whatsell whatsell, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatsell = whatsellDao.update(whatsell);
		return "redirect:/whatsale/view?mid="+mid;
		
	}
	
	@RequestMapping("/whatsale/delete")
	public String delete(Whatsell whatsell,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatsellDao.delete(mid);
		return "redirect:/whatsale/list";
	}
	
	// 의견보기
		@RequestMapping("/whatsale/view/comment")
		@ResponseBody
		public Object sale_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
			Map<String, Object> object = new HashMap<String, Object>();
			int ref = Integer.parseInt(request.getParameter("ref"));
			object.put("list",whatsellDao.getCommentListsale_board(ref));
			return object;
		}
		//의견 쓰기
		@RequestMapping("/whatsale/view/comment_insert")
		@ResponseBody
		public Object sale_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
			String content = request.getParameter("content");
			int ref = Integer.parseInt(request.getParameter("ref"));
			String writer = request.getParameter("writer");
	         Calendar date = Calendar.getInstance();
	         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
	         String date1 = today.format(date.getTime());
			comment = new Comment(ref, writer, content, date1);
			whatsellDao.insertComment(comment);
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("list",whatsellDao.getCommentListsale_board(ref));
			return object;
		}
		//의견 삭제
		@RequestMapping("/whatsale/view/comment_delete")
		@ResponseBody
		public Object sale_comment_delete(HttpServletRequest request){
			int num = Integer.parseInt(request.getParameter("num"));
			whatsellDao.deleteComment(num);
			Map<String, Object> object = new HashMap<String, Object>();
			int ref = Integer.parseInt(request.getParameter("ref"));
			object.put("list",whatsellDao.getCommentListsale_board(ref));
			return object;
		}
		//의견 수정
		@RequestMapping("/whatsale/view/comment_update")
		@ResponseBody
		public Object sale_comment_update(HttpServletRequest request){
			int num = Integer.parseInt(request.getParameter("num"));
			String cmt = request.getParameter("cmt");
			Comment comment = new Comment(num, cmt);
			whatsellDao.updateComment(comment);
			Map<String, Object> object = new HashMap<String, Object>();
			int ref = Integer.parseInt(request.getParameter("ref"));
			object.put("list",whatsellDao.getCommentListsale_board(ref));
			return object;
		}
		//좋아요
		@RequestMapping("/whatsale/view/like")
		@ResponseBody
		public Object sale_board_like(HttpServletRequest request, Model model){
			
			int mid = Integer.parseInt(request.getParameter("mid"));
			String nick = request.getParameter("user");
			FavVO fav = new FavVO(mid, nick);
			int success = whatsellDao.checkFav(fav);
			if(success == 0){
				whatsellDao.insertFav(fav);
				whatsellDao.updateFavpluse(mid);
			}
			if(success == 1){
				whatsellDao.deleteFav(fav);
				whatsellDao.updateFavcan(mid);
			}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("list",whatsellDao.selectBoardByMid(mid));
			return object;
		}
	
	//占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌉쏙옙占쏙옙
	@RequestMapping("/whatshare/list")
	public String plalist(@ModelAttribute("cmd") Whatsell whatsell, Errors errors, Model model, HttpServletRequest request){
		if(errors.hasErrors()){
			return "Whatsell/saleshare_boardList";
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
		int totrcds = whatshareDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Whatsell> whatsells = whatshareDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("whatsells", whatsells);
		return "Whatsell/saleshare_boardList";
	}
	
	@RequestMapping("/whatshare/write")
	public String plawrite(){
		
		return "Whatsell/saleshare_boardUpload";
	}
	
	@RequestMapping(value="/whatshare/writedo", method=RequestMethod.POST)
	public String plawritedo(Whatsell whatsell){
		Whatsell whatsell1 = new Whatsell(whatsell.getTitle(), whatsell.getNick(), whatsell.getContent(), whatsell.getTime(), whatsell.getLoc());
		whatshareDao.insert(whatsell1);
		return "redirect:/whatshare/list";
		
	}
	
	@RequestMapping(value="/whatshare/search", method=RequestMethod.POST)
	public String sharesearch(@ModelAttribute("cmd") Whatsell whatsell, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Whatsell/saleshare_boardList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Whatsell> whatsells = whatshareDao.search(search, sec);
		model.addAttribute("whatsells", whatsells);
		return "Whatsell/saleshare_boardList";
	}

			
	@RequestMapping(value="/whatshare/view", method=RequestMethod.GET)
	public String plaview(@ModelAttribute("cmd") Whatsell whatsell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = whatshareDao.updateHit(mid);
		if(updateCount != 0){
			whatsell = whatshareDao.selectBoardByMid(mid);
			model.addAttribute("whatsells", whatsell);
		}
		return "Whatsell/saleshare_boardView";
	}
	
	@RequestMapping("/whatshare/update")
	public String plaupdate(Whatsell whatsell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatsell = whatshareDao.selectBoardByMid(mid);
		model.addAttribute("whatsells", whatsell);
		return "Whatsell/saleshare_boardUpdate";
	}
	
	@RequestMapping(value="/whatshare/updatedo", method=RequestMethod.POST)
	public String plaupdate(Whatsell whatsell, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatsell = whatshareDao.update(whatsell);
		return "redirect:/whatshare/view?mid="+mid;
		
	}
	
	@RequestMapping("/whatshare/delete")
	public String pladelete(Whatsell whatsell,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whatshareDao.delete(mid);
		return "redirect:/whatshare/list";
	}
	
	// 의견보기
	@RequestMapping("/whatshare/view/comment")
	@ResponseBody
	public Object share_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatshareDao.getCommentListshare_board(ref));
		return object;
	}
	//의견 쓰기
	@RequestMapping("/whatshare/view/comment_insert")
	@ResponseBody
	public Object share_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		String writer = request.getParameter("writer");
         Calendar date = Calendar.getInstance();
         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
         String date1 = today.format(date.getTime());
		comment = new Comment(ref, writer, content, date1);
		whatshareDao.insertComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",whatshareDao.getCommentListshare_board(ref));
		return object;
	}
	//의견 삭제
	@RequestMapping("/whatshare/view/comment_delete")
	@ResponseBody
	public Object share_comment_delete(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		whatshareDao.deleteComment(num);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatshareDao.getCommentListshare_board(ref));
		return object;
	}
	//의견 수정
	@RequestMapping("/whatshare/view/comment_update")
	@ResponseBody
	public Object share_comment_update(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		String cmt = request.getParameter("cmt");
		Comment comment = new Comment(num, cmt);
		whatshareDao.updateComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",whatshareDao.getCommentListshare_board(ref));
		return object;
	}
	//좋아요
	@RequestMapping("/whatshare/view/like")
	@ResponseBody
	public Object share_board_like(HttpServletRequest request, Model model){
		
		int mid = Integer.parseInt(request.getParameter("mid"));
		String nick = request.getParameter("user");
		FavVO fav = new FavVO(mid, nick);
		int success = whatshareDao.checkFav(fav);
		if(success == 0){
			whatshareDao.insertFav(fav);
			whatshareDao.updateFavpluse(mid);
		}
		if(success == 1){
			whatshareDao.deleteFav(fav);
			whatshareDao.updateFavcan(mid);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",whatshareDao.selectBoardByMid(mid));
		return object;
	}
}
