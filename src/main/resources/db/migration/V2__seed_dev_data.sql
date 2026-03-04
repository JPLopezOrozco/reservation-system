INSERT INTO restaurant(id, name, address, time_zone, created_at)
    VALUES (1, 'Don Julio', 'Guatemala 4699, Buenos Aires', 'America/Argentina/Buenos_Aires', now());

INSERT INTO restaurant_table(id, restaurant_id, table_name, min_capacity, max_capacity)
    VALUES
        (1, 1, 'T1', 2, 4),
        (2, 1, 'T2', 2, 4),
        (3, 1, 'T3', 4, 6);

INSERT INTO policy(id, restaurant_id)
    VALUES (1, 1);

INSERT INTO schedules (policy_id, day_of_week, start_time, end_time)
    VALUES
        (1, 'MONDAY', '12:00', '23:00'),
        (1, 'TUESDAY', '12:00', '23:00'),
        (1, 'FRIDAY', '12:00', '23:30');

INSERT INTO users(id, name, surname, email, password, phone, role)
    VALUES (1, 'Juan', 'Lopez', 'juanlopez@gmail.com', 'password', '1145562233', 'OWNER')
