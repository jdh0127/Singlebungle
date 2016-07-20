
	
	 CREATE TABLE HONEY 
   (   "MID" NUMBER(38,0), 
   "TITLE" VARCHAR2(50 BYTE) , 
   "CONTENT" CLOB, 
   "TIME" varchar2(30) , 
   "HIT" NUMBER(38,0) , 
   "FAV" NUMBER(38,0) , 
   "NICK" VARCHAR2(50 BYTE) 
    );
	
   insert table honey values(honey_seq.nextval, '123', '123', '123', 0, 0, 'kg');
   create sequence honey_seq start with 1 increment by 1;
   select * from board21;
   
   drop sequence honey_seq;
	drop table honey;
	
	DELETE FROM member
     WHERE nick = 'qwer' ;
	
	select * from member;
	delete from honey where mid=21;
	
	select * from HONEY;
	
	create sequence honey_seq start with 1 increment by 1;
	
	alter table honey 
	modify ( 
		time varchar2(20) 
		);
	
	create table member(
	email varchar2(50),
	name varchar2(50) ,
	pwd varchar2(50) ,
	birth varchar2(50) ,
	phone varchar2(50) ,
	nick varchar2(50) ,
	primary key(nick)
	);
	
	drop table member;