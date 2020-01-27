create table sys_permission
(
  id            integer not null,
  available     boolean,
  name          varchar(255),
  parent_id     bigint,
  parent_ids    varchar(255),
  permission    varchar(255),
  resource_type enum ('menu','button'),
  url           varchar(255),
  primary key (id)
);
create table sys_role
(
  id          integer not null,
  available   boolean,
  description varchar(255),
  role        varchar(255),
  primary key (id)
);

create table sys_role_permission
(
  role_id       integer not null,
  permission_id integer not null
);

create table sys_user_role
(
  uid     integer not null,
  role_id integer not null
);

create table user_info
(
  uid      integer not null,
  name     varchar(255),
  password varchar(255),
  salt     varchar(255),
  state    tinyint not null,
  username varchar(255),
  primary key (uid)
);

alter table user_info
  drop constraint if exists UK_f2ksd6h8hsjtd57ipfq9myr64;

alter table user_info
  add constraint UK_f2ksd6h8hsjtd57ipfq9myr64 unique (username);
create sequence hibernate_sequence start with 1 increment by 1;
alter table sys_role_permission
  add constraint FKomxrs8a388bknvhjokh440waq foreign key (permission_id) references sys_permission;

alter table sys_role_permission
  add constraint FK9q28ewrhntqeipl1t04kh1be7 foreign key (role_id) references sys_role;

alter table sys_user_role
  add constraint FKhh52n8vd4ny9ff4x9fb8v65qx foreign key (role_id) references sys_role;

alter table sys_user_role
  add constraint FKgkmyslkrfeyn9ukmolvek8b8f foreign key (uid) references user_info;