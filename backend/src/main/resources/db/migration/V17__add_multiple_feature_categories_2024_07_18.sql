create table if not exists vr_feature_categories(
    feature_id varchar(40) not null,
    category_id varchar(40) not null,
    constraint fk_feature_categories_feature_id foreign key(feature_id) references vr_features(id),
    constraint fk_feature_categories_category_id foreign key(category_id) references vr_categories(id)
);

create index feature_id_i_feature_categories on vr_feature_categories (feature_id);
create index category_id_i_feature_categories on vr_feature_categories (category_id);
create unique index feature_id_category_id_ui_feature_categories on vr_feature_categories (feature_id, category_id);