create table member(
    id varchar(15) primary key,
    name varchar(15),
    passwd varchar(10),
    mdate date
);

drop table member;
create table message(
   	mid int primary key,
    id varchar(15),
    msg varchar(100),
    favcount int default 0,
    replycount int default 0,
    mdate date
);

drop table message;

create table reply(
   	rid int primary key,
    mid int,
    id varchar(15),
    rdate date,
    rmsg varchar(50),
    constraint msgrp foreign key (mid) references message (mid) on delete cascade
);

drop table reply;


select * from MESSAGE;


insert into member values('timo','Ƽ��','1234', sysdate);
insert into message values(1,'timo','���ۼ�',1,2,sysdate);
insert into reply values(1,1,'timo',sysdate,'���1');
insert into reply values(2,1,'timo',sysdate,'���2');
insert into message values(2,'timo','���ۼ�2',2,3,sysdate);
insert into reply values(3,2,'timo',sysdate,'���1');
insert into reply values(4,2,'timo',sysdate,'���2');
insert into reply values(5,2,'timo',sysdate,'���3');
insert into message values(3,'timo','Ȯ��',2,0,sysdate);
select * from message;
select * from REPLY;

select * from reply;



insert into message(mid, id, msg, mdate) values(nvl((select max(mid) from message)),'asdf','asdfasdf',sysdate)";