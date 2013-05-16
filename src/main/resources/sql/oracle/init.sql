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
        values (124, 'TaskFlow项目部', 'TF部', 'TF-100', '10', '10',
                to_date('07-09-2010', 'dd-mm-yyyy'), 'TaskFlow项目部', null);
insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (125, 'TaskFlow开发组', 'TFD', 'TF-110', '20', '10',
                to_date('07-09-2010', 'dd-mm-yyyy'), 'TaskFlow开发组', 124);
insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (126, 'TaskFlow测试组', 'TFT', 'TF-120', '20', '10',
                to_date('07-09-2010', 'dd-mm-yyyy'), 'TaskFlow测试组', 124);
insert into ORG_ORGANIZATION (id, name, shortname, no, grade_code, type_code, create_time, remark, parent_id)
        values (127, 'TaskFlow配置管理组', 'TFC', 'TF-130', '20', '10',
                to_date('07-09-2010', 'dd-mm-yyyy'), 'TaskFlow配置管理组', 124);
commit;

insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (137, '赵云', '男', null, null, null, null, null,
                to_date('09-09-2010', 'dd-mm-yyyy'), null, 125);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (134, '张三', '男', '1985-09-01', null, null, null, null,
                to_date('08-09-2010', 'dd-mm-yyyy'), null, 125);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (136, '李明', '男', null, null, null, null, null,
                to_date('09-09-2010', 'dd-mm-yyyy'), null, 127);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (138, '周涛', '男', null, null, null, null, null,
                to_date('09-09-2010', 'dd-mm-yyyy'), null, 125);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (131, '张涛', '男', '1983-06-15', null, null, null, null,
                to_date('07-09-2010', 'dd-mm-yyyy'), null, 126);
insert into ORG_EMPLOYEE (id, name, sex, birthday, certificate_type_code, certificate_no, email, phone_no, create_time, remark, organization_id)
        values (129, '王坤', '男', '1980-01-01', null, null, null, null,
                to_date('07-09-2010', 'dd-mm-yyyy'), null, 124);
commit;

insert into ACCT_USER (id, login_name, password, employee_id)
        values (1, 'user', 'user', null);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (0, 'admin', 'admin', null);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (135, 'zhangsan', 'zhangsan', 134);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (130, 'wangkun', 'wangkun', 129);
insert into ACCT_USER (id, login_name, password, employee_id)
        values (132, 'zhangtao', 'zhanglei', 131);
commit;

insert into ACCT_USER_ROLE (user_id, role_id)
        values (0, 1);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (0, 2);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (1, 2);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (135, 3);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (130, 1);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (130, 2);
insert into ACCT_USER_ROLE (user_id, role_id)
        values (132, 2);
commit;