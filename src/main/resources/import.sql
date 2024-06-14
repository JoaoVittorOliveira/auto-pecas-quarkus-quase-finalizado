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

-- CLIENTES/ADMs

-- password: 123
insert into usuario(username, senha) values ('teste', 'Z7dL+3VaMV++fdWH0b8S3NV26muviRKuWXNk5ayr2RVBF9BE8tMorc/G7NB1P51lHzLhjc7irjXu+Q5f3T997w==');

insert into pessoa(nome, sobrenome, cpf, data_nascimento, id_usuario)
values ('Teste', 'da Silva', '111.111.111-11', '1990-05-05', 1);

insert into cliente(saldo, email, id_pessoa) 
values (0.0, 'teste@gmail.com', 1);

-- password: 123
insert into usuario(username, senha) values ('fernando', 'Z7dL+3VaMV++fdWH0b8S3NV26muviRKuWXNk5ayr2RVBF9BE8tMorc/G7NB1P51lHzLhjc7irjXu+Q5f3T997w==');

insert into pessoa(nome, sobrenome, cpf, data_nascimento, id_usuario)
values ('Fernando', 'de Souza', '111.222.111-22', '2000-05-05', 2);

insert into administrador(id_pessoa) values (2);



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

-- PEDIDOS

insert into pedido (id_cliente, status_pagamento_pedido, valor_total) values 
(1, 0, 1000.0);

insert into itempedido (id_produto, quantidade_produtos, porcentagem_desconto, valor, id_pedido) values
(1, 2, 0, 400.0, 1);

insert into itempedido (id_produto, quantidade_produtos, porcentagem_desconto, valor, id_pedido) values
(2, 2, 0, 600.0, 1);


