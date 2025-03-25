-- Migration para criar a tabela Endereco

CREATE TABLE endereco (
                          id SERIAL PRIMARY KEY,
                          complemento VARCHAR(255),
                          bairro VARCHAR(255),
                          cep VARCHAR(9),
                          numero VARCHAR(10),
                          cidade VARCHAR(255),
                          uf VARCHAR(2)
);
