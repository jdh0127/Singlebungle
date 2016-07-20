<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Daum에디터 - 이미지 첨부</title> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="../../js/popup.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="../../css/popup.css" type="text/css"  charset="utf-8"/>
<script type="text/javascript">
// <![CDATA[
	
	var fileUrl;
	var fileName;
    $(function(){
 		
 		//alert("이거야");
 		
		$("#file").on("change", function() {
			/* alert("sdfsdf"); */
			var reader = new FileReader();	
			reader.fileName = file.name;
			reader.onload=imageLoad;
			reader.readAsDataURL(this.files[0]);
			
			//alert(reader.result);
		});
		
		
		/* 화면 img 태그에 이미지 삽입 */
		function imageLoad(e) {
			fileUrl = e.target.result;
			fileName = e.target.fileName;
			/* alert(fileUrl); */
			alert(fileName);
  			$("#fileImg").attr("src", e.target.result);
		}
    });      
            
	function done() {
		if (typeof(execAttach) == 'undefined') { //Virtual Function
	        return;
	    }
		
		 var _mockdata = {
			 'imageurl': fileUrl, 
			 'filename': fileName, 
			 'filesize': 640,
			 'imagealign': 'C',
		     'originalurl': fileUrl, 
			 'thumburl': fileUrl,
			 'width' : 250
			 /* 'height' : 300 */
		}; 
		execAttach(_mockdata);
		closeWindow();
	}
	
	function changeValue(object){
		var value=$(object).val();
		if(value !='') //값이 안 비었다면 
			{
				//확장자 체크 (4.11.효진)
				var ext = value.split('.').pop().toLowerCase();
				
				if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'bmp', 'tif']) == -1)
					{
						alert('gif,png,jpg,jpeg,bmp,tif 파일만 업로드 할 수 있습니다.');
						$(object).val('');
						$('#image_name').val('');
						return;
					}
				
					/* //파일명만. (4.11.효진)
					var array = value.split('\\');
					var a = 0;
					
					if(array.length > 1){
						document.getElementById('image_name').value=array[array.length-1];
					}else{
						document.getElementById('image_name').value=value;
					} */
				}
			}

	function initUploader(){
	    var _opener = PopupUtil.getOpener();
	    if (!_opener) {
	        alert('잘못된 경로로 접근하셨습니다.');
	        return;
	    }
	    
	    var _attacher = getAttacher('image', _opener);
	    registerAction(_attacher);
	}
// ]]>
</script>
</head>
<body onload="initUploader();">
<form name="myform" action target="upload_frame" method="post" enctype="multipart/form-data" style="margin:0px;">
<div class="wrapper">
	<div class="header">
		<h1>사진 첨부</h1>
	</div>	
	<div class="body">
		<dl class="alert">
		    <dt>
		    	<div class="desc">gif,png,jpg,jpeg,bmp,tif 파일만 업로드 할 수 있습니다.</div>
		    </dt>
		    <!-- <div class="p_phot01 cb">
		    	<ul>
		    		<input type="file" name="file_name" id="file_name" size="80" onchange="return onUpload();" style="width:100%; height"></input>
		    	</ul>
		    </div> -->
		    <form action="#" method="post" enctype="multipart/form-data">
		    <input onchange="javascript:changeValue(this);" type="file" id="file" name="file"></input>
		  	<!-- mh -->
   			<img id="fileImg" width="120px" src="" height="150px"/>
		    </form>
		</dl>
	</div>
	<div class="footer">
		<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
		<ul>
			<li class="submit"><a href="#" onclick="done();" title="등록" class="btnlink">등록</a> </li>
			<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
		</ul>
	</div>
</div>
</body>
</html>