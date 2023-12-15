insert into genero (nome) values ('Pop');
INSERT into genero (nome) values ('Rock');
insert into artista (nome, descricao) values ('Anitta', 'Anitta é uma cantora, compositora, atriz, apresentadora e empresária brasileira. ');
insert into artista (nome, descricao) values ('Ariana Grande', 'Ariana Grande-Butera é uma cantora, atriz, compositora e apresentadora estadunidense. ');
insert into telefone (codigoArea, numero) values (11, 111111111);
insert into telefone (codigoArea, numero) values (11, 222222222);
insert into telefone (codigoArea, numero) values (11, 333333333);

insert into estado (nome, sigla) values ('Estado Tocantins', 'TO');
INSERT INTO municipio (nome, id_estado) VALUES ('Cidade de Palmas', 1);
INSERT INTO endereco (logradouro, bairro, numero, complemento, cep, id_municipio) VALUES ('Rua A', 'Bairro X', '123', 'Apto 456', '12345-678', 1);

insert into usuario (nome, login, senha, id_endereco, cpf, perfil) values ('nome3', 'login3', 'r0ey52dhS6JXwvmpAi12w1SeOjANcDdfovBq1xeSbj8bCJ7fsVUzUyxwEFrT98KAQGDZE3YAmXFf+aPKNHJ39Q==', 1, '999.999.999-99', 1);
insert into usuario (nome, login, senha, id_endereco, cpf, perfil) values ('nome4', 'login4', '+V1RmhsqZa6fRdqCi7bRS9BwnbPdd0SxjI3LvjZKZJhNFygaVC+k4x72yek7FmEU8S0XRqYELH/o4tDMME3wsQ==', 1, '999.999.999-99', 2);

insert into usuario_telefone (id_usuario, id_telefone) values (1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values (1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values (2, 3);

insert into gravadora (nome) values ('Sony Music');
insert into gravadora (nome) values ('Universal Music');

insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('Kisses', '2019', 'Kisses é o quarto álbum de estúdio da cantora brasileira Anitta,internacional.', 29.90, 100, 1, 2, 1, 'CD');
insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('Positons', '2020', 'Positions é o sexto álbum de estúdio da cantora estadunidense Ariana Grande.', 39.90, 100, 2, 1, 2, 'CD');


