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
import Whathome.Hobby;
import Whathome.HobbyDAO;
import spring.FavVO;
import spring.PagingCount;

@Controller
public class HobbyController {
	
private HobbyDAO hobbyDao;
	
	
	public void setHobbyDao(HobbyDAO hobbyDao){
		this.hobbyDao = hobbyDao;
	}
	
	@RequestMapping("/hobby/list")
	public String list(@ModelAttribute("cmd") Hobby hobby, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Whathome/hobby_boardlist";
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
		int totrcds = hobbyDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;

		List<Hobby> hobbys = hobbyDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("hobbys", hobbys);
		return "Whathome/hobby_boardlist";

		
	}
	

	@RequestMapping("/hobby/write")
	public String write(){
		
		return "Whathome/hobby_boardWrite";
	}
	
	@RequestMapping(value="/hobby/writedo", method=RequestMethod.POST)
	public String writedo(Hobby hobby){
		Hobby newboard = new Hobby(hobby.getTitle(), hobby.getNick(), hobby.getContent(), hobby.getTime(), hobby.getSort());
		hobbyDao.insert(newboard);
				return "redirect:/hobby/list";
		
	}
	
	@RequestMapping("/hobby/search")
	public String search(@ModelAttribute("cmd") Hobby hobby, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Whathome/hobby_boardlist";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Hobby> hobbys = hobbyDao.search(search, sec);
		model.addAttribute("hobbys", hobbys);
		return "Whathome/hobby_boardlist";
	}


	
	@RequestMapping("/hobby/view")
	public String view(@ModelAttribute("cmd") Hobby hobby, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = hobbyDao.updateHit(mid);
		if(updateCount != 0){
			hobby = hobbyDao.selectBoardByMid(mid);
			model.addAttribute("hobbys", hobby);
			
		}
		return "Whathome/hobby_boardView";
	}
	
	@RequestMapping("/hobby/update")
	public String update(Hobby hobby, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		hobby = hobbyDao.selectBoardByMid(mid);
		model.addAttribute("hobbys", hobby);
		return "Whathome/hobby_boardUpdate";
	}
	
	@RequestMapping("/hobby/updatedo")
	public String update(Hobby hobby, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		Hobby hobby2 = hobbyDao.update(hobby);
		return "redirect:/hobby/view?mid="+mid;
		
	}
	
	@RequestMapping("/hobby/delete")
	public String delete(Hobby hobby,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		hobbyDao.delete(mid);
		return "redirect:/hobby/list";
	}
	// 의견보기
	@RequestMapping("/hobby/view/comment")
	@ResponseBody
	public Object hobby_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",hobbyDao.getCommentListhobby_board(ref));
		return object;
	}
	//의견 쓰기
	@RequestMapping("/hobby/view/comment_insert")
	@ResponseBody
	public Object hobby_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		String writer = request.getParameter("writer");
         Calendar date = Calendar.getInstance();
         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
         String date1 = today.format(date.getTime());
		comment = new Comment(ref, writer, content, date1);
		hobbyDao.insertComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",hobbyDao.getCommentListhobby_board(ref));
		return object;
	}
	//의견 삭제
	@RequestMapping("/hobby/view/comment_delete")
	@ResponseBody
	public Object hobby_comment_delete(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		hobbyDao.deleteComment(num);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",hobbyDao.getCommentListhobby_board(ref));
		return object;
	}
	//의견 수정
	@RequestMapping("/hobby/view/comment_update")
	@ResponseBody
	public Object hobby_comment_update(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		String cmt = request.getParameter("cmt");
		Comment comment = new Comment(num, cmt);
		hobbyDao.updateComment(comment);
		Map<String, Object> object = new HashMap<String, Object>();
		int ref = Integer.parseInt(request.getParameter("ref"));
		object.put("list",hobbyDao.getCommentListhobby_board(ref));
		return object;
	}
	//좋아요
	@RequestMapping("/hobby/view/like")
	@ResponseBody
	public Object hobby_board_like(HttpServletRequest request, Model model){
		
		int mid = Integer.parseInt(request.getParameter("mid"));
		String nick = request.getParameter("user");
		FavVO fav = new FavVO(mid, nick);
		int success = hobbyDao.checkFav(fav);
		if(success == 0){
			hobbyDao.insertFav(fav);
			hobbyDao.updateFavpluse(mid);
		}
		if(success == 1){
			hobbyDao.deleteFav(fav);
			hobbyDao.updateFavcan(mid);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list",hobbyDao.selectBoardByMid(mid));
		return object;
	}
}
