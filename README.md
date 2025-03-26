# JOBS API

API REST desenvolvida como projeto da disciplina **Programação Orientada a Objetos para Web II** na Universidade Federal de Santa Maria (UFSM). Este projeto implementa um sistema de gerenciamento de vagas de emprego, candidaturas e empresas, utilizando tecnologias modernas e boas práticas de desenvolvimento.

## Sobre o Projeto
O JOBS API foi criado para aplicar conceitos de programação orientada a objetos e desenvolvimento web em um contexto prático. Ele oferece uma solução robusta para autenticação de usuários, gerenciamento de entidades e documentação interativa, demonstrando habilidades em backend com Java e Spring Boot.

## Funcionalidades
- **Autenticação**: Login seguro com JWT (JSON Web Token).
- **Gerenciamento de Empresas**: Cadastro, edição e exclusão de empresas (restrito a usuários com papel `ROLE_EMPRESA`).
- **Gerenciamento de Vagas**: Criação e consulta de vagas de emprego.
- **Candidaturas**: Submissão e administração de candidaturas por usuários (`ROLE_USER`).
- **Documentação**: Interface Swagger para explorar e testar os endpoints.

## Tecnologias Utilizadas
- **Java 17**: Linguagem principal com foco em POO.
- **Spring Boot 3.1.3**: Framework para construção da API REST.
- **Spring Security**: Autenticação e autorização com JWT.
- **JPA/Hibernate**: Persistência de dados com banco relacional.
- **Springdoc OpenAPI**: Documentação automática com Swagger UI.
- **Maven**: Gerenciamento de dependências e build.

## Como Executar
Siga os passos abaixo para rodar o projeto localmente:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seuusuario/jobs-api.git
