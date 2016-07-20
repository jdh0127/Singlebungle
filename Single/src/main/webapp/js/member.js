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
	 
}


function emailCheck(){
	if(document.frm1.email.value == ""){
		alert('이메일을 입력하여 주십시오.');
		document.frm.email.focus();
		return;
	}
	var url = "Emailcheck?email=" + document.frm1.email.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
	}

function emailok(){
	opener.frm1.email.value="${email}";
	opener.frm1.reemail.value="${email}";
	self.close();
}

function nickCheck(){
	if(document.frm1.nick.value == ""){
		alert('닉네임을 입력하여 주십시오.');
		document.frm.nick.focus();
		return;
	}
	var url = "Nickcheck?nick=" + document.frm1.nick.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
	}

function nickok(){
	opener.frm1.nick.value="${nick}";
	opener.frm1.renick.value="${nick}";
	self.close();
}

function joinCheck(){
	if(document.frm1.email.value.length == 0){
		alert("이메일을 써주세요.");
		frm1.email.focus();
		return false;
	}
	if(document.frm1.reemail.value.length == 0){
		alert("중복 체크를 하지 않았습니다.");
		frm1.email.focus();
		return false;
	}
	if(document.frm1.name.value.length == 0){
		alert("이름을 써주세요.");
		frm1.name.focus();
		return false;
	}
	if(document.frm1.nick.value.length == 0){
		alert("닉네임을 써주세요.");
		frm1.nick.focus();
		return false;
	}
	if(document.frm1.renick.value.length == 0){
		alert("중복 체크를 하지 않았습니다.");
		frm1.nick.focus();
		return false;
	}
	if(document.frm1.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm1.pwd.focus();
		return false;
	}
	if(document.frm1.pwd.value != document.frm1.pwdcheck.value){
		alert("암호가 일치하지 않습니다.");
		frm1.pwdcheck.focus();
		return false;
	}
	if(document.frm1.birth.value.length == 0){
		alert("생년월일을 써주세요.");
		frm1.birth.focus();
		return false;
	}
	if(document.frm1.birth.value.length < 6){
		alert("생년월일을 정확하게 입력해주세요.");
		frm1.birth.focus();
		return false;
	}
	if(document.frm1.phone.value.length == 0){
		alert("전화번호를 써주세요.");
		frm1.phone.focus();
		return false;
	}
	
	return true;
}



  function out(){
      
      var result = confirm("로그아웃 하시겠습니까?");
      if(result){
    	  location.href='/app/logout'; 
      } 
      else{
    	  
    	  location.href='/app/main'; 
      }
  }

  function out2(){
	  
	  var result = confirm("로그아웃 하시겠습니까?");
	  if(result){
		  location.href='/app/logout'; 
	  } 
	  else{
		  return false; 
	  }
  }
  


