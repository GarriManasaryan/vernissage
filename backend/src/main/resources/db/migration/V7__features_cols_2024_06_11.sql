alter table vr_features_numeric alter column "from" TYPE float;
alter table vr_features_numeric alter column "to" TYPE float;

alter table vr_features_numeric ALTER COLUMN less_than_text drop not null;
alter table vr_features_numeric ALTER COLUMN more_than_text drop not null;
