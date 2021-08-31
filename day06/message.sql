create table message(
	mnum int primary key auto_increment,
	writer varchar(15) not null,
	title varchar(15) not null,
	content varchar(100) not null,
	wdate date
);

select * from message;
insert into message values(1,'kim','제목','내용',sysdate);
insert into message values(2,'kim2','제목2','내용2',sysdate);
insert into message(writer, title, content, wdate) values('kim2','제목2','내용2',curdate());
/* 샘플데이터 : 일반적으로 서비스 될때에는 샘플데이터 有! */

create table member(
 bnum int primary key auto_increment,
 bname varchar(15) not null,
 bid varchar(15) not null,
 bpw varchar(15) not null
 );
 
 insert into member(bname, bid,bpw) values('홍길동', 'hong', '1234');
 
 select * from member where bid = 'hong' and bpw = '1234';
 
 select * from member
 
 select * from member;