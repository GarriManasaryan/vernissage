create table if not exists vr_product_category(
    id varchar(40) not null,
    name text not null,
    description text,
    parent_id varchar(40),
    primary key (id),
    constraint fk_product_category_parent_id foreign key(parent_id) references vr_product_category(id)
);

