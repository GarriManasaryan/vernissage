ALTER TABLE vr_master RENAME TO vr_masters;
ALTER TABLE vr_product_category RENAME TO vr_product_categories;
ALTER TABLE vr_price RENAME TO vr_prices;

create table if not exists vr_products(
    id varchar(40) not null,
    name text not null,
    description text,
    price_id varchar(40) not null,
    master_id varchar(40) not null,
    category_id varchar(40) not null,
    primary key (id),
    constraint fk_products_master_id foreign key(master_id) references vr_masters(id),
    constraint fk_products_price_id foreign key(price_id) references vr_prices(id),
    constraint fk_products_category_id foreign key(category_id) references vr_product_categories(id)
);

