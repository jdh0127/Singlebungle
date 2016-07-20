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

import Honey.Honey;
import Honey.HoneyDAO;
import Whatdo.Comment;
import spring.FavVO;
import spring.PagingCount;

@Controller
public class HoneyController {
	private HoneyDAO honeyDao;
	
	
	public void setHoneyDao(HoneyDAO honeyDao){
		this.honeyDao = honeyDao;
	}
	
	@RequestMapping("/honey/list")
	public String list(@ModelAttribute("cmd") Honey honey, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Honey/honey_boardlist";
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
		int totrcds = honeyDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Honey> honeys = honeyDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("honeys", honeys);
		return "Honey/honey_boardlist";
	}
	

	@RequestMapping("/honey/write")
	public String write(){
		
		return "Honey/honey_boardWrite";
	}
	
	@RequestMapping(value="/honey/writedo", method=RequestMethod.POST)
	public String writedo(Honey honey){
		Honey newboard = new Honey(honey.getTitle(), honey.getNick(), honey.getContent(), honey.getTime());
		int insert = honeyDao.insert(newboard);
		if(insert != 1){
				return "redirect:/honey/write";
		}
		return "redirect:/honey/list";
	}
	
	@RequestMapping("/honey/search")
	public String search(@ModelAttribute("cmd") Honey honey, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Honey/honey_boardlist";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");

		List<Honey> honeys = honeyDao.search(search, sec);
		model.addAttribute("honeys", honeys);
		return "Honey/honey_boardlist";
	}

	
	@RequestMapping("/honey/view")
	public String view(@ModelAttribute("cmd") Honey honey, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = honeyDao.updateHit(mid);
		if(updateCount != 0){
		honey = honeyDao.selectBoardByMid(mid);
		model.addAttribute("honeys", honey);
		}
		return "Honey/honey_boardView";
	}
	
	@RequestMapping("/honey/update")
	public String update(Honey honey, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		honey = honeyDao.selectBoardByMid(mid);
		model.addAttribute("honeys", honey);
		return "Honey/honey_boardUpdate";
	}
	
	@RequestMapping("/honey/updatedo")
	public String update(Honey honey, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		Honey honey2 = honeyDao.update(honey);
		return "redirect:/honey/view?mid="+mid;
		
	}
	
	@RequestMapping("/honey/delete")
	public String delete(Honey honey,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		honeyDao.delete(mid);
		return "redirect:/honey/list";
	}
	// 의견보기
			@RequestMapping("/honey/view/comment")
			@ResponseBody
			public Object sale_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
				Map<String, Object> object = new HashMap<String, Object>();
				int ref = Integer.parseInt(request.getParameter("ref"));
				object.put("list",honeyDao.getCommentListhoney_board(ref));
				return object;
			}
			//의견 쓰기
			@RequestMapping("/honey/view/comment_insert")
			@ResponseBody
			public Object sale_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
				String content = request.getParameter("content");
				int ref = Integer.parseInt(request.getParameter("ref"));
				String writer = request.getParameter("writer");
		         Calendar date = Calendar.getInstance();
		         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
		         String date1 = today.format(date.getTime());
				comment = new Comment(ref, writer, content, date1);
				honeyDao.insertComment(comment);
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("list",honeyDao.getCommentListhoney_board(ref));
				return object;
			}
			//의견 삭제
			@RequestMapping("/honey/view/comment_delete")
			@ResponseBody
			public Object sale_comment_delete(HttpServletRequest request){
				int num = Integer.parseInt(request.getParameter("num"));
				honeyDao.deleteComment(num);
				Map<String, Object> object = new HashMap<String, Object>();
				int ref = Integer.parseInt(request.getParameter("ref"));
				object.put("list",honeyDao.getCommentListhoney_board(ref));
				return object;
			}
			//의견 수정
			@RequestMapping("/honey/view/comment_update")
			@ResponseBody
			public Object sale_comment_update(HttpServletRequest request){
				int num = Integer.parseInt(request.getParameter("num"));
				String cmt = request.getParameter("cmt");
				Comment comment = new Comment(num, cmt);
				honeyDao.updateComment(comment);
				Map<String, Object> object = new HashMap<String, Object>();
				int ref = Integer.parseInt(request.getParameter("ref"));
				object.put("list",honeyDao.getCommentListhoney_board(ref));
				return object;
			}
			//좋아요
			@RequestMapping("/honey/view/like")
			@ResponseBody
			public Object sale_board_like(HttpServletRequest request, Model model){
				
				int mid = Integer.parseInt(request.getParameter("mid"));
				String nick = request.getParameter("user");
				FavVO fav = new FavVO(mid, nick);
				int success = honeyDao.checkFav(fav);
				if(success == 0){
					honeyDao.insertFav(fav);
					honeyDao.updateFavpluse(mid);
				}
				if(success == 1){
					honeyDao.deleteFav(fav);
					honeyDao.updateFavcan(mid);
				}
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("list",honeyDao.selectBoardByMid(mid));
				return object;
			}
}
