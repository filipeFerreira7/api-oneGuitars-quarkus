-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into estado (nome, sigla) values('Tocantins','TO');
insert into estado (nome, sigla) values('Goiás','GO');
insert into estado (nome, sigla) values('Paraná','PR');
insert into estado (nome, sigla) values('São Paulo','SP');  

insert into cidade (nome, id_estado) values('Palmas',1);  
insert into cidade (nome, id_estado) values('Paraíso',1);  
insert into cidade (nome, id_estado) values('Gurupi',1);  

insert into marca(nome) values ('Tagima');
insert into marca(nome) values ('Fender');
insert into marca(nome) values ('Gibson');

insert into especificacao(sku,comprimento,tipoMadeira,tipoCaptador,tipoChave) values
                         ('GTR01',1.20,'Mahogany','P90','3 posições');
insert into especificacao(sku,comprimento,tipoMadeira,tipoCaptador,tipoChave) values
                         ('GTR02',1.30,'Rosewood','Humbucker','3 posições');
insert into especificacao(sku,comprimento,tipoMadeira,tipoCaptador,tipoChave) values
                         ('GTR03',1.25,'Jacarandá','Single coil','5 posições');
                
insert into guitarra(id_especificacao,id_marca,nome,numeroSerie,cor,preco) values
                    (1,1,'TW61','903842','Fuschia',1299.95);
insert into guitarra(id_especificacao,id_marca,nome,numeroSerie,cor,preco) values
                    (3,2,'American Standard','903831','Preto',12987.95); 
insert into guitarra(id_especificacao,id_marca,nome,numeroSerie,cor,preco) values
                    (3,2,'Les paul','903619','Vermelho Bordô',19977.15);         

insert into lote(data, estoque, id_guitarra, codigo)
	VALUES ('2010-12-03', 100, 2, 'TG001');      

    
insert into lote(data, estoque, id_guitarra, codigo)
	VALUES ('2010-12-05', 100, 1, 'ASF001');   

                            
insert into lote(data, estoque, id_guitarra, codigo)
	VALUES ('2010-12-04', 100, 3, 'GB001');   


insert into telefone(dd,numero) values ('55','87382');

insert into telefone(dd,numero) values ('43','8227382');
insert into telefone(dd,numero) values ('63','883982');
insert into telefone(dd,numero) values ('12','167832');
insert into telefone(dd,numero) values ('33','90090');



