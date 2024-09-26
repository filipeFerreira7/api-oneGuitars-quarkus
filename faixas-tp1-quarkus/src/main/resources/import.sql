-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into faixa (nome, preco) values('Faixa Branca', 100.0);
insert into faixa (nome, preco) values('Faixa Azul', 110.0);
insert into faixa (nome, preco) values('Faixa Roxa', 120.0);
insert into faixa (nome, preco) values('Faixa Marrom', 130.0);
insert into faixa (nome, preco) values('Faixa Preta', 140.0);

insert into estado (nome, sigla) values('Tocantins','TO');
insert into estado (nome, sigla) values('Goiás','GO');
insert into estado (nome, sigla) values('Paraná','PR');
insert into estado (nome, sigla) values('São Paulo','SP');  

insert into cidade (nome, id_estado) values('Palmas',1);  
insert into cidade (nome, id_estado) values('Paraíso',1);  
insert into cidade (nome, id_estado) values('Gurupi',1);  