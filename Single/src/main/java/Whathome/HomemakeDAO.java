package Whathome;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;


public class HomemakeDAO {

	private SqlSessionFactory factory;

	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public List<Whathome> selectAll(int startRcdNo, int endpage){
		SqlSession session = factory.openSession();
pageVO pageVo = new pageVO();
		
		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Whathome> results = session.selectList("whathome.selecthomemake_boardList", pageVo);
		session.close();
		return results;
	}
	
	public int countRecords(){
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("whathome.selecthomemake_boardListCount");
		session.close();
		return listCount;
	}
	
	
	public int insert(Whathome whathome){
		SqlSession session = factory.openSession();
		int insert = session.insert("whathome.inserthomemake_board", whathome);
		session.close();
		return insert;
	}
	
	public List<Whathome> search(String search, String sec){
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();
		
		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");
		
		List<Whathome> results = session.selectList("whathome.searchhomemake_board", searchVO);
		session.close();
		return results;
	}
	
	public Whathome selectBoardByMid(int mid){
		SqlSession session = factory.openSession();
		Whathome whathome = session.selectOne("whathome.selecthomemake_boardview", mid);
		session.close();
		return whathome;
	}
	
	public int updateHit(int mid){
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("whathome.updatehomemake_boardhit", mid);
		session.close();
		return updateReadCount;
	}
	
	public Whathome update(Whathome whathome){
		SqlSession session = factory.openSession();
		session.update("whathome.updatehomemake_board", whathome);
		session.close();
		return whathome;
	}
	
	public int delete(int mid){
		SqlSession session = factory.openSession();
		int deletecount = session.delete("whathome.deletehomemake_board", mid);
		session.close();
		return deletecount;
	}
	
	//�ǰߺ���
	public ArrayList<Comment> getCommentListhomemake_board(int num){
		SqlSession session = factory.openSession();
		List<Comment> commentList = session.selectList("whathome.getCommentListhomemake_board", num);
		session.close();
		return (ArrayList<Comment>) commentList;
	}
	
	//�ǰ߾���
	public int insertComment(Comment comment){
		SqlSession session = factory.openSession();
		int insert = session.insert("whathome.CommentInserthomemake_board", comment);
		session.close();
		return insert;
	}
	//�ǰ� ����
	public int deleteComment(int num){
		SqlSession session = factory.openSession();
		int insert = session.delete("whathome.CommentDeletehomemake_board", num);
		session.close();
		return insert;
	}
	//�ǰ� ����
	public Comment updateComment(Comment comment){
		SqlSession session = factory.openSession();
		int a = session.update("whathome.CommentUpdatehomemake_board", comment);
		session.close();
		return comment;
	}
	//���ƿ� üũ
	public int checkFav(FavVO fav){
		SqlSession session = factory.openSession();
		int a = session.selectOne("whathome.CheckFavhomemake_board", fav);
		session.close();
		return a;
	}
	//���ƿ� ����(+)
	public int updateFavpluse(int mid){
		SqlSession session = factory.openSession();
		int updatefav = session.update("whathome.Likehomemake_board", mid);
		session.close();
		return updatefav;
	}
	//���ƿ� ����(-)
	public int updateFavcan(int mid){
		SqlSession session = factory.openSession();
		int cancel = session.update("whathome.CancelLikehomemake_board", mid);
		session.close();
		return cancel;
	}
	//���ƿ� ���� �Է�
	public int insertFav(FavVO fav){
		SqlSession session = factory.openSession();
		int insert = session.insert("whathome.insertFavhomemake_board", fav);
		session.close();
		return insert;
	}
	//���ƿ� ���
	public int deleteFav(FavVO fav){
		SqlSession session = factory.openSession();
		int delete = session.delete("whathome.deleteFavhomemake_board", fav);
		session.close();
		return delete;
	}
}