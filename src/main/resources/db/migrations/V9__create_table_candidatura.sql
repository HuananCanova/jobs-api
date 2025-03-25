-- V2__Criar_Tabela_Candidatura.sql

-- Criação da tabela Candidatura
CREATE TABLE candidatura (
                             id SERIAL PRIMARY KEY,
                             user_id BIGINT,
                             vaga_id BIGINT,
                             FOREIGN KEY (user_id) REFERENCES users (id),
                             FOREIGN KEY (vaga_id) REFERENCES vaga (id)
);
