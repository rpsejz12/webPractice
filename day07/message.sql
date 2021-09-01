create table message(
	mnum int primary key auto_increment,
	writer varchar(15) not null,
	title varchar(15) not null,
	content varchar(100) not null,
	wdate date,
    foreign key (writer) references member(bid) ON DELETE CASCADE
);

select * from message;
insert into message(writer, title, content, wdate) values('hong','����2','����2',curdate());
/* ���õ����� : �Ϲ������� ���� �ɶ����� ���õ����� ��! */

create table member(
 bnum int primary key auto_increment,
 bname varchar(15) not null,
 bid varchar(15) unique not null,
 bpw varchar(15) not null
 );
 
 drop table member;
 drop table message;
 
 delete from member where bid = 'hong';
 
 select * from message where content like '%����%'; 
 
 insert into member(bname, bid,bpw) values('ȫ�浿', 'hong', '1234');
 insert into member(bname, bid,bpw) values('�ƹ���', 'amu', '1234');
 insert into member(bname, bid,bpw) values('����', 'garen', '1234');
 
 delete from member where bid = 'hong';
 
 select * from member where bid = 'hong' and bpw = '1234';
 
 select * from member
 
 select * from member;