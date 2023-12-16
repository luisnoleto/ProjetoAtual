insert into genero (nome) values ('Pop');
INSERT into genero (nome) values ('Rock');
Insert into genero (nome) values ('Indie');
insert into genero (nome) values ('MPB');

insert into artista (nome, descricao) values ('Taylor Swift', 'Taylor Alison Swift é uma cantora e compositora norte-americana. Artista mais popular da atualidade, é conhecida pelas suas complexas composições. ');
insert into artista (nome, descricao) values ('Paramore', 'Paramore é uma banda americana de rock formada em Franklin, Tennessee, em 2004. A banda já lançou cinco álbuns de estúdio: All We Know Is Falling, Riot!, Brand New Eyes, Paramore e After Laughter.');
insert into artista (nome, descricao) values ('ANAVITÓRIA', 'Ana Caetano e Vitória Falcão, mais conhecidas como ANAVITÓRIA, são uma dupla musical brasileira formada em 2015 na cidade de Araguaína, no Tocantins.');


insert into telefone (codigoArea, numero) values (11, 111111111);
insert into telefone (codigoArea, numero) values (22, 222222222);
insert into telefone (codigoArea, numero) values (11, 333333333);
INSERT INTO telefone (codigoArea, numero) VALUES (11, 444444444);
INSERT INTO telefone (codigoArea, numero) VALUES (11, 555555555);

insert into estado (nome, sigla) values ('Estado Tocantins', 'TO');
insert into estado (nome, sigla) values ('Estado São Paulo', 'SP');

INSERT INTO municipio (nome, id_estado) VALUES ('Cidade de Palmas', 1);
INSERT INTO municipio (nome, id_estado) VALUES ('Cidade de Campinas', 2);

INSERT INTO endereco (logradouro, bairro, numero, complemento, cep, id_municipio) VALUES ('Rua A', 'Bairro X', '123', 'Apto 456', '12345-678', 1);
INSERT INTO endereco (logradouro, bairro, numero, complemento, cep, id_municipio) VALUES ('Rua B', 'Bairro Y', '456', 'Apto 789', '12345-678', 2);
insert into endereco (logradouro, bairro, numero, complemento, cep, id_municipio) values ('Rua C', 'Bairro Z', '789', 'Apto 101', '12345-678', 2);


insert into usuario (nome, login, senha, cpf, perfil) values ('luis', 'luiss', 'r0ey52dhS6JXwvmpAi12w1SeOjANcDdfovBq1xeSbj8bCJ7fsVUzUyxwEFrT98KAQGDZE3YAmXFf+aPKNHJ39Q==', '609.688.453-99', 2);
insert into usuario (nome, login, senha, cpf, perfil) values ('emily', 'emilyy', '+V1RmhsqZa6fRdqCi7bRS9BwnbPdd0SxjI3LvjZKZJhNFygaVC+k4x72yek7FmEU8S0XRqYELH/o4tDMME3wsQ==', '071.548.466-56', 1);
insert into usuario (nome, login, senha, cpf, perfil) values ('eduardo', 'eduardoo', '+V1RmhsqZa6fRdqCi7bRS9BwnbPdd0SxjI3LvjZKZJhNFygaVC+k4x72yek7FmEU8S0XRqYELH/o4tDMME3wsQ==', '999.999.999-99', 2);


insert into usuario_telefone (id_usuario, id_telefone) values (1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values (1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values (2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values (2, 4);
insert into usuario_telefone (id_usuario, id_telefone) values (3, 5);

insert into usuario_endereco (id_usuario, id_endereco) values (1, 1);
insert into usuario_endereco (id_usuario, id_endereco) values (2, 2);
insert into usuario_endereco (id_usuario, id_endereco) values (3, 3);


insert into gravadora (nome) values ('Sony Music');
insert into gravadora (nome) values ('Universal Music Group');
insert into gravadora (nome) values ('Republic Records');

insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('Midnights', '2022', 'Midnights é o décimo álbum de estúdio da aclamada cantora Taylor Swift. A tornou a primeira pessoa a ganhar 4 AOTY nos Grammys ' ,29.90, 100, 1, 3, 3, 'CD');
insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('evermore', '2020', 'Evermore é o segundo álbum pandêmico de Taylor Swift. Aclamado por suas letras complexas e produção refinada, é considerado refêrencia no meio Indie', 39.90, 100, 1, 1, 3, 'VINIL');
insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('After Laughter', '2017', 'After Laughter é o quinto álbum de estúdio da banda Paramore. Aclamado por sua produção e letras.', 29.90, 100, 2, 3, 2, 'CD');
insert into album ( nome, anoLancamento, descricao, preco, estoque, id_artista, id_genero, id_gravadora, tipoProduto) values ('ANAVITÓRIA', '2016', 'ANAVITÓRIA é o primeiro álbum da dupla ANAVITÓRIA. Aclamado por suas letras e produção.', 29.90, 100, 3, 4, 1, 'FITA_K7');


