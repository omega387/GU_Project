create database pims;

use pims;

create table product
(
 id integer auto_increment primary key,
 pname varchar(100) not null,
 quantity int not null ,
 price int not null
);

desc product;

select*from product;