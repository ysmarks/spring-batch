drop table autobot if exists;
create table autobot(
autobot_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    car VARCHAR(50)
);