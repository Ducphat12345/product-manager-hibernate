create database QLSP
go
use QLSP
go

create table users(
id int identity primary key,
username nvarchar(50),
password nvarchar(50),
role nvarchar(50),
)

create table category(
id int identity primary key,
name nvarchar(50),
)

create table product(
id int identity primary key,
name nvarchar(50),
price money,
category_id int foreign key references category(id),
)

insert into users(username, password, role) values(N'thaovp', N'thao123', 'admin')
insert into users(username, password, role) values(N'phatnd', N'phat123', 'customer')
insert into category(name) values (N'Điện thoại')
insert into category(name) values (N'Laptop')
insert into category(name) values (N'Quần áo nam')
insert into category(name) values (N'Quần áo nữ')
insert into category(name) values (N'Sport')
insert into product(name, price, category_id) values(N'Iphone12 ProMax', 100000, 5)
insert into product(name, price, category_id) values(N'Iphone13 ProMax', 200000, 5)
insert into product(name, price, category_id) values(N'Iphone14 ProMax', 300000, 5)
insert into product(name, price, category_id) values(N'Dell I5', 100000, 6)
insert into product(name, price, category_id) values(N'Asus I7', 200000, 6)
insert into product(name, price, category_id) values(N'HP I9', 300000, 6)
insert into product(name, price, category_id) values(N'Quần âu', 100000, 7)
insert into product(name, price, category_id) values(N'Áo khoác', 200000, 7)
insert into product(name, price, category_id) values(N'Áo len nam', 300000, 7)
insert into product(name, price, category_id) values(N'Áo dạ len', 100000, 8)
insert into product(name, price, category_id) values(N'Quần tất', 200000, 8)
insert into product(name, price, category_id) values(N'Áo len nữ', 300000, 8)
insert into product(name, price, category_id) values(N'Mizuno', 100000, 9)
insert into product(name, price, category_id) values(N'Adidas', 200000, 9)
insert into product(name, price, category_id) values(N'Puma', 300000, 9)
select * from category
select * from product
delete from category where id = 8
delete from product where id = 19
select * from product p inner join category c on p.category_id = c.id where c.id = 5
