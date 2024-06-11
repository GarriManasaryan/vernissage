create table if not exists vr_price(
    id varchar(40) not null,
    value integer not null,
    currency varchar(56),
    discount float,
    primary key (id)
);

