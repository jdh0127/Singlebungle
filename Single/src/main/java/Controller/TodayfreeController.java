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

import BuySell.TodaybuyDAO;
import BuySell.TodayfreeDAO;
import BuySell.Todaysell;
import BuySell.TodaysellDAO;
import Whatdo.Comment;
import spring.FavVO;
import spring.PagingCount;

@Controller
public class TodayfreeController {

private TodayfreeDAO TodayfreeDao;
	
	public void setTodayfreeDao(TodayfreeDAO TodayfreeDao){
		this.TodayfreeDao = TodayfreeDao;
	}
	
	@RequestMapping("/todayfree/list")
	public String todayfreelist(@ModelAttribute("cmd") Todaysell todaysell, Errors errors, Model model, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return "Todayfree/todayfreeList";
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
		int totrcds = TodayfreeDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Todaysell> todayfrees = TodayfreeDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("todayfree", todayfrees);
		return "Todayfree/todayfreeList";
	}
	
	
	@RequestMapping("/todayfree/write")
	public String write(){
		
		return "Todayfree/todayfreeUpload";
	}
	
	@RequestMapping(value="/todayfree/writedo", method=RequestMethod.POST)
	public String todayfreewritedo(Todaysell todaysell){
		Todaysell Todaysell1 = new Todaysell(todaysell.getTitle(), todaysell.getNick(), todaysell.getContent(), todaysell.getTime(), todaysell.getLoc());
		TodayfreeDao.insert(Todaysell1);
				return "redirect:/todayfree/list";
		
	}
	
	@RequestMapping(value="/todayfree/search", method=RequestMethod.POST)
	public String search(@ModelAttribute("cmd") Todaysell Todaysell, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Todayfree/todayfreeList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Todaysell> Todaysells = TodayfreeDao.search(search, sec);
		model.addAttribute("todayfree", Todaysells);
		return "Todayfree/todayfreeList";
	}


	
	@RequestMapping("/todayfree/view")
	public String todayfreeview(@ModelAttribute("cmd") Todaysell todaysell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = TodayfreeDao.updateHit(mid);
		if(updateCount != 0){
			todaysell = TodayfreeDao.selectBoardByMid(mid);
			model.addAttribute("Todayfrees", todaysell);
			
		}
		return "Todayfree/todayfreeView";
	}
	
	@RequestMapping("/todayfree/update")
	public String todayfreeupdate(Todaysell todaysell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		todaysell = TodayfreeDao.selectBoardByMid(mid);
		model.addAttribute("Todayfrees", todaysell);
		return "Todayfree/todayfreeUpdate";
	}
	
	@RequestMapping(value="/todayfree/updatedo", method=RequestMethod.POST)
	public String todayfreeupdate(Todaysell todaysell, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		todaysell = TodayfreeDao.update(todaysell);
		return "redirect:/todayfree/view?mid="+mid;
		
	}
	
	@RequestMapping("/todayfree/delete")
	public String todayfreedelete(Todaysell todaysell,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		TodayfreeDao.delete(mid);
		return "redirect:/todayfree/list";
	}
	
	// 의견보기
	@RequestMapping("/todayfree/view/comment")
	@ResponseBody
	public Object todayfreecomment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",TodayfreeDao.getCommentListtodayfree_board(ref));
		return object;
	}
	//의견 쓰기
	@RequestMapping("/todayfree/view/comment_insert")
	@ResponseBody
	public Object todayfreecomment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		String writer = request.getParameter("writer");
         Calendar date = Calendar.getInstance();
         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
         String date1 = today.format(date.getTime());
		comment = new Comment(ref, writer, content, date1);
		TodayfreeDao.insertComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",TodayfreeDao.getCommentListtodayfree_board(ref));
		return object;
	}
	//의견 삭제
	@RequestMapping("/todayfree/view/comment_delete")
	@ResponseBody
	public Object todayfreecomment_delete(@ModelAttribute("cmd") Todaysell Todaysell, HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		TodayfreeDao.deleteComment(num);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",TodayfreeDao.getCommentListtodayfree_board(ref));
		return object;
	}
	//의견 수정
	@RequestMapping("/todayfree/view/comment_update")
	@ResponseBody
	public Object todayfreecomment_update(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		String cmt = request.getParameter("cmt");
		Comment comment = new Comment(num, cmt);
		TodayfreeDao.updateComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",TodayfreeDao.getCommentListtodayfree_board(ref));
		return object;
	}
	//좋아요
	@RequestMapping("/todayfree/view/like")
	@ResponseBody
	public Object todayfreeboard_like(HttpServletRequest request, Todaysell todaysell, Model model){
		
		int mid = Integer.parseInt(request.getParameter("mid"));
		String nick = request.getParameter("user");
		FavVO fav = new FavVO(mid, nick);
		int success = TodayfreeDao.checkFav(fav);
		if(success == 0){
			TodayfreeDao.insertFav(fav);
			TodayfreeDao.updateFavpluse(mid);
		}
		if(success == 1){
			TodayfreeDao.deleteFav(fav);
			TodayfreeDao.updateFavcan(mid);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",TodayfreeDao.selectBoardByMid(mid));
		return object;
	}
	
	//占쏙옙求占� 占쏙옙트占싼뤄옙
	
	private TodaybuyDAO TodaybuyDao;
	
	public void setTodaybuyDao(TodaybuyDAO TodaybuyDao){
		this.TodaybuyDao = TodaybuyDao;
	}
	
	@RequestMapping("/todaybuy/list")
	public String todaybuylist(@ModelAttribute("cmd") Todaysell todaysell, Errors errors, Model model, HttpServletRequest request){
		if(errors.hasErrors()){
			return "Todayfree/todaybuyList";
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
		int totrcds = TodaybuyDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Todaysell> Todaybuys = TodaybuyDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("Todaybuys", Todaybuys);
		return "Todaybuy/todaybuyList";
	}
	
	@RequestMapping("/todaybuy/write")
	public String write2(){
		
		return "Todaybuy/todaybuyUpload";
	}
	
	@RequestMapping(value="/todaybuy/writedo", method=RequestMethod.POST)
	public String todaybuywritedo(Todaysell todaysell){
		Todaysell todaysell1 = new Todaysell(todaysell.getTitle(), todaysell.getNick(), todaysell.getContent(), todaysell.getTime(), todaysell.getLoc());
		TodaybuyDao.insert(todaysell1);
				return "redirect:/todaybuy/list";
		
	}
	
	@RequestMapping(value="/todaybuy/search", method=RequestMethod.POST)
	public String buysearch(@ModelAttribute("cmd") Todaysell Todaysell, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Todaybuy/todaybuyList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Todaysell> Todaybuys = TodaybuyDao.search(search, sec);
		model.addAttribute("Todaybuys", Todaybuys);
		return "Todaybuy/todaybuyList";
	}


	
	@RequestMapping("/todaybuy/view")
	public String todaybuyview(@ModelAttribute("cmd") Todaysell todaysell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = TodaybuyDao.updateHit(mid);
		if(updateCount !=0){
			todaysell = TodaybuyDao.selectBoardByMid(mid);
			model.addAttribute("Todaybuys", todaysell);
			
		}
		return "Todaybuy/todaybuyView";
	}
	
	@RequestMapping("/todaybuy/update")
	public String todaybuyupdate(Todaysell todaysell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		todaysell = TodaybuyDao.selectBoardByMid(mid);
		model.addAttribute("Todaybuys", todaysell);
		return "Todaybuy/todaybuyUpdate";
	}
	
	@RequestMapping(value="/todaybuy/updatedo", method=RequestMethod.POST)
	public String todaybuyupdate(Todaysell todaysell, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		todaysell = TodaybuyDao.update(todaysell);
		return "redirect:/todaybuy/view?mid="+mid;
		
	}
	
	@RequestMapping("/todaybuy/delete")
	public String todaybuydelete(Todaysell todaysell,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		TodaybuyDao.delete(mid);
		return "redirect:/todaybuy/list";
	}
	// 의견보기
		@RequestMapping("/todaybuy/view/comment")
		@ResponseBody
		public Object todaybuycomment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
			Map<String, Object> object = new HashMap<String, Object>();
			int ref = Integer.parseInt(request.getParameter("ref"));
			object.put("list",TodaybuyDao.getCommentListtodaybuy_board(ref));
			return object;
		}
		//의견 쓰기
		@RequestMapping("/todaybuy/view/comment_insert")
		@ResponseBody
		public Object todaybuycomment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
			String content = request.getParameter("content");
			int ref = Integer.parseInt(request.getParameter("ref"));
			String writer = request.getParameter("writer");
	         Calendar date = Calendar.getInstance();
	         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
	         String date1 = today.format(date.getTime());
			comment = new Comment(ref, writer, content, date1);
			TodaybuyDao.insertComment(comment);
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("list",TodaybuyDao.getCommentListtodaybuy_board(ref));
			return object;
		}
		//의견 삭제
		@RequestMapping("/todaybuy/view/comment_delete")
		@ResponseBody
		public Object todaybuycomment_delete(@ModelAttribute("cmd") Todaysell Todaysell, HttpServletRequest request){
			int num = Integer.parseInt(request.getParameter("num"));
			TodaybuyDao.deleteComment(num);
			Map<String, Object> object = new HashMap<String, Object>();
			int ref = Integer.parseInt(request.getParameter("ref"));
			object.put("list",TodaybuyDao.getCommentListtodaybuy_board(ref));
			return object;
		}
		//의견 수정
		@RequestMapping("/todaybuy/view/comment_update")
		@ResponseBody
		public Object todaybuycomment_update(HttpServletRequest request){
			int num = Integer.parseInt(request.getParameter("num"));
			String cmt = request.getParameter("cmt");
			Comment comment = new Comment(num, cmt);
			TodaybuyDao.updateComment(comment);
			Map<String, Object> object = new HashMap<String, Object>();
			int ref = Integer.parseInt(request.getParameter("ref"));
			object.put("list",TodaybuyDao.getCommentListtodaybuy_board(ref));
			return object;
		}
		//좋아요
		@RequestMapping("/todaybuy/view/like")
		@ResponseBody
		public Object todaybuyboard_like(HttpServletRequest request, Todaysell todaysell, Model model){
			
			int mid = Integer.parseInt(request.getParameter("mid"));
			String nick = request.getParameter("user");
			FavVO fav = new FavVO(mid, nick);
			int success = TodaybuyDao.checkFav(fav);
			if(success == 0){
				TodaybuyDao.insertFav(fav);
				TodaybuyDao.updateFavpluse(mid);
			}
			if(success == 1){
				TodaybuyDao.deleteFav(fav);
				TodaybuyDao.updateFavcan(mid);
			}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("list",TodaybuyDao.selectBoardByMid(mid));
			return object;
		}
		
	
	
	
	//占싯니댐옙 占쏙옙트占싼뤄옙
	
private TodaysellDAO TodaysellDao;
	
	public void setTodaysellDao(TodaysellDAO TodaysellDao){
		this.TodaysellDao = TodaysellDao;
	}
	
	@RequestMapping("/todaysell/list")
	public String list(@ModelAttribute("cmd") Todaysell todaysell, Errors errors, Model model, HttpServletRequest request){
		if(errors.hasErrors()){
			return "Todaysell/todaysellList";
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
		int totrcds = TodaysellDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Todaysell> Todaysells = TodaysellDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("Todaysells", Todaysells);
		return "Todaysell/todaysellList";
	}
	
	@RequestMapping("/todaysell/write")
	public String write3(){
		
		return "Todaysell/todaysellUpload";
	}
	
	@RequestMapping(value="/todaysell/writedo", method=RequestMethod.POST)
	public String writedo(Todaysell Todaysell){
		Todaysell Todaysell1 = new Todaysell(Todaysell.getTitle(), Todaysell.getNick(), Todaysell.getContent(), Todaysell.getTime(), Todaysell.getLoc());
		TodaysellDao.insert(Todaysell1);
				return "redirect:/todaysell/list";
		
	}
	
	@RequestMapping(value="/todaysell/search", method=RequestMethod.POST)
	public String sellsearch(@ModelAttribute("cmd") Todaysell Todaysell, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Todaysell/todaysellList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Todaysell> Todaysells = TodaysellDao.search(search, sec);
		model.addAttribute("Todaysells", Todaysells);
		return "Todaysell/todaysellList";
	}


	
	@RequestMapping("/todaysell/view")
	public String view(@ModelAttribute("cmd") Todaysell todaysell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = TodaysellDao.updateHit(mid);
		if(updateCount !=0){
			todaysell = TodaysellDao.selectBoardByMid(mid);
			model.addAttribute("Todaysells", todaysell);
			
		}
		return "Todaysell/todaysellView";
	}
	
	@RequestMapping("/todaysell/update")
	public String update(Todaysell todaysell, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		todaysell = TodaysellDao.selectBoardByMid(mid);
		model.addAttribute("Todaysells", todaysell);
		return "Todaysell/todaysellUpdate";
	}
	
	@RequestMapping(value="/todaysell/updatedo", method=RequestMethod.POST)
	public String update(Todaysell todaysell, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		todaysell = TodaysellDao.update(todaysell);
		return "redirect:/todaysell/view?mid="+mid;
		
	}
	
	@RequestMapping("/todaysell/delete")
	public String delete(Todaysell todaysell,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		TodaysellDao.delete(mid);
		return "redirect:/todaysell/list";
	}
	
	// 의견보기
	@RequestMapping("/todaysell/view/comment")
	@ResponseBody
	public Object comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",TodaysellDao.getCommentListtodaysell_board(ref));
		return object;
	}
	//의견 쓰기
	@RequestMapping("/todaysell/view/comment_insert")
	@ResponseBody
	public Object comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		String writer = request.getParameter("writer");
         Calendar date = Calendar.getInstance();
         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
         String date1 = today.format(date.getTime());
		comment = new Comment(ref, writer, content, date1);
		TodaysellDao.insertComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",TodaysellDao.getCommentListtodaysell_board(ref));
		return object;
	}
	//의견 삭제
	@RequestMapping("/todaysell/view/comment_delete")
	@ResponseBody
	public Object comment_delete(@ModelAttribute("cmd") Todaysell Todaysell, HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		TodaysellDao.deleteComment(num);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",TodaysellDao.getCommentListtodaysell_board(ref));
		return object;
	}
	//의견 수정
	@RequestMapping("/todaysell/view/comment_update")
	@ResponseBody
	public Object comment_update(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		String cmt = request.getParameter("cmt");
		System.out.println(cmt);
		Comment comment = new Comment(num, cmt);
		TodaysellDao.updateComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",TodaysellDao.getCommentListtodaysell_board(ref));
		return object;
	}
	//좋아요
	@RequestMapping("/todaysell/view/like")
	@ResponseBody
	public Object board_like(HttpServletRequest request, Todaysell todaysell, Model model){
		
		int mid = Integer.parseInt(request.getParameter("mid"));
		System.out.println(mid);
		String nick = request.getParameter("user");
		
		FavVO fav = new FavVO(mid, nick);
		int success = TodaysellDao.checkFav(fav);
		if(success == 0){
			TodaysellDao.insertFav(fav);
			TodaysellDao.updateFavpluse(mid);
		}
		if(success == 1){
			TodaysellDao.deleteFav(fav);
			TodaysellDao.updateFavcan(mid);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",TodaysellDao.selectBoardByMid(mid));
		return object;
	}

}
