package spring;

public class PagingCount {
   int shownpgs=5;      //하단에 표시되는 페이지의 개수
   int rcdsinone=7;      //각 페이지에 나오는 레코드의 개수
   int totalpage=0;
   
   public PagingCount() {
      super();
   }
   
   public PagingCount(int totrcds) {      // 하단에 표시될 총 페이지개수 설정
      super();
      this.totalpage = ( (totrcds-1) / rcdsinone ) +1;
   }
   
   public PagingCount(int totrcds, int shownpgs, int rcdsinone) {  // totrcds(레코드번호) 의 시작 게시물 번호, 링크번호 설정
      super();
      this.shownpgs = shownpgs;
      this.rcdsinone = rcdsinone;
      this.totalpage = ( (totrcds-1) / rcdsinone ) +1;   // totrcds(레코드번호)가 속해있는 페이지번호 구하기
   }
   
   // end 생성자
   
   
   public int countstartRcdNo(int page) {   //페이지의 시작 레코드번호 설정
      int startRcdNo = (page-1) * this.rcdsinone+1;
      return startRcdNo;
   }
   
   public String showPaging(int pagelink, String pagename) {   // 페이지번호와 이름을 받았을때 화면에 내용을 표시하는 메소드
      String str="";
      
      str="<table style=\"width: 550; border-style: none; text-align: center\">";
      str=str+"<tr><td style=\"text-align: center;\">";
      
      int bpage=0;   // 이전 10
      int bstartRcdNo=0;
      
      int cpage=0;   // 현재 페이지 카운트에 쓰임
      int cstartRcdNo=0;
      
      int vpage=0;   // 현재 페이지는 a태그 적용 안시킬 때
      int nstartRcdNo=0;
      
      int lstartRcdNo=0;
      
      // 1페이지일때와 아닐때의 '처음'버튼 활성/비활성화 표시
      if( pagelink != 1 ) {         // 페이지번호가 1페이지가 아니라면
         str=str+"[<a href="+ pagename +"?pagelink=1&startRcdNo=0><font style=\"color: red; font-size: 2;\">처음</font></a>]";
            // "" 안에 ""가 또 있다면 앞에 '\' 를 붙이거나, 홑따옴표 ( ' ) 를 준다.
      }else{                     // 페이지번호가 1페이지라면
         str=str+"[<font style=\"color: gray; font-size: 2\">처음</font>]";
      }
      
      // 이전 10 : pagelink가 10미만일때와 11이상일때
      bpage=pagelink - 10;            // 이전버튼 눌렀을때 현재 페이지 - 10
      bstartRcdNo=countstartRcdNo(bpage);      // 이전 10개의 페이지보기 버튼을 눌렀을 때 표시할 첫 레코드번호 리턴받기
      if(pagelink > shownpgs){   // '이전' 버튼 눌렀을 때 표시되는 페이지 숫자(pagelink)가 현재 설정된 하단의 페이지 개수(shownpgs)보다 크면  
         str=str+"[<a href=" + pagename + "?pagelink=" + bpage + "&startRcdNo=" + bstartRcdNo + "><font style=\"color: blue; font-size: 2;\">이전10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">이전 10</font>]";
      }
      
      // 현재 페이지는 a태그 적용 안시키기
      vpage=pagelink;      //현재페이지를 잠깐 다른변수의 아래에서 사용함
      pagelink=( (pagelink-1)/shownpgs ) * shownpgs + 1;      //화면에 보여질 시작 페이지 구하기 ex) 21 | 22 | 23 | ... | 30
      for( cpage=pagelink; cpage<pagelink+shownpgs; cpage++ ){
         if(cpage>totalpage){   // 페이지번호보다 현재 페이지번호가 더 크면
            break;               // 멈춰.
         }      
         
         cstartRcdNo=countstartRcdNo(cpage);  // 현재페이지의 첫 레코드번호 리턴받기
         
         if( cpage!= vpage ){
            str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + cstartRcdNo + "><font style=\"color: black; font-size: 2;\">"+ cpage +"</font></a>]";
         }else{
            str=str+"[<font style=\"color: red; font-size: 2\">"+cpage+"</font>]";
         } //end if
         
      } //end for
      
      // 다음10
         // for문을 빠져 나왔을 때 cpage의 값은 1만큼 더 증가되어있기 때문에 그냥 사용한다.
      nstartRcdNo=countstartRcdNo(cpage);      // cpage값(페이지)의 첫 레코드번호 리턴받기
      if( (totalpage-pagelink)>=shownpgs ){
         str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + nstartRcdNo + "><font style=\"color: blue; font-size: 2;\">다음10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">다음10</font>]";
      }
      
      //마지막페이지
      lstartRcdNo=countstartRcdNo(totalpage);      // 현 페이지번호에 대한 첫 레코드번호 리턴받기
      if(vpage!=totalpage){
         str=str+"[<a href=" + pagename + "?pagelink=" + totalpage + "&startRcdNo=" + lstartRcdNo + "><font style=\"color: red; font-size: 2;\">마지막</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">마지막</font>]";
      }
      
      str=str+"</td></tr></table>";
      
      return str;
   } //end String showPaging(int pagelink, String pagename)
   
   public String showSearchPaging(int pagelink, String pagename, String find, String findword, String url) {   // 페이지번호와 이름을 받았을때 화면에 내용을 표시하는 메소드
      String str="";
      
      str="<table style=\"width: 550; border-style: none; text-align: center\">";
      str=str+"<tr><td style=\"text-align: center;\">";
      
      int bpage=0;   // 이전 10
      int bstartRcdNo=0;
      
      int cpage=0;   // 현재 페이지 카운트에 쓰임
      int cstartRcdNo=0;
      
      int vpage=0;   // 현재 페이지는 a태그 적용 안시킬 때
      int nstartRcdNo=0;
      
      int lstartRcdNo=0;
      
      // 1페이지일때와 아닐때의 '처음'버튼 활성/비활성화 표시
      if( pagelink != 1 ) {         // 페이지번호가 1페이지가 아니라면
         str=str+"[<a href="+ pagename +"?pagelink=1&startRcdNo=0&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: red; font-size: 2;\">처음</font></a>]";
            // "" 안에 ""가 또 있다면 앞에 '\' 를 붙이거나, 홑따옴표 ( ' ) 를 준다.
      }else{                     // 페이지번호가 1페이지라면
         str=str+"[<font style=\"color: gray; font-size: 2\">처음</font>]";
      }
      
      // 이전 10 : pagelink가 10미만일때와 11이상일때
      bpage=pagelink - 10;            // 이전버튼 눌렀을때 현재 페이지 - 10
      bstartRcdNo=countstartRcdNo(bpage);      // 이전 10개의 페이지보기 버튼을 눌렀을 때 표시할 첫 레코드번호 리턴받기
      if(pagelink > shownpgs){      // '이전' 버튼 눌렀을 때 표시되는 페이지 숫자(pagelink)가 현재 설정된 하단의 페이지 개수(shownpgs)보다 크면  
         str=str+"[<a href=" + pagename + "?pagelink=" + bpage + "&startRcdNo=" + bstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: blue; font-size: 2;\">이전10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">이전 10</font>]";
      }
      
      // 현재 페이지는 a태그 적용 안시키기
      vpage=pagelink;      //현재페이지를 잠깐 다른변수의 아래에서 사용함
      pagelink=( (pagelink-1)/shownpgs ) * shownpgs + 1;      //화면에 보여질 시작 페이지 구하기 ex) 21 | 22 | 23 | ... | 30
      for( cpage=pagelink; cpage<pagelink+shownpgs; cpage++ ){
         if(cpage>totalpage){   // 페이지번호보다 현재 페이지번호가 더 크면
            break;               // 멈춰.
         }      
         
         cstartRcdNo=countstartRcdNo(cpage);  // 현재페이지의 첫 레코드번호 리턴받기
         
         if( cpage!= vpage ){
            str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + cstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: black; font-size: 2;\">"+ cpage +"</font></a>]";
         }else{
            str=str+"[<font style=\"color: red; font-size: 2\">"+cpage+"</font>]";
         } //end if
         
      } //end for
      
      // 다음10
         // for문을 빠져 나왔을 때 cpage의 값은 1만큼 더 증가되어있기 때문에 그냥 사용한다.
      nstartRcdNo=countstartRcdNo(cpage);      // cpage값(페이지)의 첫 레코드번호 리턴받기
      if( (totalpage-pagelink)>=shownpgs ){
         str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + nstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: blue; font-size: 2;\">다음10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">다음10</font>]";
      }
      
      //마지막페이지
      lstartRcdNo=countstartRcdNo(totalpage);      // 현 페이지번호에 대한 첫 레코드번호 리턴받기
      if(vpage!=totalpage){
         str=str+"[<a href=" + pagename + "?pagelink=" + totalpage + "&startRcdNo=" + lstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: red; font-size: 2;\">마지막</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">마지막</font>]";
      }
      
      str=str+"</td></tr></table>";
      
      return str;
   } //end String showSearchPaging(int pagelink, String pagename)
}