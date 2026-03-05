ALTER TABLE reservation
    ADD COLUMN end_time TIMESTAMP WITHOUT TIME ZONE;

UPDATE reservation
    SET end_time = start_time + (duration_min * INTERVAL '1 minute');

ALTER TABLE reservation
    ALTER COLUMN end_time SET NOT NULL;

ALTER TABLE table_group
    ADD COLUMN restaurant_id BIGINT;

UPDATE table_group
    set restaurant_id = 1;

ALTER TABLE table_group
    ALTER COLUMN restaurant_id SET NOT NULL;

ALTER TABLE table_group
    ADD CONSTRAINT FK_TABLE_GROUP_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);



