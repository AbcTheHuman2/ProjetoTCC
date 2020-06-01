create database db_fatec
go

use db_fatec
go

create table RELATORIOS (
id int identity(1,1) primary key,
imagem varbinary(max) not null,
dta datetime not null,
eh_cafe char(1),
n_frutos int,
verdes int
)

sp_help RELATORIOS

select * from RELATORIOS