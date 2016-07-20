create table todaysell(
mid int NOT NULL auto_increment primary key,
title VARCHAR(50) NOT NULL, 
nick VARCHAR(50) NOT NULL, 
content text NOT NULL, 
hit int not null, 
loc varchar(30) not null,
fav int NOT NULL, 
time VARCHAR(30) not null,
FOREIGN KEY (nick) REFERENCES member (nick)
) engine=InnoDB character set=utf8;

set @mid:=0;

select @rownum:=@rownum+1
from todaysell;

SELECT
    @ROWNUM := @ROWNUM + 1 AS ROWNUM,
    todaysell.* 
FROM
    todaysell,
    (SELECT @ROWNUM := 0) R;

    
SELECT
    A.*
FROM
(
    SELECT
        @ROWNUM := @ROWNUM + 1 AS ROWNUM,
        todaysell.* 
    FROM
        todaysell,
       (SELECT @ROWNUM := 0) R
) A
WHERE
    A.ROWNUM < 2;    

    select * from 
    (
    	select @rownum:= @rownum + 1 as rnum, 
    	b.* from (select * from hobby order by mid desc) b
    ) a 
    where a.rnum>=0 and a.rnum<3;
    
    
select * from todaysell;



	CREATE TABLE honny 
   (   MID int NOT NULL AUTO_INCREMENT , 
   TITLE VARCHAR(50) , 
   NICK VARCHAR(50) , 
   CONTENT text, 
   HIT int, 
   FAV int, 
   TIME varchar(30),
   primary key(mid)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
  
    CREATE TABLE spring4fs.honny 
   (   MID int NOT NULL AUTO_INCREMENT , 
   TITLE VARCHAR(50) , 
   NICK VARCHAR(50) , 
   CONTENT text, 
   HIT int, 
   FAV int, 
   TIME varchar(30),
   primary key(mid)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  commit;
  
    drop table honey;
    
	alter table honey modify mid int
   insert table honey values('1', '123', '123', '123', 0, 0, 'kg');
   create sequence honey_seq start with 1 increment by 1;
   select * from board21;
   
   drop sequence honey_seq;
	drop table honey;
	
	DELETE FROM member
     WHERE nick = 'qwer' ;
	
	select * from member;
	delete from honey where mid=21;
	
	
	select * from HONEY;
	show tables;
	drop table honny;
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
	locCheck"), rs.getInt("point
	alter table member add (point int);
	alter table honey
	modify content text;
	alter table 
	
	drop table member;