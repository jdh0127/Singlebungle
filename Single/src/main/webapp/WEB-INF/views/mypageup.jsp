<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/insert.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" href="css/skitter.styles3.css">
<link rel="stylesheet" href="css/main.css">

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
</style>
</head>

<body>
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

	<div class="my_page_box">
		<ul>
			<li><a href="mypage">마이페이지</a></li>
			<li><a href="customer">고객센터</a></li>
			<li><a href="#">채팅하기</a></li>
			<li><a href="#">회사정보</a></li>
		</ul>
	</div>

	<div id="mypage_contianer">
		<h1 id="mypage_title">마이페이지</h1>
		<form action="confirm_update" id="mypageView_form" method="post">
			<table class="mypage_td_up">
				<c:forEach var="member" items="${members}">
					<tr>
						<th>이메일</th>
						<td class="mypageup_email"><strong>${member.email}</strong></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" style="width: 300px"
							placeholder="이름 입력" value="${member.name}" name="name"></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td class="mypageup_nick">${member.nick}</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" style="width: 300px"
							placeholder="비밀번호 입력" value="${member.pwd}" name="pwd"></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" style="width: 300px"
							placeholder="생년월일 입력" value="${member.birth}" name="birth"></td>
					</tr>
					<tr>
						<th>폰번호</th>
						<td><input type="text" style="width: 300px"
							placeholder="폰번호입력" value="${member.phone}" name="phone"></td>
					</tr>
					<tr class="margin_item">
						<th>위치</th>
						<td><span id="category1"></span><span id="category2"></span>
							<input type="hidden" id="loc" size="20" name="loc"></td>
					</tr>
					<tr class="mypageup_btn">
						<td colspan="2" class="last_td"><a href="#"><input
								type="submit" value="확인" class="join3_btn"></a> <a href="#"><input
								type="reset" value="다시 작성" class="reset3_btn"></a></td>
					</tr>
					<input type="hidden" value="<%=session.getAttribute("loginUser")%>"
						name="nick">
					<input type="hidden" value="${member.email }" name="email">
				</c:forEach>
			</table>
		</form>
	</div>
	<!-- container E -->

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">
	$(".my_page_btn_link").click(
			function() {
				if ($(this).find("img").attr("src") == "img/my_btn.png") {
					$(this).find("img").attr(
							"src",
							$(this).find("img").attr("src").replace(".png",
									"_on.png"));
					$(".my_page_box").slideDown();
				} else {
					$(this).find("img").attr(
							"src",
							$(this).find("img").attr("src").replace("_on.png",
									".png"));
					$(".my_page_box").slideUp();
				}
			});
</script>
</html>