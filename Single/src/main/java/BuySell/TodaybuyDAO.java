package BuySell;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;

public class TodaybuyDAO {

	private SqlSessionFactory factory;

	
	
	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}
	
	public List<Todaysell> selectAll(int startRcdNo, int endpage){
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();
		
		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Todaysell> results = session.selectList("todaysell.selecttodaybuyList", pageVo);
		session.close();
		return results;
	}
	
	public int countRecords(){
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("todaysell.selecttodaybuyListCount");
		session.close();
		return listCount;
	}
	
	
	public int insert(Todaysell todaysell){
		SqlSession session = factory.openSession();
		int insert = session.insert("todaysell.inserttodaybuy", todaysell);
		session.close();
		return insert;
	}
	
	public List<Todaysell> search(String search, String sec){
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();
		
		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");
		
		List<Todaysell> results = session.selectList("todaysell.searchtodaybuy", searchVO);
		session.close();
		return results;
	}
	
	
	public Todaysell selectBoardByMid(int mid){
		SqlSession session = factory.openSession();
		Todaysell todaysell = session.selectOne("todaysell.selecttodaybuyview", mid);
		session.close();
		return todaysell;
	}
	
	public int updateHit(int mid){
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("todaysell.updatetodaybuyhit", mid);
		session.close();
		return updateReadCount;
	}
	
	public Todaysell update(Todaysell todaysell){
		SqlSession session = factory.openSession();
		session.update("todaysell.updatetodaybuy", todaysell);
		session.close();
		return todaysell;
	}
	
	public int delete(int mid){
		SqlSession session = factory.openSession();
		int deletecount = session.delete("todaysell.deletetodaybuy", mid);
		session.close();
		return deletecount;
	}
	
	//의견보기
		public ArrayList<Comment> getCommentListtodaybuy_board(int num){
			SqlSession session = factory.openSession();
			List<Comment> commentList = session.selectList("todaysell.getCommentListtodaybuy_board", num);
			session.close();
			return (ArrayList<Comment>) commentList;
		}
		
		//의견쓰기
		public int insertComment(Comment comment){
			SqlSession session = factory.openSession();
			int insert = session.insert("todaysell.CommentInserttodaybuy_board", comment);
			session.close();
			return insert;
		}
		//의견 삭제
		public int deleteComment(int num){
			SqlSession session = factory.openSession();
			int insert = session.delete("todaysell.CommentDeletetodaybuy_board", num);
			session.close();
			return insert;
		}
		//의견 수정
		public Comment updateComment(Comment comment){
			SqlSession session = factory.openSession();
			int a = session.update("todaysell.CommentUpdatetodaybuy_board", comment);
			session.close();
			return comment;
		}
		//좋아요 체크
		public int checkFav(FavVO fav){
			SqlSession session = factory.openSession();
			int a = session.selectOne("todaysell.CheckFavtodaybuy_board", fav);
			session.close();
			return a;
		}
		//좋아요 업뎃(+)
		public int updateFavpluse(int mid){
			SqlSession session = factory.openSession();
			int updatefav = session.update("todaysell.LikeTodaybuy", mid);
			session.close();
			return updatefav;
		}
		//좋아요 업뎃(-)
		public int updateFavcan(int mid){
			SqlSession session = factory.openSession();
			int cancel = session.update("todaysell.CancelLikeTodaybuy", mid);
			session.close();
			return cancel;
		}
		//좋아요 정보 입력
		public int insertFav(FavVO fav){
			SqlSession session = factory.openSession();
			int insert = session.insert("todaysell.insertFavtodaybuy_board", fav);
			session.close();
			return insert;
		}
		//좋아요 취소
		public int deleteFav(FavVO fav){
			SqlSession session = factory.openSession();
			int delete = session.delete("todaysell.deleteFavtodaybuy_board", fav);
			session.close();
			return delete;
		}
}
