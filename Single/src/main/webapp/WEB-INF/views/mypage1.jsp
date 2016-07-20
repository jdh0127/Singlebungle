<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	      <h1 id="mypage_title">마이페이지</h1>
	      <p class="not_pwd">${mes}</p>
	      <form method="post" action="confirmpwd">
	      	  <div class="mypage_input_box">
	      	       <input type="password" style="width: 280px"  placeholder="비밀번호를 입력해주세요" value="" name="pwd">
	      	       <input type="hidden" value="<%=session.getAttribute("loginUser")%>" name="nick">
	      	  </div>
	      	  <div class="mypage_btn">
      	  		   <input type="submit" value="확인">
      	  		   <a href="main"><input type="button" value="취소"></a>
	      	  </div>
	      </form>
	  </div>
</body>
	  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">
$(".my_page_btn_link>img").click(function(){
	if($(this).attr("src")=="img/my_btn.png"){
	 $(this).attr("src", $(this).attr("src").replace(".png", "_on.png"));
		$(".my_page_box").slideDown();
	}else{
	 $(this).attr("src", $(this).attr("src").replace("_on.png", ".png"));
		$(".my_page_box").slideUp();
	}
});
</script>            
</html>