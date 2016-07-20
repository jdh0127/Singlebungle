<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" href="css/skitter.styles3.css">
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/member.js"></script>
<script src="js/jquery-1.11.0.min.js"></script>

<style>
.margin_item {
	height: 60px;
}

.my_page_box {
	padding: 50px;
	position: fixed;
	top: 36px;
	right: 7px;
	z-index: 90000;
	background-color: rgba(0, 0, 0, 0.8);
	display: none;
}

.my_page_box li {
	font-weight: bold;
	padding: 20px 5px;
}

.my_page_box li>a {
	color: #fff;
	display: block;
}

.my_page_box li>a:HOVER {
	color: #f85;
}

.td_text {
	position: relative;
}

.td_text>span {
	position: absolute;
	top: 45px;
	left: 0;
}

#customer_title {
	color: white;
	font-size: 3em;
	font-weight: bold;
	padding: 120px 0 100px 150px;
}

#customer_contianer {
	margin: 0 auto 50px;
	position: relative;
	width: 750px;
	background-color: #92b893;
	box-shadow: 5px 5px rgba(0, 0, 0, 0.3); border-radius : 20px;
	padding: 50px;
	border-radius: 20px;
}

table {
	
}

table td {
	padding-bottom: 10px;
}
.mail_sub{
margin:0 auto;
display:block;
padding:5px 70px;
border:3px solid #925555;
box-shadow:2px 2px rgba(0, 0, 0, 0.3);
border-radius : 5px;
background-color:#eaa;
font-weight:bold;
color:#333;


}
</style>
<body>
<c:if test="${alert != null}">
	${alert }
	<c:set scope="session" var="alert" value="${null}"/>
</c:if>
	<div id="wrap">
		<div id="header">
			<div class="headerinner">
				<h1>
					<a href="main"><span>single벙글</span></a>
				</h1>
				<div class="navi">
					<ul>
						<li><a href="main#page1">오늘은뭐하지</a></li>
						<li><a href="main#page2">오늘은뭐사지</a></li>
						<li><a href="main#page3">집에서뭐하지</a></li>
						<li><a href="main#page4">요거는몰랐지</a></li>
						<li><a href="main#page5">사거팔거없나</a></li>
					</ul>
				</div>
				<div class="login">
					<div>
						<%
							if (session.getAttribute("loginUser") != null) // 세션의 키 값이 있으면
							{
						%>


						<a href='#'><img src='img/logout_btn.gif' alt='로그아웃'
							onclick="return out2()"></a> <a
							href='#<%-- mypage?nick=<%=session.getAttribute("loginUser")%> --%>'
							class="my_page_btn_link"><img src='img/my_btn.png'
							alt='마이페이지' class="my_page_btn"></a>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<h1 id="customer_title">고객센터</h1>
<form action="mailsend" method="post">
	<div id="customer_contianer">

		<table>
			<tr>
				<td style="width: 100px;">작성자</td>
				<td>${loginUser }</td>
			</tr>
			<tr>
				<td>문의유형</td>
				<td><select name="sort" id="sort">
						<option>선택</option>
						<option value="개인정보">개인정보</option>
						<option value="신고/이용제한">신고/이용제한</option>
						<option value="이벤트">이벤트</option>
						<option value="고객의견">고객의견</option>
						<option value="기타">기타</option>
				</select></td>
			</tr>
			<tr>
				<td>문의사항</td>
				<td><input type="text" name="title" id="title" size="48"></td>
			</tr>
			<tr>
				<td class="td_text"><span>문의내용</span></td>
				<td><textarea name="content" id="content" rows="10" cols="100"
						style="width: 620px; height: 408px;"></textarea></td>
		</table>
		<input type="submit" class="mail_sub" value="보내기">
	</div>
	</form>
	
	<!-- 마이페이지 -->
	<div class="my_page_box">
		<ul>
			<li><a href="mypage">마이페이지</a></li>
			<li><a href="customer">고객센터</a></li>
			<li><a href="#">채팅하기</a></li>
			<li><a href="#">회사정보</a></li>
		</ul>
	</div>


</body>
<script type="text/javascript">
	$(".my_page_btn_link>img").click(
			function() {
				if ($(this).attr("src") == "img/my_btn.png") {
					$(this).attr("src",
							$(this).attr("src").replace(".png", "_on.png"));
					$(".my_page_box").css({
						"display" : "block"
					});
				} else {
					$(this).attr("src",
							$(this).attr("src").replace("_on.png", ".png"));
					/* $(".my_page_box").slideUp(); */
					$(".my_page_box").css({
						"display" : "none"
					});
				}
			});
</script>
</html>