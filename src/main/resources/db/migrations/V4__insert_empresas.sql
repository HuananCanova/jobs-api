-- Inserção de empresas com endereços
INSERT INTO endereco (complemento, bairro, cep, numero, cidade, uf)
VALUES
    ('Endereco 1', 'Bairro 1', '12345-678', '123', 'Cidade 1', 'U1'),
    ('Endereco 2', 'Bairro 2', '98765-432', '456', 'Cidade 2', 'U2'),
    ('Endereco 3', 'Bairro 3', '11111-111', '789', 'Cidade 3', 'U3'),
    ('Endereco 4', 'Bairro 4', '22222-222', '101', 'Cidade 4', 'U4'),
    ('Endereco 5', 'Bairro 5', '33333-333', '202', 'Cidade 5', 'U5');

INSERT INTO empresa (razao_social, CNPJ, email, endereco_id)
VALUES
    ('Empresa 1', '1234567890', 'empresa1@example.com', 1),
    ('Empresa 2', '9876543210', 'empresa2@example.com', 2),
    ('Empresa 3', '1111111111', 'empresa3@example.com', 3),
    ('Empresa 4', '2222222222', 'empresa4@example.com', 4),
    ('Empresa 5', '3333333333', 'empresa5@example.com', 5);
