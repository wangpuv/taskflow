--初始化
insert into ACCT_AUTHORITY (id, name)
        values (2, '修改机构管理');
insert into ACCT_AUTHORITY (id, name)
        values (4, '修改账号管理');
insert into ACCT_AUTHORITY (id, name)
        values (1, '浏览机构管理');
insert into ACCT_AUTHORITY (id, name)
        values (3, '浏览账号管理');
insert into ACCT_AUTHORITY (id, name)
        values (5, '浏览任务管理');
commit;

insert into ACCT_ROLE (id, name)
        values (2, '高级用户');
insert into ACCT_ROLE (id, name)
        values (1, '管理员');
insert into ACCT_ROLE (id, name)
        values (3, '普通用户');
commit;

insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (1, 1);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (1, 2);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (1, 3);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (1, 4);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (1, 5);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (2, 1);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (2, 3);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (2, 5);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (3, 1);
insert into ACCT_ROLE_AUTHORITY (role_id, authority_id)
        values (3, 5);
commit;

insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (1, 'TaskFlow项目部', 'TF部', 'TF-100', '10', '10',
                TIMESTAMP'2010-09-07', 'TaskFlow项目部', null);
insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (2, 'TaskFlow开发组', 'TFD', 'TF-110', '20', '10',
                TIMESTAMP'2010-09-07', 'TaskFlow开发组', 1);
insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (3, 'TaskFlow测试组', 'TFT', 'TF-120', '20', '10',
                TIMESTAMP'2010-09-07', 'TaskFlow测试组', 1);
insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (4, 'TaskFlow配置管理组', 'TFC', 'TF-130', '20', '10',
                TIMESTAMP'2010-09-07', 'TaskFlow配置管理组', 1);
commit;

insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (1, '赵云', '男', null, null, null, null, null,
                TIMESTAMP'2010-09-09', null, 2);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (2, '张三', '男', '1985-09-01', null, null, null, null,
                TIMESTAMP'2010-09-08', null, 2);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (3, '李明', '男', null, null, null, null, null,
                TIMESTAMP'2010-09-09', null, 4);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (4, '周涛', '男', null, null, null, null, null,
                TIMESTAMP'2010-09-09', null, 2);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (5, '张涛', '男', '1983-06-15', null, null, null, null,
                TIMESTAMP'2010-09-07', null, 3);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (6, '王坤', '男', '1980-01-01', null, null, null, null,
                TIMESTAMP'2010-09-07', null, 1);
commit;

insert into ACCT_USER (id, login_name, password, employee_id)
        values (1, 'user', 'user', null);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (0, 'admin', 'admin', null);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (2, 'zhangsan', 'zhangsan', 2);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (3, 'wangkun', 'wangkun', 6);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (4, 'zhangtao', 'zhanglei', 5);
commit;

insert into ACCT_USER_ROLE (user_id, role_id)
        values (0, 1);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (0, 2);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (1, 2);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (2, 3);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (3, 1);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (3, 2);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (4, 2);
commit;