-- Inserir usuário 1 com senha criptografada usando MD5
INSERT INTO users (nome, email, senha, celular, foto_perfil_url)
VALUES ('Usuário 1', 'usuario1@example.com', 'senha123', '123456789', 'foto1.jpg');


-- Atribuir a role ROLE_USER ao usuário 1
INSERT INTO user_roles (user_id, role)
VALUES ((SELECT id FROM users WHERE email = 'usuario1@example.com'), 'ROLE_EMPRESA');
