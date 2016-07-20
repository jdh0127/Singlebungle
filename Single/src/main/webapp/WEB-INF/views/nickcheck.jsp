<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리</title>
<script type="text/javascript" src="js/member.js"></script>
<script type="text/javascript">
function nickok(){
	opener.frm1.nick.value="${nick}";
	opener.frm1.renick.value="${nick}";
	self.close();
}

</script>

</head>
<body>
<h2> 닉네임 중복확인 </h2>
<form action="Nickcheck" name="frm1">
닉네임 <input type="text" name="nick">
<input type=submit value="중복 체크">
<br>
<c:if test="${result == 1 }">
<script type="text/javascript">
opener.document.frm1.nick.value="";
</script>
${nick}는 이미 사용 중인 닉네임입니다.
</c:if>
<c:if test="${result==0}">
${nick}는 사용 가능한 닉네임입니다.
<input type="button" value="사용" class="cancel" onclick="nickok()">
</c:if>
</form>
</body>
</html>