$(function(){
	var oEditors = [];

	
	var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content",
		sSkinURI: "../S.E/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
			fOnBeforeUnload : function(){
				//alert("완료!");
			}
		}
	});
	$("#savebutton").click(function(){
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

		var title = $("input[name='title']").val();
		if(!title){
			alert("제목을 입력하세요.");
			$("input[name='title']").focus();
			return false;
			
		}
		//Editor 모드 조건 검사
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

		$("#frm").submit();
		
	});

});
