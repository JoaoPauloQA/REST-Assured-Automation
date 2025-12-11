# 🕹️ GameStore API Tests

Suite de testes automatizados das APIs da plataforma GameStore, utilizando Rest Assured, JUnit 5, Allure Reports e integração com CI/CD.

## 🧩 Tecnologias & Bibliotecas

-Java 17
-Maven
-Rest Assured
-JUnit 5
-Allure Reports
-GitHub Actions (CI/CD)

## 📦 Instalação
Pré-requisitos
-Java 17 instalado
-Maven instalado
-Git

## 🔧 Endpoints Testados
🕹️ Games
Método	Rota
GET	/api/jogos
GET	/api/games
GET	/api/jogos/recomendado
GET	/api/games/count
GET	/api/gamepass
GET	/api/games/search
GET	/api/top-played
GET	/api/rawg-games
GET	/api/games/popular
GET	/api/games/:id/details
👤 Autenticação
Método	Rota
POST	/api/auth/register
POST	/api/auth/login
POST	/api/auth/refresh
GET	/api/auth/verify
GET	/api/user/me
🛒 Checkout & Account
Método	Rota
POST	/api/checkout
GET	/api/account/:id
GET	/api/orders/user/:id

## 🔄 CI/CD (GitHub Actions)

Pipeline configurado para:

✔ Rodar testes automaticamente a cada push
✔ Gerar artefatos
✔ Validar o build Maven

