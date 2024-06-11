create table if not exists vr_products(
    id varchar(40) not null,
    name text not null,
    description text,
    price_id varchar(40) not null,
    master_id varchar(40) not null,
    product_category_id varchar(40) not null,
    primary key (id)
);

ALTER TABLE vr_master RENAME TO vr_masters;
ALTER TABLE vr_product_category RENAME TO vr_product_categories;
ALTER TABLE vr_price RENAME TO vr_prices;

