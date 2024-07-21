create table if not exists vr_product_features(
    product_id varchar(40) not null,
    feature_id varchar(40) not null,
    constraint fk_product_features_product_id foreign key(product_id) references vr_products(id),
    constraint fk_product_features_feature_id foreign key(feature_id) references vr_features(id)
);

create index product_id_i_product_features on vr_product_features (product_id);
create index feature_id_i_product_features on vr_product_features (feature_id);
create unique index product_id_feature_id_ui_product_features on vr_product_features (product_id, feature_id);