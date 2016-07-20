function boardCheck(){
	if(document.frm.title.value.length == 0){
		alert("제목을 써주세요.");
		frm.title.focus();
		return false;
	}
	
	if(document.frm.description.value.length == 0){
		alert("내용을 써주세요.");
		frm.description.focus();
		return false;
	}
	
	return true;
}