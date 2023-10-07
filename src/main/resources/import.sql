insert into genero (nome) values ('Pop');
INSERT into genero (nome) values ('Rock');
insert into artista (nome, descricao) values ('Anitta', 'Anitta é uma cantora, compositora, atriz, apresentadora e empresária brasileira. Iniciou sua ');
insert into telefone (codigoArea, numero) values (11, 111111111);
insert into usuario (nome, login, senha, isAdmin) values ('admin', 'admin', 'admin', true);
insert into usuario_telefone (id_usuario, id_telefone) values (1, 1);
insert into gravadora (nome) values ('Sony Music');

 
 
 
insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('Kisses', '2019', 'Kisses é o quarto álbum de estúdio da cantora brasileira Anitta,internacional.', 29.90, 100, 1, 2, 1, 'CD');