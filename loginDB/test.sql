create table member(
num number primary key,
name varchar(20),
userID varchar(20),
userPW varchar(20)
);

insert into member values('ȫ�浿','hong','1234');
select * from MEMBER;

create table product(
num number primary key,
name varchar(20),
price varchar(20)
);

drop table member;