create table if not exists vr_users(
    id varchar(40) not null,
    name text not null,
    role varchar(128) not null,
    time_zone varchar(32) not null,
    primary key (id)
);