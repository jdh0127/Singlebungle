<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import =  "java.util.Calendar" %>
<%@page import="java.text.SimpleDateFormat"%>
<%
Calendar date = Calendar.getInstance();
SimpleDateFormat today = new SimpleDateFormat("yy-MM-dd HH:mm");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- <script type="text/javascript" src="../script/board.js"></script>
 --><script type="text/javascript" src="../S.E/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.min.js" charset="utf-8"></script>
    
    
<script type="text/javascript">
    var oEditors = [];
    $(function() {
        nhn.husky.EZCreator.createInIFrame({
            oAppRef : oEditors,
            elPlaceHolder : "content",
            //SmartEditor2Skin.html 파일이 존재하는 경로
            sSkinURI : "../S.E/SmartEditor2Skin.html",
            htParams : {
                // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
                bUseToolbar : true,
                // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
                bUseVerticalResizer : true,
                // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
                bUseModeChanger : true,
                fOnBeforeUnload : function() {
 
                }
            },
            fOnAppLoad : function() {
                //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
/*                  oEditors.getById["ir1"].exec("PASTE_HTML",
                        [ "기존 DB에 저장된 내용을 에디터에 적용할 문구" ]);  */
            },
            fCreator : "createSEditor2"
        })
    })</script>
    
 <script type="text/javascript">
 function submitContents(elClickedObj) {
	   oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);   // 에디터의 내용이 textarea에 적용됩니다.
	   
	   // 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	   
	   // 제목 조건 검사 
		
	   if(document.getElementById("title").value == ""){		   
	      alert("제목을 입력해주세요");
	      return false;
	   } 
	   
	   // Edit 모드 조건 검사
	   if(document.getElementById("content").value == "<p>&nbsp;</p>"){		  
	      alert("본문내용을 입력해주세요");
	      return false;   
	   }   
	   
	   // Html 모드 조건 검사
	   if(document.getElementById("content").value == "<p></p>"){		  
	      alert("본문내용을 입력해주세요");
	      return false;   
	   }
	   
	   // Text 모드 조건 검사
	   if(document.getElementById("content").value == ""){		   
	      alert("본문내용을 입력해주세요");
	      return false;   
	   }   
	   
	   try {
	      elClickedObj.form.submit();
	   } catch(e) {}
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

	<form method="post" id="frm" name="frm" action="writedo">
	<input type="hidden" id="nick" name="nick" value="<%=session.getAttribute("loginUser")%>">
	<input type="hidden" id="time" name="time" value="<%=today.format(date.getTime())%>">
	<table align="center" class="res_board">
	<tr>
	<td><label>제목</label></td>
	<td colspan="2"><input type="text" name="title" id="title" size="40"></td>
	</tr>
	<tr>
	<td class="res_loc"></td>
		<td>
		<input type="button" id="savebutton" name="savebutton" onclick="submitContents(this)" value="글등록">
		</td>
	</tr>
	<tr>
		<td colspan="3" style="width:630px; height:412px;" class="res_textbox">
	 	<textarea name="content" id="content" rows="10" cols="100" style="width:620px; height:408px;" ></textarea>
		</td>
	</tr>
	</table>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
		$(".borad_content_header>span").html("꿀팁 게시판");
		 $(".hub_sub_menu:eq(3)").slideDown(600); // 꿀팁 게시판  -----슬라이드 다운
	</script>
</html>