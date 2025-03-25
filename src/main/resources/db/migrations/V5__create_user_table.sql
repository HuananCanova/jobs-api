CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       nome VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       senha VARCHAR(255),
                       celular VARCHAR(255) UNIQUE,
                       foto_perfil_url VARCHAR(255)
);

