drop table vr_prices cascade;
ALTER TABLE vr_products drop COLUMN "price_id";
ALTER TABLE vr_products add "price" jsonb;

ALTER TABLE vr_products drop COLUMN "category_id";

drop table vr_masters cascade;
