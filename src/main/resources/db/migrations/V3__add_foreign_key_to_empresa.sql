-- Migration para adicionar chave estrangeira à tabela Empresa

ALTER TABLE empresa
    ADD COLUMN endereco_id INT REFERENCES endereco(id);
