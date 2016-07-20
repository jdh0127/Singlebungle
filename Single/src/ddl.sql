create user 'spring4'@'localhost' identified by 'spring4';

create database spring4fs character set=utf8;

grant all privileges on spring4fs.* to 'spring4'@'localhost';

create table spring4fs.MEMBER(
ID int auto_increment primary key,
EMAIL varchar(255),
PASSWORD varchar(100),
name varchar(100),
regdate datetime,
unique key(email)
) engine=InnoDB character set=utf8;

drop table member;
commit;
insert into member(email, password, name, regdate) values ('madvirus@madvirus.net', '1234', 'cbk', now());
류희영
select * from member;

create table member(
NICK VARCHAR(50) NOT NULL, 
EMAIL VARCHAR(50) NOT NULL, 
NAME VARCHAR(50) NOT NULL, 
PWD VARCHAR(50) NOT NULL, 
BIRTH VARCHAR(50), 
PHONE VARCHAR(50) NOT NULL, 
LOC VARCHAR(30), 
unique key(nick)
) engine=InnoDB character set=utf8;
alter table member add(admin int);

alter table member add(point int);

insert into member values('aa', 'aa@naver.com', 'aa', 'aa', 'aa', 'aa', 'aa', 0);
delete from member;
commit;

delete from  pla_board;
delete from  pla_comment;
delete from  res_board;
delete from  sale_board;
delete from  saleshare_board;
delete from  todaybuy;
delete from  todayfree;
delete from  todaysell;
select * from member;
update member set admin = 1 where name = 'admin';

create table honey(
mid int NOT NULL auto_increment primary key,
title VARCHAR(50) NOT NULL, 
nick VARCHAR(50) NOT NULL, 
content blob NOT NULL, 
hit int not null, 
fav int NOT NULL, 
time VARCHAR(30) not null,
FOREIGN KEY (nick) REFERENCES member (nick)
) engine=InnoDB character set=utf8;

select * from member;

insert into honey (title,nick,content,hit,fav,time) values('�Ф�','bb','�Ф�',0,0,'16-05-15');

alter table honey modify content text;
alter table member 
create table res_board(
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

select * from res_board;

create table pla_board(
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

create table sale_board(
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

create table saleshare_board(
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

create table interior_board(
mid int NOT NULL auto_increment primary key,
title VARCHAR(50) NOT NULL, 
nick VARCHAR(50) NOT NULL, 
content text NOT NULL, 
hit int not null, 
fav int NOT NULL, 
time VARCHAR(30) not null,
FOREIGN KEY (nick) REFERENCES member (nick)
) engine=InnoDB character set=utf8;

create table homemake_board(
mid int NOT NULL auto_increment primary key,
title VARCHAR(50) NOT NULL, 
nick VARCHAR(50) NOT NULL, 
content text NOT NULL, 
hit int not null, 
fav int NOT NULL, 
time VARCHAR(30) not null,
FOREIGN KEY (nick) REFERENCES member (nick)
) engine=InnoDB character set=utf8;


CREATE TABLE hobby 
   (   MID int NOT NULL AUTO_INCREMENT , 
   TITLE VARCHAR(50) , 
   NICK VARCHAR(50) ,
   SORT VARCHAR(30),
   CONTENT text, 
   HIT int, 
   FAV int, 
   TIME varchar(30),
   primary key(mid)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  create table todayfree(
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

select * from todaysell;

select * from hobby;

show tables;
select * from comment;
pla_comment
select * from todayfree order by mid desc;
delete from member;

select * from member;

select * from sale_board where loc like '%���%' order by mid desc;
show create table honey;

ALTER TABLE res_board Drop FOREIGN KEY res_board_ibfk_1; 
select * from member;

ALTER TABLE res_board ADD CONSTRAINT `rsfk_nick` FOREIGN KEY (`nick`) REFERENCES `member` (`nick`) ON DELETE CASCADE ON UPDATE CASCADE;
update member set admin=1 where email="admin";
