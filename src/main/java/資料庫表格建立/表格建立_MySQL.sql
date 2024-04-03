CREATE DATABASE IF NOT EXISTS ezban;
USE ezban;

DROP TABLE IF EXISTS event_coupon;
DROP TABLE IF EXISTS host_no;

CREATE TABLE host  (
host_no INT PRIMARY KEY
);
INSERT INTO host (host_no) VALUES (1);
INSERT INTO host (host_no) VALUES (2);
INSERT INTO host (host_no) VALUES (3);
INSERT INTO host (host_no) VALUES (4);
INSERT INTO host (host_no) VALUES (5);
INSERT INTO host (host_no) VALUES (6);
INSERT INTO host (host_no) VALUES (7);
INSERT INTO host (host_no) VALUES (8);
INSERT INTO host (host_no) VALUES (9);
INSERT INTO host (host_no) VALUES (10);

CREATE TABLE event_coupon (
    event_coupon_no INT AUTO_INCREMENT NOT NULL,
    host_no INT,
    event_coupon_name VARCHAR(100),
    coupon_code VARCHAR(10) UNIQUE,
    usage_limit INT,
    remaining_times INT,
    min_spend INT,
    event_coupon_discount INT,
    start_date DATETIME,
    end_date DATETIME,
    event_coupon_status TINyINT,
    CONSTRAINT event_coupon_PK PRIMARY KEY (event_coupon_no),
    CONSTRAINT event_coupon_FK FOREIGN KEY (host_no) REFERENCES host (host_no)
)AUTO_INCREMENT = 5001;

INSERT INTO event_coupon (host_no, event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status) 
VALUES 
(1, 'Event A', 'CODEA', 50, 24, 300, 150, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1),
(2, 'Event B', 'CODEB', 60, 48, 150, 100, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1),
(3, 'Event C', 'CODEC', 30, 15, 200, 75, '2024-02-01 00:00:00', '2024-03-31 23:59:59', 0),
(4, 'Event D', 'CODED', 80, 40, 250, 125, '2024-04-01 00:00:00', '2024-05-30 23:59:59', 1),
(5, 'Event E', 'CODEE', 100, 50, 350, 175, '2024-06-01 00:00:00', '2024-07-31 23:59:59', 1),
(6, 'Event F', 'CODEF', 20, 10, 150, 50, '2024-08-01 00:00:00', '2024-09-30 23:59:59', 0),
(7, 'Event G', 'CODEG', 70, 35, 300, 150, '2024-10-01 00:00:00', '2024-11-30 23:59:59', 2),
(8, 'Event H', 'CODEH', 40, 20, 200, 100, '2024-01-15 00:00:00', '2024-02-28 23:59:59', 1),
(9, 'Event I', 'CODEI', 60, 30, 250, 125, '2024-03-01 00:00:00', '2024-04-30 23:59:59', 0),
(10, 'Event J', 'CODEJ', 90, 45, 400, 200, '2024-05-01 00:00:00', '2024-06-30 23:59:59', 2);





