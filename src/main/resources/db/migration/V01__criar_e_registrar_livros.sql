CREATE TABLE livros (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	autor VARCHAR(50) NOT NULL,
	edicao INT(5) NOT NULL,
	quantidade INT(5) NOT NULL
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO livros (nome, autor, edicao, quantidade) VALUES ('Batman', 'Renato', 1, 1);
INSERT INTO livros (nome, autor, edicao, quantidade) VALUES ('Robin', 'Raphael', 2, 2);
INSERT INTO livros (nome, autor, edicao, quantidade) VALUES ('One Piece', 'Renata', 3, 3);