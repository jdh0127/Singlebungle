package Whathome;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;

public class InteriorDAO {
	
	private SqlSessionFactory factory;

	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public List<Whathome> selectAll(int startRcdNo, int endpage){
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();
		
		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Whathome> results = session.selectList("whathome.selectinterior_boardList", pageVo);
		session.close();
		return results;
	}
	
	public int countRecords(){
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("whathome.selectinterior_boardListCount");
		session.close();
		return listCount;
	}
	
	
	public int insert(Whathome whathome){
		SqlSession session = factory.openSession();
		int insert = session.insert("whathome.insertinterior_board", whathome);
		session.close();
		return insert;
	}
	
	public List<Whathome> search(String search, String sec){
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();
		
		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");
		
		List<Whathome> results = session.selectList("whathome.searchinterior_board", searchVO);
		session.close();
		return results;
	}
	public Whathome selectBoardByMid(int mid){
		SqlSession session = factory.openSession();
		Whathome whathome = session.selectOne("whathome.selectinterior_boardview", mid);
		session.close();
		return whathome;
	}
	
	public int updateHit(int mid){
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("whathome.updateinterior_boardhit", mid);
		session.close();
		return updateReadCount;
	}
	
	public Whathome update(Whathome whathome){
		SqlSession session = factory.openSession();
		session.update("whathome.updateinterior_board", whathome);
		session.close();
		return whathome;
	}
	
	public int delete(int mid){
		SqlSession session = factory.openSession();
		int deletecount = session.delete("whathome.deleteinterior_board", mid);
		session.close();
		return deletecount;
	}
	
	//의견보기
	public ArrayList<Comment> getCommentListinterior_board(int num){
		SqlSession session = factory.openSession();
		List<Comment> commentList = session.selectList("whathome.getCommentListinterior_board", num);
		session.close();
		return (ArrayList<Comment>) commentList;
	}
	
	//의견쓰기
	public int insertComment(Comment comment){
		SqlSession session = factory.openSession();
		int insert = session.insert("whathome.CommentInsertinterior_board", comment);
		session.close();
		return insert;
	}
	//의견 삭제
	public int deleteComment(int num){
		SqlSession session = factory.openSession();
		int insert = session.delete("whathome.CommentDeleteinterior_board", num);
		session.close();
		return insert;
	}
	//의견 수정
	public Comment updateComment(Comment comment){
		SqlSession session = factory.openSession();
		int a = session.update("whathome.CommentUpdateinterior_board", comment);
		session.close();
		return comment;
	}
	//좋아요 체크
	public int checkFav(FavVO fav){
		SqlSession session = factory.openSession();
		int a = session.selectOne("whathome.CheckFavinterior_board", fav);
		session.close();
		return a;
	}
	//좋아요 업뎃(+)
	public int updateFavpluse(int mid){
		SqlSession session = factory.openSession();
		int updatefav = session.update("whathome.Likeinterior_board", mid);
		session.close();
		return updatefav;
	}
	//좋아요 업뎃(-)
	public int updateFavcan(int mid){
		SqlSession session = factory.openSession();
		int cancel = session.update("whathome.CancelLikeinterior_board", mid);
		session.close();
		return cancel;
	}
	//좋아요 정보 입력
	public int insertFav(FavVO fav){
		SqlSession session = factory.openSession();
		int insert = session.insert("whathome.insertFavinterior_board", fav);
		session.close();
		return insert;
	}
	//좋아요 취소
	public int deleteFav(FavVO fav){
		SqlSession session = factory.openSession();
		int delete = session.delete("whathome.deleteFavinterior_board", fav);
		session.close();
		return delete;
	}
}