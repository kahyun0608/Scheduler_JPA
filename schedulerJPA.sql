-- 유저 테이블 생성

create table 'user'
(
    id         bigint auto_increment primary key,
    created_at datetime(6)  null default current_timestamp,
    updated_at datetime(6)  null default current_timestamp on update current_timestamp,
    password   varchar(20)  not null,
    username   varchar(20)  not null,
    email      varchar(255) not null,
    unique (email)
);

-- 유저 생성

insert into user (username, email, password)
values ('name' 'email@XXX.com', '1234');

-- 유저 조회
select username, email
from user
where id = 1;

-- 유저 삭제
Delete from user
where id = 1;

-- 일정 테이블 생성
create table 'schedule'
(
    id         bigint auto_increment primary key,
    created_at datetime(6)  null default current_timestamp,
    updated_at datetime(6)  null default current_timestamp on update current_timestamp,
    user_id    bigint       null,
    title      varchar(20)  not null,
    contents   varchar(255) not null,
    foreign key (user_id) references schedulerjpa.user (id)
);

-- 일정 생성
insert into schedule (username, title, contents)
values ('name', 'title', 'contents');

-- 일정 전체 조회
select id, title, contents, created_at, updated_at
from schedule
where user_id = 1;

-- 일정 단건 조회
select id, title, contents, created_at, updated_at
from schedule
where id = 1;

-- 일정 단건 수정

update schedule
SET title = 'updated title',
    contents = 'updated contents'
WHERE id = 1;

-- 일정 단건 삭제

delete from schedule
where id = 1;