package Whatdo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spring.FavVO;
import spring.SearchVO;
import spring.pageVO;

public class WhatplaDAO {
	private SqlSessionFactory factory;

	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public List<Whatdo> selectAll(int startRcdNo, int endpage) {
		SqlSession session = factory.openSession();
		pageVO pageVo = new pageVO();

		pageVo.setStartRcdNo(startRcdNo);
		pageVo.setEndpage(endpage);
		List<Whatdo> results = session.selectList("whatdo.selectpla_boardList", pageVo);
		session.close();
		return results;
	}

	public int countRecords() {
		SqlSession session = factory.openSession();
		int listCount = session.selectOne("whatdo.selectpla_boardListCount");
		session.close();
		return listCount;
	}

	public int insert(Whatdo whatdo) {
		SqlSession session = factory.openSession();
		int insert = session.insert("whatdo.insertpla_board", whatdo);
		session.close();
		return insert;
	}

	public List<Whatdo> search(String search, String sec) {
		SqlSession session = factory.openSession();
		SearchVO searchVO = new SearchVO();

		searchVO.setSearch(search);
		searchVO.setSec("%" + sec + "%");

		List<Whatdo> results = session.selectList("whatdo.searchpla_board", searchVO);
		session.close();
		return results;
	}

	public Whatdo selectBoardByMid(int mid) {
		SqlSession session = factory.openSession();
		Whatdo whatdo = session.selectOne("whatdo.selectpla_boardview", mid);
		session.close();
		return whatdo;
	}

	public int updateHit(int mid) {
		SqlSession session = factory.openSession();
		int updateReadCount = session.update("whatdo.updatepla_boardhit", mid);
		session.close();
		return updateReadCount;
	}

	public Whatdo update(Whatdo whatdo) {
		SqlSession session = factory.openSession();
		session.update("whatdo.updatepla_board", whatdo);
		session.close();
		return whatdo;
	}

	public int delete(int mid) {
		SqlSession session = factory.openSession();
		int deletecount = session.delete("whatdo.deletepla_board", mid);
		session.close();
		return deletecount;
	}
	
	//의견보기
	public ArrayList<Comment> getCommentListpla_board(int num){
		SqlSession session = factory.openSession();
		List<Comment> commentList = session.selectList("whatdo.getCommentListpla_board", num);
		session.close();
		return (ArrayList<Comment>) commentList;
	}
	
	//의견쓰기
	public int insertComment(Comment comment){
		SqlSession session = factory.openSession();
		int insert = session.insert("whatdo.CommentInsertpla_board", comment);
		session.close();
		return insert;
	}
	//의견 삭제
	public int deleteComment(int num){
		SqlSession session = factory.openSession();
		int insert = session.delete("whatdo.CommentDeletepla_board", num);
		session.close();
		return insert;
	}
	//의견 수정
	public Comment updateComment(Comment comment){
		SqlSession session = factory.openSession();
		int a = session.update("whatdo.CommentUpdatepla_board", comment);
		session.close();
		return comment;
	}
	//좋아요 체크
	public int checkFav(FavVO fav){
		SqlSession session = factory.openSession();
		int a = session.selectOne("whatdo.CheckFavpla_board", fav);
		session.close();
		return a;
	}
	//좋아요 업뎃(+)
	public int updateFavpluse(int mid){
		SqlSession session = factory.openSession();
		int updatefav = session.update("whatdo.Likepla_board", mid);
		session.close();
		return updatefav;
	}
	//좋아요 업뎃(-)
	public int updateFavcan(int mid){
		SqlSession session = factory.openSession();
		int cancel = session.update("whatdo.CancelLikepla_board", mid);
		session.close();
		return cancel;
	}
	//좋아요 정보 입력
	public int insertFav(FavVO fav){
		SqlSession session = factory.openSession();
		int insert = session.insert("whatdo.insertFavpla_board", fav);
		session.close();
		return insert;
	}
	//좋아요 취소
	public int deleteFav(FavVO fav){
		SqlSession session = factory.openSession();
		int delete = session.delete("whatdo.deleteFavpla_board", fav);
		session.close();
		return delete;
	}
}
