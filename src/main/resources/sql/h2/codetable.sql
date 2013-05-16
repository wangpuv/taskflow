drop table if exists CODE_ORGANIZATION_TYPE;
drop table if exists CODE_ORGANIZATION_GRADE;
drop table if exists CODE_CERTIFICATE_TYPE;

--机构类型代码表
create table CODE_ORGANIZATION_TYPE
(
  CODE   char(2)     not null PRIMARY KEY,
  NAME   varchar(50) not null,
  CHOOSE varchar(1)  not null
);
comment on table CODE_ORGANIZATION_TYPE is '机构类型代码表';
comment on column CODE_ORGANIZATION_TYPE.CODE is '代码';
comment on column CODE_ORGANIZATION_TYPE.NAME is '名称';
comment on column CODE_ORGANIZATION_TYPE.CHOOSE is '是否选择';

insert into CODE_ORGANIZATION_TYPE (code, name, choose) values ('10', '业务', 'Y');
insert into CODE_ORGANIZATION_TYPE (code, name, choose) values ('20', '行政', 'Y');
insert into CODE_ORGANIZATION_TYPE (code, name, choose) values ('30', '财会', 'Y');
insert into CODE_ORGANIZATION_TYPE (code, name, choose) values ('40', '后勤', 'Y');
insert into CODE_ORGANIZATION_TYPE (code, name, choose) values ('99', '其它', 'Y');
commit;

--机构级别代码表
create table CODE_ORGANIZATION_GRADE
(
  CODE   char(2)     not null PRIMARY KEY,
  NAME   varchar(50) not null,
  CHOOSE varchar2(1) not null
);
comment on table CODE_ORGANIZATION_GRADE is '机构级别代码表';
comment on column CODE_ORGANIZATION_GRADE.CODE is '代码';
comment on column CODE_ORGANIZATION_GRADE.NAME is '名称';
comment on column CODE_ORGANIZATION_GRADE.CHOOSE is '是否选择';

insert into CODE_ORGANIZATION_GRADE (code, name, choose) values ('10', '部级', 'Y');
insert into CODE_ORGANIZATION_GRADE (code, name, choose) values ('20', '组级', 'Y');
insert into CODE_ORGANIZATION_GRADE (code, name, choose) values ('99', '其它', 'Y');
commit;

--身份证件类型代码表
create table CODE_CERTIFICATE_TYPE
(
  CODE   char(2)     not null PRIMARY KEY,
  NAME   varchar(50) not null,
  CHOOSE varchar2(1) not null
);
comment on table CODE_CERTIFICATE_TYPE is '身份证件类型代码表';
comment on column CODE_CERTIFICATE_TYPE.CODE is '代码';
comment on column CODE_CERTIFICATE_TYPE.NAME is '名称';
comment on column CODE_CERTIFICATE_TYPE.CHOOSE is '是否选择';

insert into CODE_CERTIFICATE_TYPE (code, name, choose) values ('10', '居民身份证', 'Y');
insert into CODE_CERTIFICATE_TYPE (code, name, choose) values ('20', '护照', 'Y');
commit;