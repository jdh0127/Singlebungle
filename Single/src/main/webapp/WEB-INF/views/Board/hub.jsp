<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/reset.css"/>
<link rel="stylesheet" href="../css/main.css">
<script type="text/javascript" src="../js/member.js"></script>
	
	<style type="text/css">
		html body {
					background:#e5e5e5 !important;
		}
		#hub_conent{
					margin:120px auto 0; 
					width:1000px; 
					background-size:100%; 
					padding:20px 20px 50px 200px; 
		}
		#hub_conent:after{
					content:"";
					display:block;
					clear: both;
		}
		.hub_conent_main_menus{
					width:200px; 
					position:fixed;
					top:131px;
					left:0px;
		}
		#hub_conent_boards{
					width:1000px; 
					padding-bottom:50px;	
					float:left;
		}
		#hub_footer{
					margin-top:100px;
					width:100%;
					background:#333;
					padding-bottom:100px; 
					position:fixed;
					bottom:0px;
					z-index:-1;
		}
		.borad_content{
					width:700px;
					margin:0 auto;
		}
		.borad_content_header{
					background:url("../img/boardbg_top.png") no-repeat; 
					height:50px;
		}
		.borad_content_header>span{
			color:#e5e5e5;
			font-weight:bold;
			line-height:50px;
			padding-left:20px;
		}
		.borad_contentinner{
					background:url("../img/boardbg_con.jpg") repeat-y; 
		}
		.borad_footer{
					background:url("../img/boardbg_bottom.png") no-repeat;
					padding-top:70px;
		}
		.hub_sub_menu{
					border:1px solid #1e242b;
					border-left:none;
					border-right:none;
		}
		.hub_conent_menu>li{
					border-top:1px solid #4a4e5a;
					border-bottom:1px solid #1e2127;
		}			
		.hub_conent_menu>li>a:link, .hub_conent_menu>li>a:visited{
		            display:block;
		            background:#383d49 url("../img/hub_sumBottom.png") no-repeat;
		            color:#fff;
		            height:35px;
		            line-height:35px;
		            font-weight:bold;
		            text-decoration:none;
		            text-align:center;
        }
        .hub_conent_menu>li>a:hover, .hub_conent_menu>li>a:focus{
		            background:#596686 url("../img/hub_sumTop.png") no-repeat;
		            color:#99f;
        }
        .hub_sub_menu>li>a:link, .hub_sub_menu>li>a:visited{
		            display:block;
		            height:30px;
		            line-height:30px;
		            text-align:center;
		            text-decoration:none;
		            color:#aaa;
		            background:url("../img/hub_list_bg.jpg") no-repeat;
		        	/* margin:0 0 5px 0; */
        }
        .hub_sub_menu>li>a:hover, .hub_sub_menu>li>a:focus{
		            color:#fff;
		            background:#666;
		}
        
        
        
	</style>
</head>
<!-- <script type="text/javascript">

if($("table img").css("width") > 640 ){
	
}
	
</script> -->
<body>
	<div id="wrap">
		<div id="header">
            <div class="headerinner">
            <h1><a href="/app/main"><span>single벙글</span></a></h1>
                <div class="navi">
                    <ul>
                        <li><a href="/app/main#page1">오늘은뭐하지</a></li>
                        <li><a href="/app/main#page2">오늘은뭐사지</a></li>
                        <li><a href="/app/main#page3">집에서뭐하지</a></li>
                        <li><a href="/app/main#page4">요거는몰랐지</a></li>
                        <li><a href="/app/main#page5">사거팔거없나</a></li>
                    </ul>
                </div>
           		 <div class="login">
               		<div>
               			<%
                	if(session.getAttribute("loginUser") != null)	// 세션의 키 값이 있으면
                	{     
                	%>                     
                            
                		                		
                		<a href='#' ><img src='../img/logout_btn.gif'  alt='로그아웃' onclick="return out()"></a> 
                		<a href='#' class="my_page_btn_link"><img src='../img/my_btn.png' alt='마이페이지' class="my_page_btn"></a>
                	<%
                	}else{ 	// 세션의 키 값이 없으면

                    	%>  
                		<a href='#header' class="login_btn"><img src='../img/login_btn.gif' alt='로그인' ></a>
                		 <script type="text/javascript">
                      		function session1(){
                         
                         
                         var result = confirm("접근권한이 없습니다. 로그인 하시겠습니까?");
                         if(result){
                            location.href='main_login'
                            return false;
                         }else{
                            return false;
                       	}	    
                      }
                      </script>
                		<%
                	}      	
                	
                	%>  
                	</div>

                
                    <div class="logininner">
                        <div class="login_box">
                            <h3>LOGIN</h3>

                           <form method="post" name="frm" action="Login.do">
                                <input type="text" value="" name="email" placeholder="이메일을 입력해주세요" maxlength="30" id="sing_id">
                                <input type="password" value="" name="pwd" placeholder="암호를 입력해주세요" maxlength="20" id="sing_pw">
                                <input type="submit" value="로그인" onclick="return loginCheck()" class="login_btn">
                                <input type="button" value="회원가입" onclick="location.href='경로'" class="join_btn">
                            </form>
                            
                            <div class="login_close">
                            <a href="#header"><span>X</span></a>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div><!-- headerinner END -->
		</div>
		<div id="hub_conent">
		
		
		<div class="hub_conent_main_menus"> <!-- 서브 메뉴 바 Start -->
				<ul class="hub_conent_menu">
					<li><a href="#"><img src="../img/hub_sum1.png"></a>
						<ul class="hub_sub_menu">
							<li>
								<a href="/app/whateat/list">혼자 먹을 식당</a>
							</li>
							<li>
								<a href="/app/whatpla/list">혼자 놀 장소</a>
							</li>
						</ul>
					</li>
					
					<li>
						<a href="#"><img src="../img/hub_sum2.png"></a>
						<ul class="hub_sub_menu">
							<li><a href="/app/whatsale/list">세일 상품</a></li>
							<li><a href="/app/whatshare/list">쇼핑 공유</a></li>
						</ul>
					</li>
					<li><a href="#"><img src="../img/hub_sum3.png"></a>
						<ul class="hub_sub_menu">
							<li><a href="/app/interior/list">인테리어</a></li>
							<li><a href="/app/homemake/list">집밥</a></li>
							<li><a href="/app/hobby/list">취미</a></li>
						</ul>
					</li>
					<li>
						<a href="#"><img src="../img/hub_sum4.png"></a>
						<ul class="hub_sub_menu">
							<li><a href="/app/honey/list">꿀팁</a></li>
						</ul>
					</li>
					<li>
						<a href="#"><img src="../img/hub_sum5.png"></a>
						<ul class="hub_sub_menu">
							<li><a href="/app/todayfree/list">무료나눔</a></li>
							<li><a href="/app/todaybuy/list">삽니다</a></li>
							<li><a href="/app/todaysell/list">팝니다</a></li>
						</ul>
					</li>
				</ul>
			</div> <!-- 서브 메뉴 바 END -->
			<div class="my_page_box"> 
      	<ul>
      		<li><a href="../mypage">마이페이지</a></li>
      		<li><a href="../customer">고객센터</a></li>
      		<li><a href="#">채팅하기</a></li>
      		<li><a href="#">회사정보</a></li>
      	</ul>
      </div>
		
			
			
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/jquery-1.11.0.min.js"></script>

                	<script type="text/javascript">
$(".my_page_btn_link>img").click(function(){
	if($(this).attr("src")=="../img/my_btn.png"){
	 $(this).attr("src", $(this).attr("src").replace(".png", "_on.png"));
		$(".my_page_box").css({"display":"block"});
	}else{
	 $(this).attr("src", $(this).attr("src").replace("_on.png", ".png"));
		/* $(".my_page_box").slideUp(); */
		$(".my_page_box").css({"display":"none"});
	}
});
</script> 
<script>


//어커디언 -------------------------------------
$(".hub_sub_menu").hide();
$(".hub_conent_menu>li>a").click(function(){
    //$(".hub_sub_menu").slideUp(500);
    $(this).next().stop().slideToggle(500);
    $(this).parent().siblings().children("ul").slideUp(500);
});

</script>

			<div id="hub_conent_boards">
				<div class="borad_content">
				<%-- <jsp:include page="<%=/* 게시판이 들어갈장소 */ %>"/> --%>
				<div class="borad_content_header"><span>  </span></div>
				<div class="borad_contentinner">