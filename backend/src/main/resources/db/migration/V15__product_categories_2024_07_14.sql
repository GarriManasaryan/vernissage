create table if not exists vr_product_categories(
    product_id varchar(40) not null,
    category_id varchar(40) not null,
    constraint fk_product_categories_product_id foreign key(product_id) references vr_products(id),
    constraint fk_product_categories_category_id foreign key(category_id) references vr_categories(id)
);

create index product_id_i_product_categories on vr_product_categories (product_id);
create index category_id_i_product_categories on vr_product_categories (category_id);
create unique index product_id_category_id_ui_product_categories on vr_product_categories (product_id, category_id);