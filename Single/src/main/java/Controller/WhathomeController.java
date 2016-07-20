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
import Whathome.HomemakeDAO;
import Whathome.InteriorDAO;
import Whathome.Whathome;
import spring.FavVO;
import spring.PagingCount;


@Controller
public class WhathomeController {
	private HomemakeDAO homemakeDao;
	private InteriorDAO interiorDao;
	
	
	public void setHomemakeDao(HomemakeDAO homemakeDao){
		this.homemakeDao = homemakeDao;
	}
	public void setInteriorDao(InteriorDAO interiorDao){
		this.interiorDao = interiorDao;
	}
	
	
	//interior 占쌉쏙옙占쏙옙
	@RequestMapping("/interior/list")
	public String interiorlist(@ModelAttribute("cmd") Whathome whathome, Errors errors, Model model, HttpServletRequest request){
		if(errors.hasErrors()){
			return "Whathome/interior_boardList";
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
		int totrcds = interiorDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Whathome> whathomes = interiorDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("whathomes", whathomes);
		return "Whathome/interior_boardList";
	}
	
	@RequestMapping("/interior/write")
	public String interiorwrite(){
		
		return "Whathome/interior_boardUpload";
	}
	
	@RequestMapping(value="/interior/writedo", method=RequestMethod.POST)
	public String writedo(Whathome whathome){
		Whathome whathome1 = new Whathome(whathome.getTitle(), whathome.getNick(), whathome.getContent(), whathome.getTime());
		interiorDao.insert(whathome1);
				return "redirect:/interior/list";
		
	}
	
	@RequestMapping(value="/interior/search", method=RequestMethod.POST)
	public String search(@ModelAttribute("cmd") Whathome whathome, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Whathome/interior_boardList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Whathome> whathomes = interiorDao.search(search, sec);
		model.addAttribute("whathomes", whathomes);
		return "Whathome/interior_boardList";
	}


	
	@RequestMapping(value="/interior/view", method=RequestMethod.GET)
	public String view(@ModelAttribute("cmd") Whathome whathome, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = interiorDao.updateHit(mid);
		if(updateCount != 0){
			whathome = interiorDao.selectBoardByMid(mid);
			model.addAttribute("whathomes", whathome);
			
		}
		return "Whathome/interior_boardView";
	}
	
	@RequestMapping("/interior/update")
	public String update(Whathome whathome, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whathome = interiorDao.selectBoardByMid(mid);
		model.addAttribute("whathomes", whathome);
		return "Whathome/interior_boardUpdate";
	}
	
	@RequestMapping(value="/interior/updatedo", method=RequestMethod.POST)
	public String update(Whathome whathome, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whathome = interiorDao.update(whathome);
		return "redirect:/interior/view?mid="+mid;
		
	}
	
	@RequestMapping("/interior/delete")
	public String delete(Whathome whathome,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		interiorDao.delete(mid);
		return "redirect:/interior/list";
	}
	
	// 의견보기
			@RequestMapping("/interior/view/comment")
			@ResponseBody
			public Object interior_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
				Map<String, Object> object = new HashMap<String, Object>();
				int ref = Integer.parseInt(request.getParameter("ref"));
				object.put("list",interiorDao.getCommentListinterior_board(ref));
				return object;
			}
			//의견 쓰기
			@RequestMapping("/interior/view/comment_insert")
			@ResponseBody
			public Object interior_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
				String content = request.getParameter("content");
				int ref = Integer.parseInt(request.getParameter("ref"));
				String writer = request.getParameter("writer");
		         Calendar date = Calendar.getInstance();
		         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
		         String date1 = today.format(date.getTime());
				comment = new Comment(ref, writer, content, date1);
				interiorDao.insertComment(comment);
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("list",interiorDao.getCommentListinterior_board(ref));
				return object;
			}
			//의견 삭제
			@RequestMapping("/interior/view/comment_delete")
			@ResponseBody
			public Object interior_comment_delete(HttpServletRequest request){
				int num = Integer.parseInt(request.getParameter("num"));
				interiorDao.deleteComment(num);
				Map<String, Object> object = new HashMap<String, Object>();
				int ref = Integer.parseInt(request.getParameter("ref"));
				object.put("list",interiorDao.getCommentListinterior_board(ref));
				return object;
			}
			//의견 수정
			@RequestMapping("/interior/view/comment_update")
			@ResponseBody
			public Object interior_comment_update(HttpServletRequest request){
				int num = Integer.parseInt(request.getParameter("num"));
				String cmt = request.getParameter("cmt");
				Comment comment = new Comment(num, cmt);
				interiorDao.updateComment(comment);
				Map<String, Object> object = new HashMap<String, Object>();
				int ref = Integer.parseInt(request.getParameter("ref"));
				object.put("list",interiorDao.getCommentListinterior_board(ref));
				return object;
			}
			//좋아요
			@RequestMapping("/interior/view/like")
			@ResponseBody
			public Object interior_board_like(HttpServletRequest request, Model model){
				
				int mid = Integer.parseInt(request.getParameter("mid"));
				String nick = request.getParameter("user");
				FavVO fav = new FavVO(mid, nick);
				int success = interiorDao.checkFav(fav);
				if(success == 0){
					interiorDao.insertFav(fav);
					interiorDao.updateFavpluse(mid);
				}
				if(success == 1){
					interiorDao.deleteFav(fav);
					interiorDao.updateFavcan(mid);
				}
				Map<String, Object> object = new HashMap<String, Object>();
				object.put("list",interiorDao.selectBoardByMid(mid));
				return object;
			}
	
	
	//homemake 占쌉쏙옙占쏙옙
	@RequestMapping("/homemake/list")
	public String plalist(@ModelAttribute("cmd") Whathome whathome, Errors errors, Model model, HttpServletRequest request){
		if(errors.hasErrors()){
			return "Whathome/homemake_boardList";
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
		int totrcds = interiorDao.countRecords();
		// �뙆�씪誘명꽣 : 珥앸젅肄붾뱶媛쒖닔 , �븯�떒�쓽 �몴�떆�릺�뒗 �럹�씠吏�媛쒖닔, 媛� �럹�씠吏��뿉 �굹�삤�뒗 �젅肄붾뱶媛쒖닔
		PagingCount pgcnt = new PagingCount(totrcds, 5, rcdsinone);
		
		// �븯�떒�뿉 �럹�씠吏뺢린踰� �깭洹몃�� �뼸�뼱�궡�뒗 硫붿냼�뱶.
		String pagecounting = pgcnt.showPaging(pagelink, "list");
		// �솕硫댁뿉 寃뚯떆臾쇰뱾�쓣 肉뚮젮二쇨린 �쐞�븳 select荑쇰━臾�.
		int endpage = startRcdNo+rcdsinone;
		List<Whathome> whathomes = homemakeDao.selectAll(startRcdNo, endpage);
		request.setAttribute("pagecounting", pagecounting);
		request.setAttribute("Boardpage", "list");
		model.addAttribute("whathomes", whathomes);
		return "Whathome/homemake_boardList";
	}
	
	@RequestMapping("/homemake/write")
	public String plawrite(){
		
		return "Whathome/homemake_boardUpload";
	}
	
	@RequestMapping(value="/homemake/writedo", method=RequestMethod.POST)
	public String plawritedo(Whathome whathome){
		Whathome whathome1 = new Whathome(whathome.getTitle(), whathome.getNick(), whathome.getContent(), whathome.getTime());
		homemakeDao.insert(whathome1);
		return "redirect:/homemake/list";
		
	}
	
	@RequestMapping(value="/homemake/search", method=RequestMethod.POST)
	public String plasearch(@ModelAttribute("cmd") Whathome whathome, Errors erros, Model model, HttpServletRequest request){
		if(erros.hasErrors()){
			return "Whathome/homemake_boardList";
		}
		String search = request.getParameter("search");
		String sec = request.getParameter("sec");
		List<Whathome> whathomes = homemakeDao.search(search, sec);
		model.addAttribute("whathomes", whathomes);
		return "Whathome/homemake_boardList";
	}
			
	@RequestMapping(value="/homemake/view", method=RequestMethod.GET)
	public String plaview(@ModelAttribute("cmd") Whathome whathome, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		int updateCount = homemakeDao.updateHit(mid);
		if(updateCount != 0){
			whathome = homemakeDao.selectBoardByMid(mid);
			model.addAttribute("whathomes", whathome);
			
		}
		return "Whathome/homemake_boardView";
	}
	
	@RequestMapping("/homemake/update")
	public String plaupdate(Whathome whathome, Model model, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whathome = homemakeDao.selectBoardByMid(mid);
		model.addAttribute("whathomes", whathome);
		return "Whathome/homemake_boardUpdate";
	}
	
	@RequestMapping(value="/homemake/updatedo", method=RequestMethod.POST)
	public String plaupdate(Whathome whathome, HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		whathome = homemakeDao.update(whathome);
		return "redirect:/homemake/view?mid="+mid;
		
	}
	
	@RequestMapping("/homemake/delete")
	public String pladelete(Whathome whathome,HttpServletRequest request){
		int mid = Integer.parseInt(request.getParameter("mid"));
		homemakeDao.delete(mid);
		return "redirect:/homemake/list";
	}
	
	// 의견보기
				@RequestMapping("/homemake/view/comment")
				@ResponseBody
				public Object homemake_comment(@ModelAttribute("cmd")Comment cmd, Model model, HttpServletRequest request){
					Map<String, Object> object = new HashMap<String, Object>();
					int ref = Integer.parseInt(request.getParameter("ref"));
					object.put("list",homemakeDao.getCommentListhomemake_board(ref));
					return object;
				}
				//의견 쓰기
				@RequestMapping("/homemake/view/comment_insert")
				@ResponseBody
				public Object homemake_comment_insert(@ModelAttribute("cmd") Comment comment, HttpServletRequest request){
					String content = request.getParameter("content");
					int ref = Integer.parseInt(request.getParameter("ref"));
					String writer = request.getParameter("writer");
			         Calendar date = Calendar.getInstance();
			         SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
			         String date1 = today.format(date.getTime());
					comment = new Comment(ref, writer, content, date1);
					homemakeDao.insertComment(comment);
					Map<String, Object> object = new HashMap<String, Object>();
					object.put("list",homemakeDao.getCommentListhomemake_board(ref));
					return object;
				}
				//의견 삭제
				@RequestMapping("/homemake/view/comment_delete")
				@ResponseBody
				public Object homemake_comment_delete(HttpServletRequest request){
					int num = Integer.parseInt(request.getParameter("num"));
					homemakeDao.deleteComment(num);
					Map<String, Object> object = new HashMap<String, Object>();
					int ref = Integer.parseInt(request.getParameter("ref"));
					object.put("list",homemakeDao.getCommentListhomemake_board(ref));
					return object;
				}
				//의견 수정
				@RequestMapping("/homemake/view/comment_update")
				@ResponseBody
				public Object homemake_comment_update(HttpServletRequest request){
					int num = Integer.parseInt(request.getParameter("num"));
					String cmt = request.getParameter("cmt");
					Comment comment = new Comment(num, cmt);
					homemakeDao.updateComment(comment);
					Map<String, Object> object = new HashMap<String, Object>();
					int ref = Integer.parseInt(request.getParameter("ref"));
					object.put("list",homemakeDao.getCommentListhomemake_board(ref));
					return object;
				}
				//좋아요
				@RequestMapping("/homemake/view/like")
				@ResponseBody
				public Object homemake_board_like(HttpServletRequest request, Model model){
					
					int mid = Integer.parseInt(request.getParameter("mid"));
					String nick = request.getParameter("user");
					FavVO fav = new FavVO(mid, nick);
					int success = homemakeDao.checkFav(fav);
					if(success == 0){
						homemakeDao.insertFav(fav);
						homemakeDao.updateFavpluse(mid);
					}
					if(success == 1){
						homemakeDao.deleteFav(fav);
						homemakeDao.updateFavcan(mid);
					}
					Map<String, Object> object = new HashMap<String, Object>();
					object.put("list",homemakeDao.selectBoardByMid(mid));
					return object;
				}
}
