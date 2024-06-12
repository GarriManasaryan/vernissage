create table if not exists vr_features(
    id varchar(40) not null,
    name text not null,
    description text,
    parent_id varchar(40),
    category_id varchar(40) not null,
    discriminator varchar(56) not null,
    primary key (id),
    constraint fk_features_parent_id foreign key(parent_id) references vr_features(id),
    constraint fk_features_category_id foreign key(category_id) references vr_product_categories(id)
);

--    вот тут как раз нет, ибо на один и тот же параметр пол может быть два разных значения
--    primary key (id),
create table if not exists vr_features_text(
    id varchar(40) not null,
    value text not null,
    constraint fk_features_text_id_to_base foreign key(id) references vr_features(id)
);

--    вот тут как раз нет, ибо на один и тот же параметр пол может быть два разных значения
--    primary key (id),
create table if not exists vr_features_numeric(
    id varchar(40) not null,
    "from" integer not null,
    "to" integer not null,
    unit varchar(256) not null,
    less_than_text varchar(128) not null,
    more_than_text varchar(128) not null,
    constraint fk_features_numeric_id_to_base foreign key(id) references vr_features(id)
);