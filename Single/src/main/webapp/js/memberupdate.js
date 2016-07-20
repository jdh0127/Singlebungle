function loginCheck(){
	if(document.frm.email.value.length == 0){
		alert("이메일을 써주세요");
		frm.email.focus();
		return false;
	}
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	/*return true;*/
	/*document.frm.submit();*/
}

function emailCheck(){
	if(document.frm.email.value == ""){
		alert('이메일을 입력하여 주십시오.');
		document.frm.email.focus();
		return;
	}
	var url = "emailCheck.do?email=" + document.frm.email.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
	}

function emailok(){
	opener.frm.email.value="${email}";
	opener.frm.reemail.value="${email}";
	self.close();
}

function nickCheck(){
	if(document.frm.nick.value == ""){
		alert('닉네임을 입력하여 주십시오.');
		document.frm.nick.focus();
		return;
	}
	var url = "nickCheck.do?nick=" + document.frm.nick.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
	}

function nickok(){
	opener.frm.nick.value="${nick}";
	opener.frm.renick.value="${nick}";
	self.close();
}

function joinCheck(){
	if(document.frm.name.value.length == 0){
		alert("이름을 써주세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value != document.frm.pwdcheck.value){
		alert("암호가 일치하지 않습니다.");
		frm.pwdcheck.focus();
		return false;
	}
	if(document.frm.birth.value.length == 0){
		alert("생년월일을 써주세요.");
		frm.birth.focus();
		return false;
	}
	if(document.frm.phone.value.length == 0){
		alert("전화번호를 써주세요.");
		frm.phone.focus();
		return false;
	}
	
	return true;
}