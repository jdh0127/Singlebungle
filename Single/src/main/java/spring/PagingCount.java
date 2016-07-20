package spring;

public class PagingCount {
   int shownpgs=5;      //�ϴܿ� ǥ�õǴ� �������� ����
   int rcdsinone=7;      //�� �������� ������ ���ڵ��� ����
   int totalpage=0;
   
   public PagingCount() {
      super();
   }
   
   public PagingCount(int totrcds) {      // �ϴܿ� ǥ�õ� �� ���������� ����
      super();
      this.totalpage = ( (totrcds-1) / rcdsinone ) +1;
   }
   
   public PagingCount(int totrcds, int shownpgs, int rcdsinone) {  // totrcds(���ڵ��ȣ) �� ���� �Խù� ��ȣ, ��ũ��ȣ ����
      super();
      this.shownpgs = shownpgs;
      this.rcdsinone = rcdsinone;
      this.totalpage = ( (totrcds-1) / rcdsinone ) +1;   // totrcds(���ڵ��ȣ)�� �����ִ� ��������ȣ ���ϱ�
   }
   
   // end ������
   
   
   public int countstartRcdNo(int page) {   //�������� ���� ���ڵ��ȣ ����
      int startRcdNo = (page-1) * this.rcdsinone+1;
      return startRcdNo;
   }
   
   public String showPaging(int pagelink, String pagename) {   // ��������ȣ�� �̸��� �޾����� ȭ�鿡 ������ ǥ���ϴ� �޼ҵ�
      String str="";
      
      str="<table style=\"width: 550; border-style: none; text-align: center\">";
      str=str+"<tr><td style=\"text-align: center;\">";
      
      int bpage=0;   // ���� 10
      int bstartRcdNo=0;
      
      int cpage=0;   // ���� ������ ī��Ʈ�� ����
      int cstartRcdNo=0;
      
      int vpage=0;   // ���� �������� a�±� ���� �Ƚ�ų ��
      int nstartRcdNo=0;
      
      int lstartRcdNo=0;
      
      // 1�������϶��� �ƴҶ��� 'ó��'��ư Ȱ��/��Ȱ��ȭ ǥ��
      if( pagelink != 1 ) {         // ��������ȣ�� 1�������� �ƴ϶��
         str=str+"[<a href="+ pagename +"?pagelink=1&startRcdNo=0><font style=\"color: red; font-size: 2;\">ó��</font></a>]";
            // "" �ȿ� ""�� �� �ִٸ� �տ� '\' �� ���̰ų�, Ȭ����ǥ ( ' ) �� �ش�.
      }else{                     // ��������ȣ�� 1���������
         str=str+"[<font style=\"color: gray; font-size: 2\">ó��</font>]";
      }
      
      // ���� 10 : pagelink�� 10�̸��϶��� 11�̻��϶�
      bpage=pagelink - 10;            // ������ư �������� ���� ������ - 10
      bstartRcdNo=countstartRcdNo(bpage);      // ���� 10���� ���������� ��ư�� ������ �� ǥ���� ù ���ڵ��ȣ ���Ϲޱ�
      if(pagelink > shownpgs){   // '����' ��ư ������ �� ǥ�õǴ� ������ ����(pagelink)�� ���� ������ �ϴ��� ������ ����(shownpgs)���� ũ��  
         str=str+"[<a href=" + pagename + "?pagelink=" + bpage + "&startRcdNo=" + bstartRcdNo + "><font style=\"color: blue; font-size: 2;\">����10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">���� 10</font>]";
      }
      
      // ���� �������� a�±� ���� �Ƚ�Ű��
      vpage=pagelink;      //������������ ��� �ٸ������� �Ʒ����� �����
      pagelink=( (pagelink-1)/shownpgs ) * shownpgs + 1;      //ȭ�鿡 ������ ���� ������ ���ϱ� ex) 21 | 22 | 23 | ... | 30
      for( cpage=pagelink; cpage<pagelink+shownpgs; cpage++ ){
         if(cpage>totalpage){   // ��������ȣ���� ���� ��������ȣ�� �� ũ��
            break;               // ����.
         }      
         
         cstartRcdNo=countstartRcdNo(cpage);  // ������������ ù ���ڵ��ȣ ���Ϲޱ�
         
         if( cpage!= vpage ){
            str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + cstartRcdNo + "><font style=\"color: black; font-size: 2;\">"+ cpage +"</font></a>]";
         }else{
            str=str+"[<font style=\"color: red; font-size: 2\">"+cpage+"</font>]";
         } //end if
         
      } //end for
      
      // ����10
         // for���� ���� ������ �� cpage�� ���� 1��ŭ �� �����Ǿ��ֱ� ������ �׳� ����Ѵ�.
      nstartRcdNo=countstartRcdNo(cpage);      // cpage��(������)�� ù ���ڵ��ȣ ���Ϲޱ�
      if( (totalpage-pagelink)>=shownpgs ){
         str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + nstartRcdNo + "><font style=\"color: blue; font-size: 2;\">����10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">����10</font>]";
      }
      
      //������������
      lstartRcdNo=countstartRcdNo(totalpage);      // �� ��������ȣ�� ���� ù ���ڵ��ȣ ���Ϲޱ�
      if(vpage!=totalpage){
         str=str+"[<a href=" + pagename + "?pagelink=" + totalpage + "&startRcdNo=" + lstartRcdNo + "><font style=\"color: red; font-size: 2;\">������</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">������</font>]";
      }
      
      str=str+"</td></tr></table>";
      
      return str;
   } //end String showPaging(int pagelink, String pagename)
   
   public String showSearchPaging(int pagelink, String pagename, String find, String findword, String url) {   // ��������ȣ�� �̸��� �޾����� ȭ�鿡 ������ ǥ���ϴ� �޼ҵ�
      String str="";
      
      str="<table style=\"width: 550; border-style: none; text-align: center\">";
      str=str+"<tr><td style=\"text-align: center;\">";
      
      int bpage=0;   // ���� 10
      int bstartRcdNo=0;
      
      int cpage=0;   // ���� ������ ī��Ʈ�� ����
      int cstartRcdNo=0;
      
      int vpage=0;   // ���� �������� a�±� ���� �Ƚ�ų ��
      int nstartRcdNo=0;
      
      int lstartRcdNo=0;
      
      // 1�������϶��� �ƴҶ��� 'ó��'��ư Ȱ��/��Ȱ��ȭ ǥ��
      if( pagelink != 1 ) {         // ��������ȣ�� 1�������� �ƴ϶��
         str=str+"[<a href="+ pagename +"?pagelink=1&startRcdNo=0&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: red; font-size: 2;\">ó��</font></a>]";
            // "" �ȿ� ""�� �� �ִٸ� �տ� '\' �� ���̰ų�, Ȭ����ǥ ( ' ) �� �ش�.
      }else{                     // ��������ȣ�� 1���������
         str=str+"[<font style=\"color: gray; font-size: 2\">ó��</font>]";
      }
      
      // ���� 10 : pagelink�� 10�̸��϶��� 11�̻��϶�
      bpage=pagelink - 10;            // ������ư �������� ���� ������ - 10
      bstartRcdNo=countstartRcdNo(bpage);      // ���� 10���� ���������� ��ư�� ������ �� ǥ���� ù ���ڵ��ȣ ���Ϲޱ�
      if(pagelink > shownpgs){      // '����' ��ư ������ �� ǥ�õǴ� ������ ����(pagelink)�� ���� ������ �ϴ��� ������ ����(shownpgs)���� ũ��  
         str=str+"[<a href=" + pagename + "?pagelink=" + bpage + "&startRcdNo=" + bstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: blue; font-size: 2;\">����10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">���� 10</font>]";
      }
      
      // ���� �������� a�±� ���� �Ƚ�Ű��
      vpage=pagelink;      //������������ ��� �ٸ������� �Ʒ����� �����
      pagelink=( (pagelink-1)/shownpgs ) * shownpgs + 1;      //ȭ�鿡 ������ ���� ������ ���ϱ� ex) 21 | 22 | 23 | ... | 30
      for( cpage=pagelink; cpage<pagelink+shownpgs; cpage++ ){
         if(cpage>totalpage){   // ��������ȣ���� ���� ��������ȣ�� �� ũ��
            break;               // ����.
         }      
         
         cstartRcdNo=countstartRcdNo(cpage);  // ������������ ù ���ڵ��ȣ ���Ϲޱ�
         
         if( cpage!= vpage ){
            str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + cstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: black; font-size: 2;\">"+ cpage +"</font></a>]";
         }else{
            str=str+"[<font style=\"color: red; font-size: 2\">"+cpage+"</font>]";
         } //end if
         
      } //end for
      
      // ����10
         // for���� ���� ������ �� cpage�� ���� 1��ŭ �� �����Ǿ��ֱ� ������ �׳� ����Ѵ�.
      nstartRcdNo=countstartRcdNo(cpage);      // cpage��(������)�� ù ���ڵ��ȣ ���Ϲޱ�
      if( (totalpage-pagelink)>=shownpgs ){
         str=str+"[<a href=" + pagename + "?pagelink=" + cpage + "&startRcdNo=" + nstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: blue; font-size: 2;\">����10</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">����10</font>]";
      }
      
      //������������
      lstartRcdNo=countstartRcdNo(totalpage);      // �� ��������ȣ�� ���� ù ���ڵ��ȣ ���Ϲޱ�
      if(vpage!=totalpage){
         str=str+"[<a href=" + pagename + "?pagelink=" + totalpage + "&startRcdNo=" + lstartRcdNo + "&find="+ find +"&findword="+ findword+ "&page="+url+"><font style=\"color: red; font-size: 2;\">������</font></a>]";
      }else{
         str=str+"[<font style=\"color: gray; font-size: 2\">������</font>]";
      }
      
      str=str+"</td></tr></table>";
      
      return str;
   } //end String showSearchPaging(int pagelink, String pagename)
}