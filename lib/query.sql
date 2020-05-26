create database db_fatec
go

use db_fatec
go

create table RELATORIOS (
estado char(1) not null,
imagem varbinary(1024) not null,
dta date,
eh_cafe char(1),
n_frutos int,
verdes int
)

sp_help RELATORIOS