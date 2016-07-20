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
	
	//�ǰߺ���
		public ArrayList<Comment> getCommentListtodaybuy_board(int num){
			SqlSession session = factory.openSession();
			List<Comment> commentList = session.selectList("todaysell.getCommentListtodaybuy_board", num);
			session.close();
			return (ArrayList<Comment>) commentList;
		}
		
		//�ǰ߾���
		public int insertComment(Comment comment){
			SqlSession session = factory.openSession();
			int insert = session.insert("todaysell.CommentInserttodaybuy_board", comment);
			session.close();
			return insert;
		}
		//�ǰ� ����
		public int deleteComment(int num){
			SqlSession session = factory.openSession();
			int insert = session.delete("todaysell.CommentDeletetodaybuy_board", num);
			session.close();
			return insert;
		}
		//�ǰ� ����
		public Comment updateComment(Comment comment){
			SqlSession session = factory.openSession();
			int a = session.update("todaysell.CommentUpdatetodaybuy_board", comment);
			session.close();
			return comment;
		}
		//���ƿ� üũ
		public int checkFav(FavVO fav){
			SqlSession session = factory.openSession();
			int a = session.selectOne("todaysell.CheckFavtodaybuy_board", fav);
			session.close();
			return a;
		}
		//���ƿ� ����(+)
		public int updateFavpluse(int mid){
			SqlSession session = factory.openSession();
			int updatefav = session.update("todaysell.LikeTodaybuy", mid);
			session.close();
			return updatefav;
		}
		//���ƿ� ����(-)
		public int updateFavcan(int mid){
			SqlSession session = factory.openSession();
			int cancel = session.update("todaysell.CancelLikeTodaybuy", mid);
			session.close();
			return cancel;
		}
		//���ƿ� ���� �Է�
		public int insertFav(FavVO fav){
			SqlSession session = factory.openSession();
			int insert = session.insert("todaysell.insertFavtodaybuy_board", fav);
			session.close();
			return insert;
		}
		//���ƿ� ���
		public int deleteFav(FavVO fav){
			SqlSession session = factory.openSession();
			int delete = session.delete("todaysell.deleteFavtodaybuy_board", fav);
			session.close();
			return delete;
		}
}
