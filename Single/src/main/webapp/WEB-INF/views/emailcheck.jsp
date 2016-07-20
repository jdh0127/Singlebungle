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
function emailok(){
	opener.frm1.email.value="${email}";
	opener.frm1.reemail.value="${email}";
	self.close();
}

</script>

</head>
<body>
<h2> 이메일 중복확인 </h2>
<form action="Emailcheck" name="frm1">
이메일 <input type="email" name="email">
<input type=submit value="중복 체크">
<br>
<c:if test="${result == 1 }">
<script type="text/javascript">
opener.document.frm1.email.value="";
</script>
${email}는 이미 사용 중인 이메일입니다.
</c:if>
<c:if test="${result==0}">
${email}는 사용 가능한 이메일입니다.
<input type="button" value="사용" class="cancel" onclick="emailok()">
</c:if>
</form>

</body>
</html>