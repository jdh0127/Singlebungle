<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
</head>
<style>
a img {
	border: 0;
}

html {
	width: 100%;
	height: 100%;
}

body {
	background: url('img/main.jpg') no-repeat;
	background-size: 100% 100%;
}

.intro_content{
width:900px;
height:600px;
position: relative;
margin:10% auto 0;
}

#logo {
	position: absolute;
	left: 23%;
	top: 25%;
}


ul.intro_quick>li:nth-child(1){
position: absolute;
left: 10%;
top: 35%;
}
ul.intro_quick>li:nth-child(2){
position: absolute;
left: 22%;
top: 13%;
}
ul.intro_quick>li:nth-child(3){
position: absolute;
left: 45%;
top: 5%;
}
ul.intro_quick>li:nth-child(4){
position: absolute;
left: 68%;
top: 13%;
}
ul.intro_quick>li:nth-child(5){
position: absolute;
left: 80%;
top: 35%;
}
#intro_footer{
background-color: #575040;
position: fixed;
bottom:0px;
width: 100%;
height: 30px;
}
#intro_footer>ul>li>a {
color:#fff;
display:block;
text-decoration: none;
}
#intro_footer>ul>li{
float: left;
}
.intro_footer_list{
position:relative;
margin-top: 5px;
}
.intro_footer_list>li:nth-child(1){
	margin-left:30px;
}
.intro_footer_list>li:nth-child(2){
	margin-left:50px;
}
.intro_footer_list>li:nth-child(3){
	margin-left:50px;
}
.intro_footer_list>li:nth-child(4){
	position:absolute;
	right:50px;
	top:0px;
		
}
</style>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
	$(".intro_quick>li>a").hover(function(){
		 $(this).find("img").attr("src", $(this).find("img").attr("src").replace(".png", "_on.png"));
	
		 },function(){
		 $(this).find("img").attr("src", $(this).find("img").attr("src").replace("_on.png", ".png")); 
	});
});

</script>
<body>
<div class="intro_content">
	<h1 id="logo">
		<a href="/app/main"><img src="img/logo.png" alt="싱글벙글 로고 홈버튼" title="메인화면 바로가기"></a>
	</h1>
	<ul class="intro_quick">
		<li><a href="#"><img src="img/in_home.png" alt="집에서 뭐하지 버튼" />
		</a></li>
		<li><a href="#"> <img src="img/in_mart.png" alt="오늘은뭐사지 버튼" />
		</a></li>
		<li><a href="boardList.do?board1=1" onclick="return session1()"> <img src="img/in_play.png" alt="오늘은뭐하지 버튼" />
		</a></li>
		<li><a href="#"> <img src="img/in_tip.png" alt="요거는몰랐지 버튼" />
		</a></li>
		<li><a href="#"> <img src="img/in_buy.png" alt="사고팔거없나 버튼" />
		</a></li>
		
	</ul>
</div>
<div id="intro_footer">
<ul class="intro_footer_list">
  	<li><a href="#">회사정보</a></li>
	<li><a href="#">개인정보 취급방침</a></li>
	<li><a href="#">약관</a></li>
	<li><a href="#">고객센터</a></li>
</ul>
</div>
</body>
</html>