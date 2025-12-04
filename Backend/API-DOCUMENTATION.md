# 📚 DOCUMENTAÇÃO DA API - GAMESTORE

## 📖 Documentação Swagger/OpenAPI Completa

Este projeto possui documentação completa da API no formato OpenAPI 3.0 (Swagger).

---

## 🚀 COMO VISUALIZAR A DOCUMENTAÇÃO

### **Opção 1: Swagger UI Interativo (RECOMENDADO)**

1. **Inicie o servidor backend:**
   ```bash
   cd Backend
   npm start
   ```

2. **Abra o Swagger UI no navegador:**
   ```
   file:///d:/ProjetoGameStore/Backend/swagger-ui.html
   ```
   
   Ou copie o arquivo `swagger-ui.html` para a pasta `public` e acesse via:
   ```
   http://localhost:3000/swagger-ui.html
   ```

---

### **Opção 2: Editor Online do Swagger**

1. **Acesse:** https://editor.swagger.io

2. **Cole o conteúdo de `Backend/swagger.yaml`**

3. **Visualize e teste a documentação**

---

### **Opção 3: VS Code + Extensão Swagger**

1. **Instale a extensão:** `42Crunch.vscode-openapi`

2. **Abra o arquivo:** `Backend/swagger.yaml`

3. **Pressione:** `Ctrl+Shift+P` → `OpenAPI: Show Preview`

---

## 📂 ARQUIVOS DA DOCUMENTAÇÃO

```
Backend/
├── swagger.yaml        ✅ Especificação OpenAPI 3.0
└── swagger-ui.html     ✅ Interface interativa Swagger UI
```

---

## 🔑 ENDPOINTS DISPONÍVEIS

### **🎮 JOGOS** (Sem autenticação)
```
GET    /api/jogos                 - Listar todos os jogos
GET    /api/games                 - Alias para /api/jogos
GET    /api/jogos/recomendado     - Jogo aleatório recomendado
GET    /api/games/count           - Contar total de jogos
GET    /api/gamepass              - Produtos Xbox Game Pass
GET    /api/games/search          - Buscar jogos por título
GET    /api/top-played            - Jogos mais jogados
GET    /api/games/:id/details     - Detalhes de um jogo (RAWG)
```

### **🔐 AUTENTICAÇÃO**
```
POST   /api/auth/register         - Registrar novo usuário
POST   /api/auth/login            - Login (retorna JWT)
GET    /api/auth/verify           - Verificar token JWT 🔒
POST   /api/auth/refresh          - Renovar token JWT 🔒
```

### **🛒 COMPRAS** (Requer autenticação 🔒)
```
POST   /api/checkout              - Processar compra
GET    /api/account/:id           - Dados da conta + compras
GET    /api/orders/user/:id       - Histórico de pedidos
GET    /api/compras/historico     - Histórico legado
```

### **👤 USUÁRIO** (Requer autenticação 🔒)
```
GET    /api/user/me               - Dados do usuário logado
```

### **🌐 RAWG API** (Integração externa)
```
GET    /api/rawg-games            - Buscar jogos na RAWG
GET    /api/games/popular         - Jogos populares da RAWG
GET    /api/rawg-news             - Notícias de jogos
```

### **📞 SUPORTE**
```
POST   /api/support/ticket        - Criar ticket de suporte
```

### **🔧 DEBUG**
```
GET    /__routes                  - Listar rotas registradas
```

**Legenda:** 🔒 = Requer token JWT no header `Authorization: Bearer <token>`

---

## 🧪 TESTAR A API

### **1. Usando Swagger UI:**

- Abra `swagger-ui.html`
- Clique em "Try it out" em qualquer endpoint
- Preencha os parâmetros
- Clique em "Execute"

### **2. Usando cURL:**

```bash
# Listar jogos
curl http://localhost:3000/api/jogos

# Login
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@gamestore.com","password":"123456"}'

# Usar token JWT
curl http://localhost:3000/api/user/me \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

### **3. Usando Postman:**

1. **Importe o Swagger:**
   - Postman → Import → Selecione `swagger.yaml`

2. **Configure variáveis:**
   - `baseUrl`: http://localhost:3000
   - `token`: (após fazer login)

---

## 🔐 AUTENTICAÇÃO JWT

### **Como obter o token:**

1. **Registrar usuário:**
   ```bash
   POST /api/auth/register
   {
     "username": "teste",
     "email": "teste@example.com",
     "password": "senha123"
   }
   ```

2. **Ou fazer login:**
   ```bash
   POST /api/auth/login
   {
     "email": "admin@gamestore.com",
     "password": "123456"
   }
   ```

3. **Copiar o token da resposta:**
   ```json
   {
     "success": true,
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
   }
   ```

### **Como usar o token:**

Adicione o header em todas as requisições protegidas:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**No Swagger UI:**
1. Clique no botão **"Authorize"** (cadeado) no topo
2. Cole o token (sem o prefixo "Bearer")
3. Clique em **"Authorize"**
4. Agora você pode testar endpoints protegidos

---

## 📊 SCHEMAS DE DADOS

### **Jogo:**
```json
{
  "id": 1,
  "title": "GTA V",
  "price": 89.90,
  "platforms": ["pc", "ps", "xbox"],
  "image": "https://example.com/gta5.jpg",
  "plays": 1200000
}
```

### **Usuário:**
```json
{
  "id": 1,
  "username": "admin",
  "email": "admin@gamestore.com",
  "nome_completo": "Administrador",
  "created_at": "2025-11-30T12:00:00Z"
}
```

### **Pedido (Order):**
```json
{
  "orderId": 42,
  "totalPrice": 149.80,
  "createdAt": "2025-12-01T15:30:00Z",
  "items": [
    {
      "game_id": 1,
      "title": "GTA V",
      "price": 89.90,
      "quantity": 1
    }
  ]
}
```

---

## 🤖 AUTOMAÇÃO COM A DOCUMENTAÇÃO

### **1. Gerar Código Cliente Automaticamente:**

**Usando OpenAPI Generator:**
```bash
# Instalar
npm install -g @openapitools/openapi-generator-cli

# Gerar cliente JavaScript
openapi-generator-cli generate \
  -i Backend/swagger.yaml \
  -g javascript \
  -o ./generated-client

# Outros geradores disponíveis:
# python, java, typescript-axios, csharp, go, php, ruby, etc.
```

### **2. Gerar Testes Automatizados:**

**Usando Postman:**
1. Importe `swagger.yaml` no Postman
2. Use o **Collection Runner** para executar todos os endpoints
3. Exporte como **Newman** para CI/CD

**Usando Rest Assured (Java):**
```java
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GameStoreTests {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Test
    public void testGetJogos() {
        given()
            .when()
            .get("/api/jogos")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}
```

### **3. Validar Requisições/Respostas:**

**Usando express-openapi-validator:**
```javascript
const OpenApiValidator = require('express-openapi-validator');

app.use(
  OpenApiValidator.middleware({
    apiSpec: './Backend/swagger.yaml',
    validateRequests: true,
    validateResponses: true,
  }),
);
```

---

## 📝 EXEMPLOS DE FLUXOS

### **Fluxo de Compra Completo:**

```bash
# 1. Registrar usuário
POST /api/auth/register
{
  "username": "joao",
  "email": "joao@example.com",
  "password": "senha123"
}

# 2. Listar jogos
GET /api/jogos

# 3. Fazer checkout
POST /api/checkout
Authorization: Bearer <token>
{
  "cart": [
    {"id": 1, "qty": 1},
    {"id": 5, "qty": 2}
  ],
  "formaPagamento": "pix"
}

# 4. Ver histórico
GET /api/orders/user/1
Authorization: Bearer <token>
```

---

## 🔧 MANUTENÇÃO DA DOCUMENTAÇÃO

### **Atualizar a documentação:**

1. **Edite** `Backend/swagger.yaml`
2. **Valide** em https://editor.swagger.io
3. **Commit** as mudanças

### **Adicionar novo endpoint:**

```yaml
/api/seu-endpoint:
  get:
    tags:
      - Nome da Tag
    summary: Descrição curta
    description: Descrição detalhada
    operationId: nomeUnico
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              type: object
```

---

## 📚 REFERÊNCIAS

- **OpenAPI Spec:** https://spec.openapis.org/oas/v3.0.3
- **Swagger UI:** https://swagger.io/tools/swagger-ui/
- **Editor Online:** https://editor.swagger.io
- **OpenAPI Generator:** https://openapi-generator.tech

---

## ✅ CHECKLIST

- [x] Documentação completa no formato OpenAPI 3.0
- [x] Swagger UI interativo
- [x] Todos os 30+ endpoints documentados
- [x] Autenticação JWT explicada
- [x] Schemas de dados definidos
- [x] Exemplos de requisição/resposta
- [x] Códigos de erro documentados
- [x] Pronto para automação de testes

---

**Status:** ✅ **Documentação completa e pronta para uso!**
