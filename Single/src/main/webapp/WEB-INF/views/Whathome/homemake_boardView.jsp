<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="Whatsell.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script>

<script type="text/javascript" src="../ajax/cmtajax.js"></script>
<link rel="stylesheet" href="../css/view.css">

<script type="text/javascript">
var loginuser = "${loginUser}";
var ref = "${whathomes.mid}";
var ddd;
var board="homemake";
</script>
<body>
<form method="get" id="frm">
<input type="hidden" name="board_no" value="${whathomes.mid }">
<table class="view">
<!-- 1행 -->
<tr>
	<td colspan="3" class="titleview">
		<p>${whathomes.title }</p>
	</td>
</tr>

<!-- 2행 -->
<tr>
<td>
작성자 : ${whathomes.nick }
</td>
	<td colspan="2" class="titleview_date">
	<p>${whathomes.time}</p>
	</td>
</tr>

<!-- 3행 -->
<tr>
<td colspan="2" class="titleview_hit">
<p>조회수 : ${whathomes.hit}</p>
</td>
</tr>
<!-- 4행 -->
<tr>
<td colspan="3" class="titleview_con">
${whathomes.content}

</td>
</tr>
<tr>
<td class="fav_btn" colspan="3">
	<br>
	 <img class= "good_btn" title="좋아요!" src="../img/dohyunpng.png" alt="좋아요 버튼" height="40px" width="40px" onclick="favcount()" onmouseover="" id="${whatdos.mid }"/>
	 <img class= "end_good_btn" src="../img/dohyunpng_on.png" alt="좋아요 완료 버튼" height="40px" width="40px" onclick="alertCount()" onmouseover=""/>
	 <div id = "dynamicfav" class="dynamicfav">
	${whathomes.fav }
</div>
</td>
</tr>
<tr>
	<td class="titleview_btns" colspan="3">
<!-- 5행 버튼 -->
<c:choose>
<c:when test="${(loginUser eq 'admin') || (whathomes.nick==loginUser) }">
<input type="button" value="목록" onclick="location.href='list'">
<input type="button" value="글 수정" onclick="location.href='update?mid=${whathomes.mid}'">
<input type="button" value="글 삭제" onclick="location.href='delete?mid=${whathomes.mid}'">	
<input id="mybtn" name="<%=session.getAttribute("loginUser")%>" type="button" value="의견 보기">

</c:when>
<c:otherwise>
<input type="button" value="목록" onclick="location.href='list'">
<input id="mybtn" name="<%=session.getAttribute("loginUser")%>" type="button" value="의견 보기">

</c:otherwise>
</c:choose>
	</td>
</table>
</form>
<div id="commentDisplay">
<table style='font-size:12pt' class="CommentList">

</table>

<div class="opinion_box">
</div>
</div>
</body>
<script type="text/javascript">
	$(".borad_content_header>span").html("집밥");
	 $(".hub_sub_menu:eq(2)").slideDown(600); // 오늘은뭐하지 -----슬라이드 다운
</script>
</html>