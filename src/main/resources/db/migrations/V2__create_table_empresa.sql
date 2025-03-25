-- Migration para criar a tabela Empresa

CREATE TABLE empresa (
                         id SERIAL NOT NULL PRIMARY KEY,
                         razao_social VARCHAR(255),
                         CNPJ VARCHAR(14) NOT NULL UNIQUE,
                         email VARCHAR(255)
);
