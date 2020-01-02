
-- 部门表
create table if not exists tbl_dept
(
  id        integer primary key auto_increment,
  dept_name varchar(50)
);

-- 员工表
create table if not exists tbl_employee
(
  id        integer primary key auto_increment,
  last_name varchar(50),
  email     varchar(50),
  gender    varchar(5),
  d_id      integer,
  CONSTRAINT fk_e_e FOREIGN KEY (d_id) REFERENCES tbl_dept (id)

);




