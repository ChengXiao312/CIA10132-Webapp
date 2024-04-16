create database if not exists ezban;
use ezban;

drop table if exists event_coupon;
drop table if exists host;


-- ----------------------------------------以下是活動優惠券(event_coupon)表格---------------------------------------- -- 
-- host表格只建立 host_no，為了event_coupon的FK用-- 
create table host  (
host_no int primary key not null,
host_name varchar(50) not null
);
INSERT INTO host (host_no, host_name) VALUES (1, '創業力學院');
INSERT INTO host (host_no, host_name) VALUES (2, '國際攝影工作室');
INSERT INTO host (host_no, host_name) VALUES (3, '現代藝術中心');
INSERT INTO host (host_no, host_name) VALUES (4, '全方位健康生活');
INSERT INTO host (host_no, host_name) VALUES (5, '全球程式開發者聯盟');
INSERT INTO host (host_no, host_name) VALUES (6, '教育創新國際論壇');
INSERT INTO host (host_no, host_name) VALUES (7, '世界美食探索者');
INSERT INTO host (host_no, host_name) VALUES (8, '國際時尚品牌週');
INSERT INTO host (host_no, host_name) VALUES (9, '國際電影藝術節');
INSERT INTO host (host_no, host_name) VALUES (10, '聖誕節慶市場');

create table event_coupon (
    event_coupon_no             int auto_increment primary key,
    host_no                     int       not null,
    event_coupon_name           varchar(100)  null,
    coupon_code                 varchar(10)  not null unique ,
    usage_limit                 int       not null,
    remaining_times             int       null,
    min_spend                   int       null,
    event_coupon_discount       int       not null,
    start_date                  datetime  null,
    end_date                    datetime  null,
    event_coupon_status         tinyint   not null,
    coupon_desc                 varchar(150) null,
    constraint event_coupon_host_no_fk foreign key (host_no) references host (host_no)
)auto_increment = 5001;

insert into event_coupon (host_no, event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status, coupon_desc) 
values 
(1, '創業研討會', 'ENTREP10', 100, 100, 300, 10, '2024-03-15 00:00:00', '2024-03-16 23:59:59', 1, '針對初創企業家的研討會，限定100次，最低消費300，享9折'),
(2, '攝影工作坊', 'PHOTOWK20', 50, 50, 200, 20, '2024-04-10 00:00:00', '2024-04-12 23:59:59', 1, '攝影技巧與實踐工作坊，限定50次，最低消費200，享8折'),
(3, '藝術展覽', 'ARTSHOW15', 70, 70, 100, 15, '2024-05-01 00:00:00', '2024-05-30 23:59:59', 1, '當代藝術展，限定70次，最低消費100，享85折'),
(4, '健康與瑜伽營', 'YOGA25', 80, 80, 150, 25, '2024-06-05 00:00:00', '2024-06-10 23:59:59', 1, '結合健康飲食與瑜伽的營，限定80次，最低消費150，享75折'),
(5, '程式開發大會', 'CODECONF20', 150, 150, 400, 20, '2024-07-20 00:00:00', '2024-07-22 23:59:59', 1, '面向軟體開發者的大會，限定150次，最低消費400，享8折'),
(6, '創新教育論壇', 'EDUINNO15', 120, 120, 250, 15, '2024-08-15 00:00:00', '2024-08-17 23:59:59', 1, '探討教育創新的論壇，限定120次，最低消費250，享85折'),
(7, '美食節', 'FOODFEST20', 200, 200, 100, 20, '2024-09-10 00:00:00', '2024-09-12 23:59:59', 1, '展示各地美食的節日，限定200次，最低消費100，享8折'),
(8, '時尚週', 'FASHWK30', 100, 100, 300, 30, '2024-10-05 00:00:00', '2024-10-10 23:59:59', 1, '最新時尚趨勢發布週，限定100次，最低消費300，享7折'),
(9, '電影節', 'FILMFEST25', 150, 150, 200, 25, '2024-11-15 00:00:00', '2024-11-20 23:59:59', 1, '國際電影展映活動，限定150次，最低消費200，享75折'),
(10, '聖誕市集', 'XMAS15', 80, 80, 50, 15, '2024-12-01 00:00:00', '2024-12-25 23:59:59', 1, '聖誕節慶活動與市集，限定80次，最低消費50，享85折');




-- ----------------------------------------以下是收藏活動明細(save_event)表格---------------------------------------- -- 

-- member表格只建立 member_no，為了save_event的FK用 --
create table member
(
    member_no int primary key auto_increment not null
);
insert into member (member_no) values (1);
insert into member (member_no) values (2);
insert into member (member_no) values (3);
insert into member (member_no) values (4);
insert into member (member_no) values (5);
insert into member (member_no) values (6);
insert into member (member_no) values (7);
insert into member (member_no) values (8);
insert into member (member_no) values (9);
insert into member (member_no) values (10);

-- event表格只建立 event_no，為了save_event的FK用 --
create table event
(
    event_no   int auto_increment primary key not null
);
insert into event (event_no) values (1);
insert into event (event_no) values (2);
insert into event (event_no) values (3);
insert into event (event_no) values (4);
insert into event (event_no) values (5);
insert into event (event_no) values (6);
insert into event (event_no) values (7);
insert into event (event_no) values (8);
insert into event (event_no) values (9);
insert into event (event_no) values (10);

create table save_event
(
    member_no int not null primary key,
    event_no  int not null,
	constraint save_event_member_no_fk foreign key (member_no) references member (member_no)
);
insert into save_event (member_no, event_no) values (1, 1);
insert into save_event (member_no, event_no) values (2, 2);
insert into save_event (member_no, event_no) values (3, 3);
insert into save_event (member_no, event_no) values (4, 4);
insert into save_event (member_no, event_no) values (5, 5);
insert into save_event (member_no, event_no) values (6, 6);
insert into save_event (member_no, event_no) values (7, 7);
insert into save_event (member_no, event_no) values (8, 8);
insert into save_event (member_no, event_no) values (9, 9);
insert into save_event (member_no, event_no) values (10, 10);


-- ----------------------------------------以下是QR code 票券(qrcode_ticket)表格---------------------------------------- -- 

-- ticket_order_detail表格只建立 ticket_order_detail，為了qrcode_ticket的FK用 --
create table ticket_order_detail
(
    ticket_order_detail_no int not null auto_increment primary key
);        
insert into ticket_order_detail (ticket_order_detail_no) values (1);
insert into ticket_order_detail (ticket_order_detail_no) values (2);
insert into ticket_order_detail (ticket_order_detail_no) values (3);
insert into ticket_order_detail (ticket_order_detail_no) values (4);
insert into ticket_order_detail (ticket_order_detail_no) values (5);
insert into ticket_order_detail (ticket_order_detail_no) values (6);
insert into ticket_order_detail (ticket_order_detail_no) values (7);
insert into ticket_order_detail (ticket_order_detail_no) values (8);
insert into ticket_order_detail (ticket_order_detail_no) values (9);
insert into ticket_order_detail (ticket_order_detail_no) values (10);

create table qrcode_ticket
(
    ticket_no              bigint not null auto_increment primary key,
    ticket_order_detail_no int   not null,
    member_no              int   not null,
    ticket_usage_status    tinyint  null,
    ticket_valid_time      datetime not null,
	constraint qrcode_ticket_ticket_order_detail_no_fk foreign key (ticket_order_detail_no) references ticket_order_detail (ticket_order_detail_no),
    constraint qrcode_ticket_member_no_fk foreign key (member_no) references member (member_no)
)auto_increment = 1001;
insert into qrcode_ticket (ticket_order_detail_no, member_no, ticket_usage_status, ticket_valid_time) 
values
(1, 1, 0, '2024-05-01 10:00:00'),
(2, 2, 1, '2024-05-02 12:00:00'),
(3, 3, 0, '2024-05-03 14:00:00'),
(4, 4, 1, '2024-05-04 16:00:00'),
(5, 5, 0, '2024-05-05 18:00:00'),
(6, 6, 1, '2024-05-06 20:00:00'),
(7, 7, 0, '2024-05-07 22:00:00'),
(8, 8, 1, '2024-05-08 08:00:00'),
(9, 9, 0, '2024-05-09 09:00:00'),
(10, 10, 1, '2024-05-10 10:00:00');







