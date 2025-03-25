-- V1__Criar_Tabela_Vaga.sql

-- Criação da tabela Vaga
CREATE TABLE vaga (
                      id SERIAL PRIMARY KEY,
                      titulo VARCHAR(255) NOT NULL,
                      descricao TEXT NOT NULL,
                      requisitos TEXT,
                      empresa_id BIGINT,
                      FOREIGN KEY (empresa_id) REFERENCES empresa (id)
);
