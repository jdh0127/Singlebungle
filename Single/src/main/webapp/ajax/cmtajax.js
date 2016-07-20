
	
function cmajax(){
	if(ddd.list.length==0){
		$(".CommentList").html("<tr><td>등록된 글이 없습니다.</td></tr>");
		$(".opinion_box").html("<textarea id='content' rows='3' cols='50' placeholder='의견을 달아주세요'></textarea>").append("<input id='button' name='"+loginuser+"' type='button' value='의견달기'>");
		}
			$(".CommentList").html("<tr><th>작성자</th><th>내용</th><th>게시일</th></tr>");
			var commentArray = new Array();
				for(var i=0 ; i < ddd.list.length; i++){
					var checkuser = (ddd.list[i].writer == loginuser) || (loginuser == "관리자");
					if(eval(checkuser)){
						commentArray[i] = $("<tr num='"+i+"'><td width='15%' style='padding-bottom:10px'>"+ddd.list[i].writer+"</td><td width='65%'>"+ddd.list[i].content+"</td><td width='20%'>"+ddd.list[i].date+"<a href='javascript:cmtupdateform("+i+","+ddd.list[i].num+")'><img class='update' alt='' src='../img/pencil.png'></a><a href='javascript:cmtdelete("+ddd.list[i].num+")'><img class='delete' alt='' src='../img/delete.png'></a></td></tr>");
						$(".CommentList").append(commentArray[i]);
							}else{
								commentArray[i] = $("<tr num='"+i+"'><td width='15%' style='padding-bottom:10px'>"+ddd.list[i].writer+"</td><td width='65%'>"+ddd.list[i].content+"</td><td width='20%'>"+ddd.list[i].date+"</td></tr>");
								$(".CommentList").append(commentArray[i]);
							}
					}
			
		$(".opinion_box").html("<textarea id='content' name='content' rows='3' cols='50' placeholder='의견을 달아주세요'></textarea>").append("<input id='button' class='commentRegister' onclick='cmtinsert()' type='button' value='의견달기'>");
}

//의견보기 버튼 눌렸을 때 댓글 리스트 뿌리기
$(function() {
	$("#mybtn").click(function() {
		$.ajax({
				url : "/app/"+board+"/view/comment?ref="+ref,
				type:"get",
				datatype:"json",
				success:function(data){
					ddd =data;
					cmajax();
			}, error : function(xhr, textStatus, errorThrown){
				$("div").html("<div>"+textStatus+"(HTTP-"+xhr.status+" / "+errorThrown+")</div>");
				
				}
			});

	});
	
		});	
	

	//댓글 입력
	function cmtinsert(){
var content = $(".opinion_box>textarea").val();
		$.ajax({
			url : "/app/"+board+"/view/comment_insert?writer="+loginuser+"&ref="+ref+"&content="+content,
			type:"get",
			datatype:"json",
			success:function(data){
				ddd =data;
				cmajax();
		}, error : function(xhr, textStatus, errorThrown){
			$("div").html("<div>"+textStatus+"(HTTP-"+xhr.status+" / "+errorThrown+")</div>");
			
			}
		});
	}
	
	//댓글 삭제
	function cmtdelete(num){
				$.ajax({
					url : "/app/"+board+"/view/comment_delete?num="+num+"&ref="+ref,
					type:"get",
					datatype:"json",
					success:function(data){
						ddd =data;
						cmajax();
				}, error : function(xhr, textStatus, errorThrown){
					$("div").html("<div>"+textStatus+"(HTTP-"+xhr.status+" / "+errorThrown+")</div>");
					
					}
				});
			}
	
	//댓글 수정폼
 	function cmtupdateform(i, num){
		var c = i+1;
		var cmt = $(".CommentList tr:eq("+c+")>td:eq(1)");
		var cmtimg = $(".CommentList tr:eq("+c+")>td:eq(2)");
		var bfcmt = $(".CommentList tr:eq("+c+")>td:eq(1)").html();
		var input = $("<input type='text' id='modifycomment' class='newComment' size='28' value='"+bfcmt+"'/>");
		var btn = $("<input type='image' src='../img/check.png' class='checkupdate' name='submit' onclick='cmtupdate("+num+","+c+")''>"); 
		/* var btn1 = $("<a href='javascript:cmtupdate("+num+", modifycomment)'><img class='checkupdate' alt='' src='../img/check.png'></a>") */
		cmt.html("").append(input);
		cmtimg.html("").append(btn);
	}
	//댓글 수정하기
	function cmtupdate(num,c){
		var cmt = $(".CommentList tr:eq("+c+")>td:eq(1)").children(".newComment").val();
 		$.ajax({
			url : "/app/"+board+"/view/comment_update?num="+num+"&cmt="+cmt+"&ref="+ref,
			type:"get",
			datatype:"json",
			success:function(data){
				ddd =data;
				cmajax();
		}, error : function(xhr, textStatus, errorThrown){
			$("div").html("<div>"+textStatus+"(HTTP-"+xhr.status+" / "+errorThrown+")</div>");
			
			}
		});
	}
	//좋아요 버튼
	function favcount(){
		var fav = $('.view tr:eq(4)>td:eq(0)').children(".dynamicfav");
 		 $.ajax({
			url : "/app/"+board+"/view/like?mid="+ref+"&user="+loginuser,
			type:"get",
			datatype:"json",
			success:function(data){
				ddd = data;
				fav.html("").append(ddd.list.fav);
			}, error : function(xhr, textStatus, errorThrown){
			$("div").html("<div>"+textStatus+"(HTTP-"+xhr.status+" / "+errorThrown+")</div>");
			
			}
		});
	}
