create table message(
	mnum int primary key auto_increment,
	writer varchar(15) not null,
	title varchar(15) not null,
	content varchar(100) not null,
	wdate date,
    foreign key (writer) references member(bid) ON DELETE CASCADE
);

select * from message;
insert into message(writer, title, content, wdate) values('hong','제목2','내용2',curdate());
/* 샘플데이터 : 일반적으로 서비스 될때에는 샘플데이터 有! */

create table member(
 bnum int primary key auto_increment,
 bname varchar(15) not null,
 bid varchar(15) unique not null,
 bpw varchar(15) not null
 );
 
 drop table member;
 drop table message;
 
 delete from member where bid = 'hong';
 
 select * from message where content like '%내용%'; 
 
 insert into member(bname, bid,bpw) values('홍길동', 'hong', '1234');
 insert into member(bname, bid,bpw) values('아무무', 'amu', '1234');
 insert into member(bname, bid,bpw) values('가렌', 'garen', '1234');
 
 delete from member where bid = 'hong';
 
 select * from member where bid = 'hong' and bpw = '1234';
 
 select * from member
 
 select * from member;