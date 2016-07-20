package Honey;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;

public class HoneyDAO {

	
	private SqlSessionFactory factory;
	
	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public List<Honey> selectAll(int startRcdNo, int endpage){
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();
		
		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Honey> results = session.selectList("honey.selecthoneyList", pageVo);
		return results;
	}
	
/*	public List<Honey> selectAll(int startRcdNo, int endpage ){
		List<Honey> results = jdbcTemplate.query("SELECT A.* FROM( SELECT @ROWNUM := @ROWNUM + 1 AS rnum, b.* FROM (select * from honey order by mid desc) b, (SELECT @ROWNUM := 0) R) A WHERE A.rnum >= ? and A.rnum < ?", memRowMapper, startRcdNo, endpage );
		return results;
	}*/
	
	public int countRecords(){
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("honey.selecthoneyListCount");
		session.close();
		return listCount;
		
	}
	
	//占쏙옙 占쏙옙占�
	public int insert(Honey honey){
		SqlSession session = factory.openSession();
		
		int insert = session.insert("honey.inserthoney", honey);
		return insert;
	}
	public List<Honey> search(String search, String sec){
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();
		
		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");
		
		List<Honey> results = session.selectList("honey.searchhoney", searchVO);
		session.close();
		return results;
	}
	
	public Honey selectBoardByMid(int mid){
		SqlSession session = factory.openSession();
		Honey honey = session.selectOne("honey.selecthoneyview", mid);
		session.close();
		return honey;
	}
	
	public int updateHit(int mid){
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("honey.updatehoneyhit", mid);
		session.close();
		return updateReadCount;
		
	}
	
	public Honey update(Honey honey){
		SqlSession session = factory.openSession();
		int updateCount = session.update("honey.updatehoney", honey);
		session.close();
		return honey;
	}
	
	public int delete(int mid){
		SqlSession session = factory.openSession();
		int deletecount = session.delete("honey.deletehoney", mid);
		session.close();
		return deletecount;
	}
	
	//의견보기
			public ArrayList<Comment> getCommentListhoney_board(int num){
				SqlSession session = factory.openSession();
				List<Comment> commentList = session.selectList("honey.getCommentListhoney_board", num);
				session.close();
				return (ArrayList<Comment>) commentList;
			}
			
			//의견쓰기
			public int insertComment(Comment comment){
				SqlSession session = factory.openSession();
				int insert = session.insert("honey.CommentInserthoney_board", comment);
				session.close();
				return insert;
			}
			//의견 삭제
			public int deleteComment(int num){
				SqlSession session = factory.openSession();
				int insert = session.delete("honey.CommentDeletehoney_board", num);
				session.close();
				return insert;
			}
			//의견 수정
			public Comment updateComment(Comment comment){
				SqlSession session = factory.openSession();
				int a = session.update("honey.CommentUpdatehoney_board", comment);
				session.close();
				return comment;
			}
			//좋아요 체크
			public int checkFav(FavVO fav){
				SqlSession session = factory.openSession();
				int a = session.selectOne("honey.CheckFavhoney_board", fav);
				session.close();
				return a;
			}
			//좋아요 업뎃(+)
			public int updateFavpluse(int mid){
				SqlSession session = factory.openSession();
				int updatefav = session.update("honey.Likehoney_board", mid);
				session.close();
				return updatefav;
			}
			//좋아요 업뎃(-)
			public int updateFavcan(int mid){
				SqlSession session = factory.openSession();
				int cancel = session.update("honey.CancelLikehoney_board", mid);
				session.close();
				return cancel;
			}
			//좋아요 정보 입력
			public int insertFav(FavVO fav){
				SqlSession session = factory.openSession();
				int insert = session.insert("honey.insertFavhoney_board", fav);
				session.close();
				return insert;
			}
			//좋아요 취소
			public int deleteFav(FavVO fav){
				SqlSession session = factory.openSession();
				int delete = session.delete("honey.deleteFavhoney_board", fav);
				session.close();
				return delete;
			}
}
