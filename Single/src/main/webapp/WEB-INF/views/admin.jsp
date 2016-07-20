<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="css/reset.css"/>
<link rel="stylesheet" href="css/skitter.styles3.css">
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/member.js"></script>

<style>
.margin_item{
	height:60px;
}

.my_page_box{
	padding:50px;
	position:fixed;
	top:36px;
	right:7px;
	z-index:90000;
	background-color:rgba(0,0,0,0.8);
	display:none;
}
.my_page_box li{
	font-weight:bold;
	padding:20px 5px;
}
.my_page_box li>a{
	color:#fff;
	display:block;
}
.my_page_box li>a:HOVER {
	color:#f85;
}
</style>
<body>
<div id="wrap">
		<div id="header">
            <div class="headerinner">
            <h1><a href="main"><span>single벙글</span></a></h1>
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
                	if(session.getAttribute("loginUser") != null)	// 세션의 키 값이 있으면
                	{     
                	%>                     
                            
                		                		
                		<a href='#' ><img src='img/logout_btn.gif'  alt='로그아웃' onclick="return out2()"></a> 
                		<a href='#<%-- mypage?nick=<%=session.getAttribute("loginUser")%> --%>' class="my_page_btn_link"><img src='img/my_btn.png' alt='마이페이지' class="my_page_btn"></a>
                	<%
                	}%>
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
      		<h1 id="mypage_title">회원정보관리</h1>
			<form action="deletemember" id="mypageView_form" method="post">		
				<table class="admin_td">
					<thead>
						<tr>
							<th width="10%">닉네임</th>
							<th>이메일</th>
							<th width="10%">이름</th>
							<th width="10%">생일</th>
							<th width="15%">폰번호</th>
							<th width="15%">위치</th>
							<th width="10%">위치공유</th>
							<th width="10%">포인트</th>
						</tr>				
					</thead>
					<tbody>
						<c:forEach var="member" items="${member}">
							<tr>
								<td class="nick_check"><input type="checkbox" name="nick" value="${member.nick }">${member.nick }</td>
								<td>${member.email }</td>
								<td>${member.name}</td>
								<td>${member.birth}</td>
								<td>${member.phone}</td>
								<td>${member.loc}</td>
								<td>${member.locCheck}</td>
								<td>${member.point}</td>
							</tr>
						</c:forEach>
					</tbody>
<%-- 
					<tr class="mypageView_btn">
						<td colspan="2" class="last_td">
						<c:choose>
							<c:when test="${member.admin == 1}">
							<a href='#'><input type="button" value="회원관리" onclick="location.href=''"></a>
							<a href='#'><input type="submit" value="수정하기" class="join3_btn"></a>
							<a href="memberdelete?email=${member.email}"><input type="button" value="탈퇴하기" class="reset3_btn"></a>
							</c:when>
							<c:otherwise>
							
							<a href='#'><input type="submit" value="수정하기" class="join3_btn"></a>
							<a href="memberdelete?email=${member.email}"><input type="button" value="탈퇴하기" class="reset3_btn"></a>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
 --%>					
				</table>
				<input type="submit" value="삭제하기" class="delete.btn">
	   		 </form>
		</div><!-- container E -->
            
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">
$(".my_page_btn_link").click(function(){
	if($(this).find("img").attr("src")=="img/my_btn.png"){
	 $(this).find("img").attr("src", $(this).find("img").attr("src").replace(".png", "_on.png"));
		$(".my_page_box").slideDown();
	}else{
	 $(this).find("img").attr("src", $(this).find("img").attr("src").replace("_on.png", ".png"));
		$(".my_page_box").slideUp();
	}
});
</script>
</html>