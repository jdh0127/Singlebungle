<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script>

<!-- <script type="text/javascript" src="../S.E/js/HuskyEZCreator.js" charset="UTF-8"></script> -->

<script type="text/javascript" src="../js/board_insert.js" charset="UTF-8" ></script>
<link rel="stylesheet" href="../daumeditor/css/editor.css" type="text/css" charset="utf-8"/>
<script src="../daumeditor/js/editor_loader.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//apis.daum.net/maps/maps3.js?apikey=47c6838d7318d65430957b3b8afb57bb&libraries=services"></script>

 <script type="text/javascript">

	$(function(){
	     $.ajax({
	         url : "../daumeditor/editor.jsp",
	         success : function(data){
	             $("#editor_frame").html(data);
	             // 에디터UI load
	             var config = {
	                 /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
	                 txHost: '', 
	                 /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
	                 txPath: '', 
	                 /* 수정필요없음. */
	                 txService: 'sample', 
	                 /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
	                 txProject: 'sample',
	                 /* 대부분의 경우에 빈문자열 */
	                 initializedId: "", 
	                 /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
	                 wrapper: "tx_trex_container",
	                 /* 등록하기 위한 Form 이름 */
	                 form: "frm", 
	                 /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
	                 txIconPath: "../daumeditor/images/icon/editor/", 
	                 /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
	                 txDecoPath: "../daumeditor/images/deco/contents/", 
	                 canvas: {
	                     styles: {
	                         /* 기본 글자색 */
	                         color: "#123456", 
	                         /* 기본 글자체 */
	                         fontFamily: "굴림", 
	                         /* 기본 글자크기 */
	                         fontSize: "10pt", 
	                         /*기본 배경색 */
	                         backgroundColor: "#fff", 
	                         /*기본 줄간격 */
	                         lineHeight: "1.5", 
	                         /* 위지윅 영역의 여백 */
	                         padding: "8px"
	                     },
	                     showGuideArea: false
	                 },
	                 events: {
	                     preventUnload: false
	                 },
	                 sidebar: {
	                     attachbox: {
	                         show: true,
	                         confirmForDeleteAll: true
	                     }
	                 },
	                 size: {
	                     /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
	                     contentWidth: 700 
	                 }
	             };
	              
	             //에디터내에 환경설정 적용하기
	             new Editor(config);
	         }
	     });
	      
	     //form submit 버튼 클릭
	     $("#savebutton").click(function(){
	    	// 제목 조건 검사 
	  	   if(document.getElementById("title").value == ""){		   
	  	      alert("제목을 입력해주세요");
	  	      return false;
	  	   } 
	  	   // 위치 조건 검사 
	  	   if(document.getElementById("loc").value == ""){		   
	  	      alert("위치를 선택해주세요");
	  	      return false;
	  	   } 
	    	 //다음에디터가 포함된 form submit
	         Editor.save();
	     })
	 })
	  
	  
	 //Editor.save() 호출 한 다음에 validation 검증을 위한 함수 
	 //validation 체크해줄 입력폼들을 이 함수에 추가 지정해줍니다.
	 function validForm(editor) {
	     var validator = new Trex.Validator();
	     var content = editor.getContent();
	     if (!validator.exists(content)) {
	         alert('내용을 입력하세요');
	         return false;
	     }
	    
	     return true;
	 }
	   
	 //validForm 함수까지 true값을 받으면 이어서 form submit을 시켜주는  setForm함수
	 function setForm(editor) {
	     var content = editor.getContent();
	     $("#content").val(content)
	     return true;
	 }
</script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

.res_board{font-family: 'Nanum Gothic', sans-serif; width:680px; margin:0 auto;}

form>span{
display: none;
}

td>input[type="text"]{
	background:none;
}
input:-webkit-autofill, textarea:-webkit-autofill, select:-webkit-autofill {
    background-color:rgb(0,0,0,0);
    background-image: none;
    border:none;
}
input, textarea, keygen, select, button {
    text-rendering: auto;
    color: initial;
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    margin: 0em 0em 0em 0em;
    font: 13.3333px Arial;
}
#title{
border-bottom: 1px solid #999;
font-size: 20px;
}
#category1-1,#category2-1{
border: none;
background-color: #666;
color: #fff;
}
#savebutton{
border: 1px solid #ddb8b8;
background-color: #ce5353;
color: #fff;
float:right;
margin:5px 70px 0 0;
padding:5px;
}
.res_textbox{
padding-top:10px;
}
.res_loc{
padding-right: 10px;
}
</style>
<%
Calendar date = Calendar.getInstance();
SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd");
%>
	<form method="post" id="frm" name="frm" action="writedo">
	<input type="hidden" id="nick" name="nick" value="<%=session.getAttribute("loginUser")%>">
	<input type="hidden" id="time" name="time" value="<%=today.format(date.getTime())%>">
	<table align="center" class="res_board">
	<tr>
	<td><label>제목</label></td>
	<td colspan="2"><input type="text" name="title" id="title" size="40"></td>
	</tr>
	<tr>
	<td class="res_loc">위치</td>
	<td><span id="category1"></span><span id="category2"></span>
	<input type="hidden" id="loc" name="loc" size="20">
	</td>
		<td>
		<input type="button" id="savebutton" name="savebutton" onclick="submitContents(this)" value="글등록">
		</td>
	</tr>
	<tr>
		<td colspan="3" style="width:630px; height:412px;" class="res_textbox">
	 	<!-- <textarea name="content" id="content" rows="10" cols="100" style="width:620px; height:408px;" ></textarea> -->
		<!-- 에디터프레임호출 영역 -->
        <div id="editor_frame" style="width:630px; height:400px;"></div>
        <!-- 실제 값이 담겨져서 넘어갈 textarea 태그 -->
        <textarea name="content" id="content" rows="10" cols="100" style="width:766px; height:412px;display: none;"></textarea>
		</td>
	</tr>
	</table>
</form>

	<script type="text/javascript">
		$(".borad_content_header>span").html("혼자 놀 장소");
		 $(".hub_sub_menu:eq(0)").slideDown(600); // 오늘은뭐하지 -----슬라이드 다운
	</script>
