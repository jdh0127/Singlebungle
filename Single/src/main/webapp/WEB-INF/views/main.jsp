<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>싱글벙글</title>
</head>
<link rel="stylesheet" type="text/css" href="css/reset.css"/>
<link rel="stylesheet" href="css/skitter.styles3.css">
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<script type="text/javascript" src="js/member.js"></script>
<script type="text/javascript" src="js/insert.js"></script> 

<style type="text/css">
 
</style>

<body>
	<div id="wrap">
		<div id="header">
            <div class="headerinner">
            <h1><a href="#"><span>single벙글</span></a></h1>
                <div class="navi">
                    <ul>
                        <li><a href="#page1">오늘은뭐하지</a></li>
                        <li><a href="#page2">오늘은뭐사지</a></li>
                        <li><a href="#page3">집에서뭐하지</a></li>
                        <li><a href="#page4">요거는몰랐지</a></li>
                        <li><a href="#page5">사거팔거없나</a></li>
                    </ul>
                </div>
           		 <div class="login">
               		<div>
               			<%
               		//	System.out.println(session.getAttribute("loginUesr"));
                	if(session.getAttribute("loginUser") != null)	// 세션의 키 값이 있으면
                	{     
                	%>                     
                            
                		                		
                		<a href='#' ><img src='img/logout_btn.gif'  alt='로그아웃' onclick="return out()"></a> 
                		<a href='#' class="my_page_btn_link"><img src='img/my_btn.png' alt='마이페이지' class="my_page_btn"></a>
                	<%
                	}else{ 	// 세션의 키 값이 없으면

                    	%>  
                		<a href='#header' class="login_btn"><img src='img/login_btn.gif' alt='로그인' ></a>
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

							<form:form commandName="member" >
							
                                <form:input type="text" value="" path="email" name="email" placeholder="이메일을 입력해주세요" maxlength="30" id="sing_id" />
                                <form:errors path="email"/>
                                <input type="password" value="" name="pwd" placeholder="암호를 입력해주세요" maxlength="20" id="sing_pw">
                                <form:errors path="pwd" />
                                <label><spring:message code="rememberEmail" />
                                <form:checkbox path="rememberEmail" class="email_check"/></label>
                                <br> 
                                <input type="submit" value="로그인" onclick="return loginCheck()" class="login_btn">
                                <input type="button" value="<spring:message code="member.register"/>" class="join_btn">
                                
                            </form:form>
                            
                            <div class="login_close">
                            <a href="#header"><span>X</span></a>
                            </div>
                        </div>
                        
                    </div>
                </div><!-- login END -->
              
	                <div class="joininner"><!-- joininner Start -->
	    						<div class="join_box">
	    							<h3><spring:message code="member.register"/></h3>
	    							<form method="post" name="frm1" action="Memberjoin">
	    							<input type="email" value="" name="email" placeholder="이메일을 입력해주세요" maxlength="30" id="join_id">
	    							<input type="hidden" name="reemail" size="20"> 
	    							<input type="button" value="중복 체크" onclick="emailCheck()"  class="emailcheck3_btn">		
	    							<input type="text" name="name" placeholder="이름을 입력해주세요" maxlength="30" id="join_name">		
	    							<input type="text" name="nick" placeholder="닉네임을 입력해주세요" maxlength="30" id="join_nick"> 
	    							<input type="hidden" name="renick" size="20"> 
	    							<input type="button" value="중복 체크" onclick="nickCheck()"  class="nickcheck3_btn">	
	   								<input type="password" value="" name="pwd" placeholder="암호를 입력해주세요" maxlength="20" id="join_pw">
	   			
	   								<input type="password" value="" name="pwdcheck" placeholder="암호 확인" maxlength="20" id="join_pwdcheck">
	    							<input type="datetime" name="birth" placeholder="생년월일을 입력해주세요" maxlength="20" id="join_birth">	
	    							<input type="tel" name="phone" placeholder="폰번호를 입력해주세요" size="50" maxlength="20" id="join_phone">	
	    							<span id="category1"></span><span id="category2"></span>
	    							<input type="hidden" id="loc" size="20" name="loc">
	    							<input type="submit" value="회원가입"  onclick="return joinCheck()"  class="join3_btn"> 
	    							<input type="reset" value="다시 작성" class="reset3_btn"><br>
	    						</form>
	                            <div class="join_close">
	                            <a href="#header"><span>X</span></a>
	                            </div>
	                        </div>
	                </div><!-- joininner END -->
	                
            </div><!-- headerinner END -->
		</div>
		<div id="content">
            <div class="contentinner_main">
                <div id="main_content">
                    <h2>메인 페이지입니다.</h2>
                    <p><img src="img/tv_table.png" alt=""></p>
                    <div class="box_skitter box_skitter_large">
                         <ul>
                            <li>
                                <a href="#"><img src="img/homeplusB.jpg" alt="" class="random"></a>
                                <div class="label_text">홈플러스 바겐세일</div>
                            </li>
                            <li>
                                <a href="#"><img src="img/emartB.jpg" alt="" class="random"></a>
                                <div class="label_text">이마트 이벤트</div>
                            </li>
                            <li>
                                <a href="#"><img src="img/lottemartB.jpg" alt="" class="random"></a>
                                <div class="label_text">롯데마트 세일</div>
                                
                            </li>
                        </ul>
                    </div>
                </div><!-- ---------------- main content END ----------------- -->
            </div>
			<div class="contentinner">
				<div id="page1" class="page">
						<div>
                            <ul>
                                <li><a href="whateat/list" onclick="return session1()"><img src="img/food_map.png" alt="혼자 먹을 식당"></a></li>
                                <li><a href="whatpla/list" onclick="return session1()"><img src="img/joy_map.png" alt="혼자 놀 장소"></a></li>
                            </ul>
                            <p class="f_text"><img src="img/food_text.png" alt="나 혼자서도 잘 논다 싱글들의 즐길수 있는 공간을 공유한다!!"></p>
                            <p class="j_text"><img src="img/joy_text.png" alt=" 혼자서도 잘 먹는다 싱글들도 부담없는 공간을 공유한다!!"></p>
                        </div>						
				</div>
				<div id="page2" class="page">
						<div>
                            <ul>
                                <li><a href="whatsale/list"><img src="img/c1.png" alt="세일 상품"></a></li>
                                <li><a href="whatshare/list"><img src="img/c2.png" alt="쇼핑 공유"></a></li>
                            </ul>
                        </div>
				</div>
				<div id="page3" class="page">
						<div>
                            <ul>
                                <li><a href="interior/list"><img src="img/inte.png" alt="인테리어"></a></li>
                                <li><a href="homemake/list"><img src="img/home_bob.png" alt="집밥"></a></li>
                                <li><a href="hobby/list" onclick="return session1()"><img src="img/hobby.png" alt="취미"></a></li>
                            </ul>
                            <ul>
                                <li><img src="img/inte_text.jpg" alt="집에서도 할수있는 인테리어를 공유하는 공간"></li>
                                <li><img src="img/home_bob_text.jpg" alt="집에서도 할수있는 요리를 공유하는 공간"></li>
                                <li><img src="img/hobby_text.jpg" alt="집에서도 할수있는 취미생활을 공유하는 공간"></li>
                            </ul>
                        </div>
				</div>
				<div id="page4" class="page">
						<div><a href="honey/list" onclick="return session1()"><img src="img/h1.png" alt="꿀팁"></a></div>
				</div>
				<div id="page5" class="page">
				    <div>
                        <ul>
                            <li><a href="todayfree/list"><img src="img/free_btn.png" alt="무료나눔"></a></li>
                            <li><a href="todaybuy/list"><img src="img/buy_btn.png" alt="삽니다"></a></li>
                            <li><a href="todaysell/list"><img src="img/sell_btn.png" alt="팝니다"></a></li>
                        </ul>    
                    </div>
				</div>
			</div><!-- ---------------- contentinner END----------------- -->
			<div class="top_btn">
				<a href="#main_content"><img src="img/rocket.png" alt="top"></a>
			</div>
		</div>
		
		<div id="footer">
			<div class="footerinner">
				
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
      
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.skitter.js"></script>
<script src="js/jquery.scrollTo.min.js"></script>
    
	<script>
		
        $(".box_skitter_large").skitter({dots: true, preview: true});
		 $(".box_skitter_large").skitter({mouseOverButton: function(){}});
     
    $(function(){
        $("#header .navi>ul>li>a").click(function(){
            $("body").stop().scrollTo($(this).attr("href"), 700);
            return false;
        });
        

        
        
       // 로켓트--------------
        $(".top_btn>a").mouseover(function(){ 
        function rocket(){
             $(".top_btn").find("img").stop().animate({top:'-2px'},30,function(){
             $(".top_btn").find("img").stop().animate({left:'-2px'},30,function(){
             $(".top_btn").find("img").stop().animate({left:'+2px'},30,function(){
             $(".top_btn").find("img").stop().animate({left:'-4px'},30,function(){
             $(".top_btn").find("img").stop().animate({left:'+4px'},30,function(){
             $(".top_btn").find("img").stop().animate({top:'+2px'}, 30, rocket);   
             });/*5번째 애니메이트*/
             });/*4번째 애니메이트*/
             });/*3번째 애니메이트*/
             });/*2번째 애니메이트*/  
             });/*1번째 애니메이트*/
        }/*rocket 함수*/
        rocket();
           
        });/* .top_btn 이벤트function*/
        
       $(".top_btn").mouseout(function(){
           $(this).find("img").attr("src", 
            $(this).find("img").attr("src").replace("_c.png", ".png"));
            $(".top_btn").find("img").stop().animate({top:'0px',left:'0px'},30);
           
        });
        
        $(".top_btn").bind("click",function(){
            $(this).find("img").attr("src", 
            $(this).find("img").attr("src").replace("rocket.png", "rocket_c.png")); 
            $(".top_btn").find("img").stop().animate({top:'0px',left:'0px'},30);
            $(this).find("img").fadeOut(800);
            $(this).find("img").fadeIn(0);
            $("body").stop().scrollTo($(this).find("a").attr("href"), 700, function(){
            
            $(".top_btn").find("img").attr("src", 
            $(".top_btn").find("img").attr("src").replace("rocket_c.png", "rocket.png")); 
            });
           
        });
        
        //로켓트 끝 -------------
        
        //이미지 변환-----------------
         $(".page>div a").hover(function(){
				 $(this).find("img").attr("src", $(this).find("img").attr("src").replace(".png", "_on.png"));
				 },function(){
				 $(this).find("img").attr("src", $(this).find("img").attr("src").replace("_on.png", ".png")); 
		});
        //이미지 변환 끝--------------
         
        
        $("#page3>div>ul:nth-child(1)>li:nth-child(1)").hover(function(){
            $("#page3>div>ul:nth-child(2)>li").eq(1).hide(); 
            $("#page3>div>ul:nth-child(2)>li").eq(2).hide(); 
            $("#page3>div>ul:nth-child(2)>li").eq(0).stop().fadeIn();
            
        },function(){
            $("#page3>div>ul:nth-child(2)>li").eq(0).stop().fadeOut(); 
        });
        
        $("#page3>div>ul:nth-child(1)>li:nth-child(2)").hover(function(){
            $("#page3>div>ul:nth-child(2)>li").eq(0).hide(); 
            $("#page3>div>ul:nth-child(2)>li").eq(2).hide(); 
            $("#page3>div>ul:nth-child(2)>li").eq(1).stop().fadeIn();
            
        },function(){
            $("#page3>div>ul:nth-child(2)>li").eq(1).stop().fadeOut(); 
        });
        
        $("#page3>div>ul:nth-child(1)>li:nth-child(3)").hover(function(){
            $("#page3>div>ul:nth-child(2)>li").eq(0).hide(); 
            $("#page3>div>ul:nth-child(2)>li").eq(1).hide(); 
            $("#page3>div>ul:nth-child(2)>li").eq(2).stop().fadeIn();
            
        },function(){
            $("#page3>div>ul:nth-child(2)>li").eq(2).stop().fadeOut(); 
        });
           
       
        //page1 글
        $("#page1 ul>li:nth-child(1)>a").hover(function(){
             $(".f_text").stop().show(800); 
        },function(){
           $(".f_text").stop().hide(800);
        });
        
        $("#page1 ul>li:nth-child(2)>a").hover(function(){
             $(".j_text").stop().show(800); 
        },function(){
           $(".j_text").stop().hide(800);
        });
        

  
//로그인 inner
        $(".login_btn").click(function(){
           $(".logininner").fadeIn(); 
        });
        
       
  
        //로그인 닫기 버튼
        
        $(".login_close>a").hover(function(){
            $(this).css({"border":"5px solid chocolate"});
            $(".login_close>a>span").css({"color":"chocolate"});
        },function(){
            $(this).css({"border":"5px solid #333"});
            $(".login_close>a>span").css({"color":"#333"});
        });
        
        $(".login_close a").click(function(){
            $(".logininner").fadeOut(function(){
             	$(".login_box input:eq(0)").val(""); 
                $(".login_box input:eq(1)").val("");
                $(".email_check").attr("checked", false);


            });
        });
        
     });      
        
    
  //회원가입 inner
    $(".login_btn").click(function(){
         $(".logininner").fadeIn();
    });
  
    $(".join_btn").click(function(){
      	$(".logininner").fadeOut();
      	$(".joininner").fadeIn();
    });
    
    
     

      //회원가입 닫기 버튼
      
      $(".join_close>a").hover(function(){
          $(this).css({"border":"5px solid chocolate"});
          $(".join_close>a>span").css({"color":"chocolate"});
      },function(){
          $(this).css({"border":"5px solid #333"});
          $(".join_close>a>span").css({"color":"#333"});
      });
      
      $(".join_close a").click(function(){
          $(".joininner").fadeOut(function(){
          	$(".join_box input:eq(0)").val("");
              $(".join_box input:eq(1)").val("");
             
              $(".join_box input:eq(3)").val("");
              $(".join_box input:eq(4)").val("");
              $(".join_box input:eq(5)").val("");
              
              $(".join_box input:eq(7)").val("");
              $(".join_box input:eq(8)").val("");
              $(".join_box input:eq(9)").val("");
              $(".join_box input:eq(10)").val("");
              $(".logininner").fadeIn();
          });
      });
      
    
    
 </script>
<script type="text/javascript">
$(".my_page_btn_link>img").click(function(){
	$(".logininner").css({"display":"none !important"});
	if($(this).attr("src")=="img/my_btn.png"){
	 $(this).attr("src", $(this).attr("src").replace(".png", "_on.png"));
		$(".my_page_box").css({"display":"block"});
	}else{
	 $(this).attr("src", $(this).attr("src").replace("_on.png", ".png"));
		/* $(".my_page_box").slideUp(); */
		$(".my_page_box").css({"display":"none"});
	}
});
</script>        
</html>