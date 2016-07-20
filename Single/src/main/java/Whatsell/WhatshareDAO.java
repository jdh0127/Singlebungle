package Whatsell;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Whatdo.Comment;
import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;

public class WhatshareDAO {
	
private SqlSessionFactory factory;
	
	public void setFactory(SqlSessionFactory factory){
		this.factory = factory;
	}
	
	public List<Whatsell> selectAll(int startRcdNo, int endpage){
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();
		
		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Whatsell> results = session.selectList("whatsell.selectsaleshare_boardList", pageVo);
		session.close();
		return results;
	}
	
	public int countRecords(){
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("whatsell.selectsaleshare_boardListCount");
		session.close();
		return listCount;
	}
	
	
	public int insert(Whatsell whatsell){
		SqlSession session = factory.openSession();
		int insert = session.insert("whatsell.insertsaleshare_board", whatsell);
		session.close();
		return insert;
	}
	
	public List<Whatsell> search(String search, String sec){
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();
		
		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");
		
		System.out.println(searchVO);
		List<Whatsell> results = session.selectList("whatsell.searchsaleshare_board", searchVO);
		session.close();
		return results;
	}
	
	public Whatsell selectBoardByMid(int mid){
		SqlSession session = factory.openSession();
		Whatsell whatsell = session.selectOne("whatsell.selectsaleshare_boardview", mid);
		session.close();
		return whatsell;
	}
	
	public int updateHit(int mid){
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("whatsell.updatesaleshare_boardhit", mid);
		session.close();
		return updateReadCount;
	}
	
	public Whatsell update(Whatsell whatsell){
		SqlSession session = factory.openSession();
		session.update("whatsell.updatesaleshare_board", whatsell);
		session.close();
		return whatsell;
	}
	
	public int delete(int mid){
		SqlSession session = factory.openSession();
		int deletecount = session.delete("whatsell.deletesaleshare_board", mid);
		session.close();
		return deletecount;
	}
	
	//의견보기
	public ArrayList<Comment> getCommentListshare_board(int num){
		SqlSession session = factory.openSession();
		List<Comment> commentList = session.selectList("whatsell.getCommentListshare_board", num);
		session.close();
		return (ArrayList<Comment>) commentList;
	}
	
	//의견쓰기
	public int insertComment(Comment comment){
		SqlSession session = factory.openSession();
		int insert = session.insert("whatsell.CommentInsertshare_board", comment);
		session.close();
		return insert;
	}
	//의견 삭제
	public int deleteComment(int num){
		SqlSession session = factory.openSession();
		int insert = session.delete("whatsell.CommentDeleteshare_board", num);
		session.close();
		return insert;
	}
	//의견 수정
	public Comment updateComment(Comment comment){
		SqlSession session = factory.openSession();
		int a = session.update("whatsell.CommentUpdateshare_board", comment);
		session.close();
		return comment;
	}
	//좋아요 체크
	public int checkFav(FavVO fav){
		SqlSession session = factory.openSession();
		int a = session.selectOne("whatsell.CheckFavshare_board", fav);
		session.close();
		return a;
	}
	//좋아요 업뎃(+)
	public int updateFavpluse(int mid){
		SqlSession session = factory.openSession();
		int updatefav = session.update("whatsell.Likeshare_board", mid);
		session.close();
		return updatefav;
	}
	//좋아요 업뎃(-)
	public int updateFavcan(int mid){
		SqlSession session = factory.openSession();
		int cancel = session.update("whatsell.CancelLikeshare_board", mid);
		session.close();
		return cancel;
	}
	//좋아요 정보 입력
	public int insertFav(FavVO fav){
		SqlSession session = factory.openSession();
		int insert = session.insert("whatsell.insertFavshare_board", fav);
		session.close();
		return insert;
	}
	//좋아요 취소
	public int deleteFav(FavVO fav){
		SqlSession session = factory.openSession();
		int delete = session.delete("whatsell.deleteFavshare_board", fav);
		session.close();
		return delete;
	}
}

