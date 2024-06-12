-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
 insert into cor (nome, codigo) values('Vermelho', 'F2F2F2');
 insert into cor (nome, codigo) values('Amarelo', 'A3A3A3');
 insert into cor (nome, codigo) values('Azul', '4B4B4B');

 insert into formato (descricaoFormato) values('Convencional');
 insert into formato (descricaoFormato) values('Retangular');
 insert into formato (descricaoFormato) values('Geoidal');

 insert into material (nome) values('P.U.');
 insert into material (nome) values('Fibra de Carbono');
 insert into material (nome) values('Alumínio');

-- CLIENTES

insert into usuario(id, username, senha) values (1, 'teste', '123');

insert into pessoa(id, nome, sobrenome, cpf, data_nascimento, id_usuario)
values (1, 'Teste', 'da Silva', '111.111.111-11', '1990-05-05', 1);

insert into cliente(id, saldo, email, id_pessoa) 
values (1, 0.0, 'teste@gmail.com', 1);



-- PRODUTOS
 insert into produto(nome, codigo, estoque, preco, datacadastro) values 
('Injecao 1', '#111', 50, 200.0, CURRENT_DATE), 
('Injecao 2', '#222', 50, 300.0, CURRENT_DATE), 
('Volante 1', '#111', 50, 200.0, CURRENT_DATE);

insert into injecao(id, tipoCombustivel) values 
(1, 'Etanol'), 
(2, 'Gasolina');

insert into volante (id, diametro, idCor, idFormato, idMaterial) values
(3, 30, 1, 1, 1);




