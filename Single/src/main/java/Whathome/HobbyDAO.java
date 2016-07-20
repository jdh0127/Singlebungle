package Whathome;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;

public class HobbyDAO {

	private SqlSessionFactory factory;

	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public List<Hobby> selectAll(int startRcdNo, int endpage) {
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();

		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Hobby> results = session.selectList("hobby.selecthobbyList", pageVo);
		return results;
	}

	public int countRecords() {
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("hobby.selecthobbyListCount");
		session.close();
		return listCount;
	}

	public int insert(Hobby hobby) {
		SqlSession session = factory.openSession();

		int insert = session.insert("hobby.inserthobby", hobby);
		return insert;
	}

	public List<Hobby> search(String search, String sec) {
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();

		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");

		List<Hobby> results = session.selectList("hobby.searchhobby", searchVO);
		session.close();
		return results;
	}

	public Hobby selectBoardByMid(int mid) {
		SqlSession session = factory.openSession();
		Hobby hobby = session.selectOne("hobby.selecthobbyview", mid);
		session.close();
		return hobby;
	}

	public int updateHit(int mid) {
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("hobby.updatehobbyhit", mid);
		session.close();
		return updateReadCount;
	}

	public Hobby update(Hobby hobby) {
		SqlSession session = factory.openSession();
		session.update("hobby.updatehobby", hobby);
		session.close();
		return hobby;
	}

	public int delete(int mid) {
		SqlSession session = factory.openSession();
		int deletecount = session.delete("hobby.deletehobby", mid);
		session.close();
		return deletecount;
	}
	
	//의견보기
	public ArrayList<Comment> getCommentListhobby_board(int num){
		SqlSession session = factory.openSession();
		List<Comment> commentList = session.selectList("hobby.getCommentListhobby_board", num);
		session.close();
		return (ArrayList<Comment>) commentList;
	}
	
	//의견쓰기
	public int insertComment(Comment comment){
		SqlSession session = factory.openSession();
		int insert = session.insert("hobby.CommentInserthobby_board", comment);
		session.close();
		return insert;
	}
	//의견 삭제
	public int deleteComment(int num){
		SqlSession session = factory.openSession();
		int insert = session.delete("hobby.CommentDeletehobby_board", num);
		session.close();
		return insert;
	}
	//의견 수정
	public Comment updateComment(Comment comment){
		SqlSession session = factory.openSession();
		int a = session.update("hobby.CommentUpdatehobby_board", comment);
		session.close();
		return comment;
	}
	//좋아요 체크
	public int checkFav(FavVO fav){
		SqlSession session = factory.openSession();
		int a = session.selectOne("hobby.CheckFavhobby_board", fav);
		session.close();
		return a;
	}
	//좋아요 업뎃(+)
	public int updateFavpluse(int mid){
		SqlSession session = factory.openSession();
		int updatefav = session.update("hobby.Likehobby_board", mid);
		session.close();
		return updatefav;
	}
	//좋아요 업뎃(-)
	public int updateFavcan(int mid){
		SqlSession session = factory.openSession();
		int cancel = session.update("hobby.CancelLikehobby_board", mid);
		session.close();
		return cancel;
	}
	//좋아요 정보 입력
	public int insertFav(FavVO fav){
		SqlSession session = factory.openSession();
		int insert = session.insert("hobby.insertFavhobby_board", fav);
		session.close();
		return insert;
	}
	//좋아요 취소
	public int deleteFav(FavVO fav){
		SqlSession session = factory.openSession();
		int delete = session.delete("hobby.deleteFavhobby_board", fav);
		session.close();
		return delete;
	}
}
