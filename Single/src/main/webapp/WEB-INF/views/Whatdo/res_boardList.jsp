<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Whatdo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	width: 650px;
}

.res_board_header>th {
	padding: 2px 0;
	border-bottom: 1px solid #999;
}

.res_board_header>th:nth-child(1)>div {
	width: 56px;
	border-right: 1px solid #999;
}

.res_board_header>th:nth-child(2)>div {
	width: 394px;
	border-right: 1px solid #999;
}

.res_board_header>th:nth-child(3)>div {
	width: 119px;
	border-right: 1px solid #999;
}

.res_board_header>th:nth-child(4)>div {
	width: 80px;
}

.record>td {
	border-bottom: 1px dashed #999;
	padding: 5px 0;
	text-align: center;
}

.record>td:nth-child(2) {
	text-align: left;
}

.res_board_paging {
	padding: 5px 0;
}

.res_search_box {
	width: 410px;
	margin: 0 auto;
}

.res_text_up {
	border: 1px solid #ddb8b8;
	background: #ce5353;
	margin: 10px 10px 10px 0;
	padding: 4px 2px;
	color: #fff;
	display: block;
	width: 55px;
	float: right;
	text-align: center;
}
</style>
</head>

<body>
	<table>
		<tr>
			<td colspan="5" class="res_text_box"><a
				href="write" class="res_text_up">글 등록</a></td>
		</tr>
		<tr class="res_board_header">
			<th><div>번호</div></th>
			<th><div>제목</div></th>
			<th><div>글쓴이</div></th>
			<th><div>조회수</div></th>
			<!-- <th>매우아주좋아요</th> -->
		</tr>

		<c:forEach var="test" items="${whatdos}">
			<tr class="record">
				<td>${test.mid }</td>
				<td><a href="view?mid=${test.mid }">${test.loc} ${test.title }</a></td>
				<td>${test.nick }</td>
				<td>${test.hit }</td>
				<%-- <td>${test.fav }</td> --%>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" class="res_board_paging">${pagecounting }</td>
		</tr>
	</table>
	<div class="res_search_box">
		<form method="post" name="frm1" action="search">
			<input type="hidden" name="board1" value="1" /> <select
				name="search">
				<option>선택</option>
				<option value="loc">위치</option>
				<option value="title">제목</option>
				<option value="nick">닉네임</option>
			</select> <input type="text" name="sec" size="20"> <input
				type="submit" value="찾기">
		</form>
</div>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
		$(".borad_content_header>span").html("혼자 먹을 식당");
		$(".hub_sub_menu:eq(0)").slideDown(600); // 오늘은뭐하지 -----슬라이드 다운
</script>
</html>