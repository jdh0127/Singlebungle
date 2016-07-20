package Whatsell;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;


public class WhatsellDAO {
	
	private SqlSessionFactory factory;
	
	public void setFactory(SqlSessionFactory factory){
		this.factory = factory;
	}
	
	public List<Whatsell> selectAll(int startRcdNo, int endpage){
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();
		
		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Whatsell> results = session.selectList("whatsell.selectsale_boardList", pageVo);
		session.close();
		return results;
	}
	
	public int countRecords(){
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("whatsell.selectsale_boardListCount");
		session.close();
		return listCount;
	}
	
	
	public int insert(Whatsell whatsell){
		SqlSession session = factory.openSession();
		int insert = session.insert("whatsell.insertsale_board", whatsell);
		session.close();
		return insert;
	}
	

	public List<Whatsell> search(String search, String sec){
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();
		
		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");
		
		List<Whatsell> results = session.selectList("whatsell.searchsale_board", searchVO);
		session.close();
		return results;
	}
	
	public Whatsell selectBoardByMid(int mid){
		SqlSession session = factory.openSession();
		Whatsell whatsell = session.selectOne("whatsell.selectsale_boardview", mid);
		session.close();
		return whatsell;
	}
	
	public int updateHit(int mid){
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("whatsell.updatesale_boardhit", mid);
		session.close();
		return updateReadCount;
	}
	
	public Whatsell update(Whatsell whatsell){
		SqlSession session = factory.openSession();
		session.update("whatsell.updatesale_board", whatsell);
		session.close();
		return whatsell;
	}
	
	public int delete(int mid){
		SqlSession session = factory.openSession();
		int deletecount = session.delete("whatsell.deletesale_board", mid);
		session.close();
		return deletecount;
	}
	//�ǰߺ���
		public ArrayList<Comment> getCommentListsale_board(int num){
			SqlSession session = factory.openSession();
			List<Comment> commentList = session.selectList("whatsell.getCommentListsale_board", num);
			session.close();
			return (ArrayList<Comment>) commentList;
		}
		
		//�ǰ߾���
		public int insertComment(Comment comment){
			SqlSession session = factory.openSession();
			int insert = session.insert("whatsell.CommentInsertsale_board", comment);
			session.close();
			return insert;
		}
		//�ǰ� ����
		public int deleteComment(int num){
			SqlSession session = factory.openSession();
			int insert = session.delete("whatsell.CommentDeletesale_board", num);
			session.close();
			return insert;
		}
		//�ǰ� ����
		public Comment updateComment(Comment comment){
			SqlSession session = factory.openSession();
			int a = session.update("whatsell.CommentUpdatesale_board", comment);
			session.close();
			return comment;
		}
		//���ƿ� üũ
		public int checkFav(FavVO fav){
			SqlSession session = factory.openSession();
			int a = session.selectOne("whatsell.CheckFavsale_board", fav);
			session.close();
			return a;
		}
		//���ƿ� ����(+)
		public int updateFavpluse(int mid){
			SqlSession session = factory.openSession();
			int updatefav = session.update("whatsell.Likesale_board", mid);
			session.close();
			return updatefav;
		}
		//���ƿ� ����(-)
		public int updateFavcan(int mid){
			SqlSession session = factory.openSession();
			int cancel = session.update("whatsell.CancelLikesale_board", mid);
			session.close();
			return cancel;
		}
		//���ƿ� ���� �Է�
		public int insertFav(FavVO fav){
			SqlSession session = factory.openSession();
			int insert = session.insert("whatsell.insertFavsale_board", fav);
			session.close();
			return insert;
		}
		//���ƿ� ���
		public int deleteFav(FavVO fav){
			SqlSession session = factory.openSession();
			int delete = session.delete("whatsell.deleteFavsale_board", fav);
			session.close();
			return delete;
		}
}

