# 🕹️ GameStore API Tests

Suite de testes automatizados das APIs da plataforma GameStore, utilizando Rest Assured, JUnit 5, Allure Reports e integração com CI/CD.

## 🧩 Tecnologias & Bibliotecas

- Java 17
- Maven
- Rest Assured
- JUnit 5
- Allure Reports
- GitHub Actions (CI/CD)

## 📦 Instalação
Pré-requisitos
-Java 17 instalado
-Maven instalado
-Git 

## 🧩 Dependências para Executar os Testes Localmente

Este projeto testa a aplicação real da plataforma GameStore, que roda localmente.
Por isso, para executar os testes na sua máquina, você precisa clonar dois repositórios:

1️⃣ Clonar e rodar o projeto REAL (GameStore – Front + Back + PostgreSQL)

Este é o sistema completo que será testado.

git clone https://github.com/JoaoPauloQA/ProjetoGameStore
cd gamestore

Inicie o servidor :

node server.js

A aplicação deve estar disponível em:

http://localhost:8080

## 2️⃣ Clonar este projeto de automação (API Tests – Rest Assured)

Com o servidor rodando, agora sim clone o projeto de testes:

git clone https://github.com/JoaoPauloQA/REST-Assured-Automation

📌 Observações Importantes

- ✔ Os testes fazem chamadas reais para sua API real
- ✔ O banco de dados e o back-end precisam estar funcionando
- ✔ O CI/CD pode usar uma URL pública (se você tiver deploy)
- ✔ Localmente é obrigatório rodar o projeto GameStore primeiro

## 🔧 Endpoints Testados
## 🕹️ Games

- GET	/api/jogos
- GET	/api/games
- GET	/api/jogos/recomendado
- GET	/api/games/count
- GET	/api/gamepass
- GET	/api/games/search
- GET	/api/top-played
- GET	/api/rawg-games
- GET	/api/games/popular
- GET	/api/games/:id/details
## 👤 Autenticação

- POST	/api/auth/register
- POST	/api/auth/login
- POST	/api/auth/refresh
- GET	/api/auth/verify
- GET	/api/user/me
## 🛒 Checkout & Account

- POST	/api/checkout
- GET	/api/account/:id
- GET	/api/orders/user/:id

## 🔍 Validações e Testes de Contrato (Schema Validation)

Além de testes funcionais, o projeto também implementa testes de contrato para garantir que as respostas da API sigam exatamente o formato esperado.

Os testes de contrato incluem:

- ✔ Validação de schema (JSON Schema Validation)
- ✔ Tipos de dados corretos (string, boolean, array, number, object)
- ✔ Campos obrigatórios presentes
- ✔ Estrutura da resposta consistente
- ✔ Garantia de que alterações indevidas no back-end sejam detectadas
- ✔ Prevenção de breaking changes

## 🔄CI/CD com Allure (GitHub Actions)

Pipeline configurado para:

- ✔ Rodar testes automaticamente a cada push
- ✔ Gerar os resultados do Allure
- ✔ Salvar os artefatos (allure-results) gerados pelo Maven
- ✔ Disponibilizar os relatórios para download direto no GitHub Actions

 ## 📁 Estrutura do Projeto

A automação está organizada seguindo boas práticas de testes de API com Rest Assured, separando autenticação, payloads, configurações, validações e factories.

```
src
└── test
    └── java
        ├── auth
        │   └── (testes e métodos de autenticação)
        ├── checkout
        │   └── (testes do fluxo de checkout)
        ├── config
        │   └── (configurações globais, base URI, properties)
        ├── Factories
        │   └── (geração de dados dinâmicos e massa de teste)
        ├── Games
        │   └── (testes relacionados aos endpoints de jogos)
        ├── payloads
        │   └── (corpos de requisição usados no projeto)
        ├── utils
        │   └── (helpers, geradores, funções reutilizáveis)
        └── validators
            └── (validações e asserts customizados)
```
  
## 👨‍💻 Autor
**João Paulo QA**  
QA Automation Engineer | Test Automation Enthusiast  
LinkedIn https://www.linkedin.com/in/jo%C3%A3o-paulo-6a1b3a207/
