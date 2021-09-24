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


insert into member values('timo','Æ¼¸ð','1234', sysdate);
insert into message values(1,'timo','±ÛÀÛ¼º',1,2,sysdate);
insert into reply values(1,1,'timo',sysdate,'´ñ±Û1');
insert into reply values(2,1,'timo',sysdate,'´ñ±Û2');
insert into message values(2,'timo','±ÛÀÛ¼º2',2,3,sysdate);
insert into reply values(3,2,'timo',sysdate,'´ñ±Û1');
insert into reply values(4,2,'timo',sysdate,'´ñ±Û2');
insert into reply values(5,2,'timo',sysdate,'´ñ±Û3');
insert into message values(3,'timo','È®ÀÎ',2,0,sysdate);
select * from message;
select * from REPLY;

select * from reply;



insert into message(mid, id, msg, mdate) values(nvl((select max(mid) from message)),'asdf','asdfasdf',sysdate)";