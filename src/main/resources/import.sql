insert into genero (nome) values ('Pop');
INSERT into genero (nome) values ('Rock');
insert into artista (nome, descricao) values ('Anitta', 'Anitta é uma cantora, compositora, atriz, apresentadora e empresária brasileira. ');
insert into artista (nome, descricao) values ('Ariana Grande', 'Ariana Grande-Butera é uma cantora, atriz, compositora e apresentadora estadunidense. ');
insert into telefone (codigoArea, numero) values (11, 111111111);
insert into telefone (codigoArea, numero) values (11, 222222222);
insert into usuario (nome, login, senha, isAdmin) values ('admin', 'admin', 'admin', true);
insert into usuario (nome, login, senha, isAdmin) values ('user', 'user', 'user', false);
insert into usuario_telefone (id_usuario, id_telefone) values (2, 2);
insert into usuario_telefone (id_usuario, id_telefone) values (1, 1);
insert into gravadora (nome) values ('Sony Music');
insert into gravadora (nome) values ('Universal Music');

insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('Kisses', '2019', 'Kisses é o quarto álbum de estúdio da cantora brasileira Anitta,internacional.', 29.90, 100, 1, 2, 1, 'CD');
insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('Positons', '2020', 'Positions é o sexto álbum de estúdio da cantora estadunidense Ariana Grande.', 39.90, 100, 2, 1, 2, 'CD');
