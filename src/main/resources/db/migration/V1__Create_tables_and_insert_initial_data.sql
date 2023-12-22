CREATE TABLE clientes (
                          uuid VARCHAR(36) PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          cpf VARCHAR(14) NOT NULL,
                          email VARCHAR(255) NOT NULL
);

INSERT INTO clientes (uuid, nome, cpf, email) VALUES
                                                  ('123e4567-e89b-12d3-a456-426614174000', 'Cliente 1', '88256176660', 'cliente1@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174001', 'Cliente 2', '87223666447', 'cliente2@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174002', 'Cliente 3', '32267558386', 'cliente3@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174003', 'Cliente 4', '52846686858', 'cliente4@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174004', 'Cliente 5', '28482722956', 'cliente5@email.com');

CREATE TABLE produtos (
                          uuid VARCHAR(36) PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          valor DECIMAL(10, 2) NOT NULL,
                          descricao TEXT NOT NULL,
                          categoria VARCHAR(255) NOT NULL,
                          quantidade INT NOT NULL
);

INSERT INTO produtos (uuid, nome, valor, descricao, categoria, quantidade) VALUES
                                                                               ('123e4567-e89b-12d3-a456-426614174005', 'Produto 1', 10, 'Descricao 1', 'LANCHE', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174006', 'Produto 2', 20, 'Descricao 2', 'BEBIDA', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174007', 'Produto 3', 30, 'Descricao 3', 'ACOMPANHAMENTO', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174008', 'Produto 4', 40, 'Descricao 4', 'SOBREMESA', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174009', 'Produto 5', 50, 'Descricao 5', 'SOBREMESA', 100);

CREATE TABLE pedidos (
                         uuid VARCHAR(36) PRIMARY KEY,
                         clienteid VARCHAR(36) NOT NULL,
                         dataCriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(255) NOT NULL,
                         valorTotal DECIMAL(10, 2) NOT NULL,
                         FOREIGN KEY (clienteid) REFERENCES clientes(uuid)
);

INSERT INTO pedidos (uuid, clienteid, status, valorTotal, dataCriacao) VALUES
                                                                           ('123e4567-e89b-12d3-a456-426614174010', '123e4567-e89b-12d3-a456-426614174000', 'RECEBIDO', 10, '2022-12-21 00:00:00'),
                                                                           ('123e4567-e89b-12d3-a456-426614174011', '123e4567-e89b-12d3-a456-426614174001', 'RECEBIDO', 20, '2022-12-21 00:00:00'),
                                                                           ('123e4567-e89b-12d3-a456-426614174012', '123e4567-e89b-12d3-a456-426614174002', 'RECEBIDO', 30, '2022-12-21 00:00:00'),
                                                                           ('123e4567-e89b-12d3-a456-426614174013', '123e4567-e89b-12d3-a456-426614174003', 'RECEBIDO', 40, '2022-12-21 00:00:00'),
                                                                           ('123e4567-e89b-12d3-a456-426614174014', '123e4567-e89b-12d3-a456-426614174004', 'RECEBIDO', 50, '2022-12-21 00:00:00');

CREATE SEQUENCE pedido_produtos_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE pedido_produtos (
                                 id BIGINT DEFAULT NEXT VALUE FOR pedido_produtos_seq PRIMARY KEY,
                                 pedido_uuid VARCHAR(36) NOT NULL,
                                 produto_uuid VARCHAR(36) NOT NULL,
                                 valor DECIMAL(10, 2) NOT NULL,
                                 quantidade INT NOT NULL,
                                 categoria VARCHAR(255) NOT NULL,
                                 FOREIGN KEY (pedido_uuid) REFERENCES pedidos(uuid),
                                 FOREIGN KEY (produto_uuid) REFERENCES produtos(uuid)
);

INSERT INTO pedido_produtos (id, pedido_uuid, produto_uuid, valor, quantidade, categoria) VALUES
                                                                                              (NEXT VALUE FOR pedido_produtos_seq, '123e4567-e89b-12d3-a456-426614174010', '123e4567-e89b-12d3-a456-426614174005', 10, 1, 'LANCHE'),
                                                                                              (NEXT VALUE FOR pedido_produtos_seq, '123e4567-e89b-12d3-a456-426614174011', '123e4567-e89b-12d3-a456-426614174006', 20, 1, 'BEBIDA'),
                                                                                              (NEXT VALUE FOR pedido_produtos_seq, '123e4567-e89b-12d3-a456-426614174012', '123e4567-e89b-12d3-a456-426614174007', 30, 1, 'ACOMPANHAMENTO'),
                                                                                              (NEXT VALUE FOR pedido_produtos_seq, '123e4567-e89b-12d3-a456-426614174013', '123e4567-e89b-12d3-a456-426614174008', 40, 1, 'SOBREMESA'),
                                                                                              (NEXT VALUE FOR pedido_produtos_seq, '123e4567-e89b-12d3-a456-426614174014', '123e4567-e89b-12d3-a456-426614174009', 50, 1, 'SOBREMESA');
