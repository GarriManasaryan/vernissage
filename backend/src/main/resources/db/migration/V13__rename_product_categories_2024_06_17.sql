ALTER TABLE vr_product_categories RENAME TO vr_categories;
alter table vr_features_numeric ALTER COLUMN unit drop not null;
