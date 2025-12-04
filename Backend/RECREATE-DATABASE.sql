-- ========================================
-- SCRIPT DE RECRIAÇÃO COMPLETA DO BANCO DE DADOS
-- ProjetoGameStore - PostgreSQL
-- ========================================
-- Este script recria EXATAMENTE as tabelas conforme o código original do backend
-- SEM adicionar campos extras (CPF, telefone, endereço, etc)
--
-- ATENÇÃO: Este script apaga todas as tabelas existentes e recria do zero
-- Execute apenas se tiver certeza de que deseja resetar o banco
--
-- Conexão Railway:
-- postgresql://postgres:uQNnvLPBPaKpkAOHTKYVJsNucUCrvXKA@turntable.proxy.rlwy.net:20937/railway
-- ========================================

BEGIN;

-- ========================================
-- PASSO 1: REMOVER TABELAS EXISTENTES
-- ========================================
-- Remove as tabelas na ordem correta para evitar erros de FK

DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS compras CASCADE;
DROP TABLE IF EXISTS jogos CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;

-- ========================================
-- PASSO 2: CRIAR TABELA DE USUÁRIOS
-- ========================================
-- Tabela para autenticação JWT
-- Campos: SOMENTE os que existem no authController.js

CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  senha_hash VARCHAR(255) NOT NULL,
  nome_completo VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para melhorar performance
CREATE INDEX idx_usuarios_username ON usuarios(username);
CREATE INDEX idx_usuarios_email ON usuarios(email);

-- ========================================
-- PASSO 3: CRIAR TABELA DE JOGOS
-- ========================================
-- Tabela do catálogo de produtos (jogos + Game Pass)

CREATE TABLE jogos (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  price DECIMAL(10, 2) NOT NULL DEFAULT 0,
  platforms TEXT[] NOT NULL DEFAULT '{}',
  image TEXT,
  plays INTEGER DEFAULT 0
);

-- Índice para buscas por título
CREATE INDEX idx_jogos_title ON jogos(title);

-- ========================================
-- PASSO 4: CRIAR TABELAS DE PEDIDOS
-- ========================================
-- Sistema de pedidos usado pelo checkout

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  total_price DECIMAL(10,2) NOT NULL CHECK (total_price >= 0),
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_created_at ON orders(created_at DESC);

CREATE TABLE order_items (
  id SERIAL PRIMARY KEY,
  order_id INTEGER NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
  game_id INTEGER NOT NULL REFERENCES jogos(id) ON DELETE RESTRICT,
  quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0)
);

CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_game_id ON order_items(game_id);

-- ========================================
-- PASSO 5: CRIAR TABELA DE COMPRAS (LEGADO)
-- ========================================
-- Tabela opcional para histórico de compras
-- Usado em routes/compras.js como fallback

CREATE TABLE compras (
  id SERIAL PRIMARY KEY,
  usuario_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  jogo_id INTEGER NOT NULL REFERENCES jogos(id) ON DELETE RESTRICT,
  preco NUMERIC(10,2) NOT NULL CHECK (preco >= 0),
  data_compra TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_compras_usuario ON compras(usuario_id);
CREATE INDEX idx_compras_jogo ON compras(jogo_id);
CREATE INDEX idx_compras_data ON compras(data_compra DESC);

-- ========================================
-- PASSO 6: INSERIR DADOS INICIAIS
-- ========================================

-- Usuário de teste (senha: 123456)
-- Hash bcrypt: $2b$10$K3khQw8YiGEPx7Gl8H/TSOiZcJQfz0uTKpOqbZ9EcGxZ.ZdxNvXmO
INSERT INTO usuarios (username, email, senha_hash, nome_completo) 
VALUES ('admin', 'admin@gamestore.com', '$2b$10$K3khQw8YiGEPx7Gl8H/TSOiZcJQfz0uTKpOqbZ9EcGxZ.ZdxNvXmO', 'Administrador');

-- Jogos do catálogo
INSERT INTO jogos (title, price, platforms, image, plays) VALUES
  ('GTA V', 89.90, ARRAY['pc', 'ps', 'xbox'], 'https://s2-techtudo.glbimg.com/Dx-d7zd6abaVIdY1QqGVKl_QvJ4=/0x0:620x349/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2021/s/i/R6L1HDR3GxheHSo2ECLQ/2013-10-11-grand-theft-auto-5-gta-finais.jpg', 1200000),
  
  ('The Witcher 3', 59.90, ARRAY['pc', 'ps'], 'https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/292030/ad9240e088f953a84aee814034c50a6a92bf4516/header.jpg?t=1761131270', 890000),
  
  ('Elden Ring', 89.90, ARRAY['pc', 'ps', 'xbox'], 'https://cdn.cloudflare.steamstatic.com/steam/apps/1245620/header.jpg', 760000),
  
  ('Skyrim', 70.00, ARRAY['pc', 'ps', 'xbox'], 'https://cdn.cloudflare.steamstatic.com/steam/apps/72850/header.jpg', 430000),
  
  ('Baldur''s Gate 3', 89.90, ARRAY['pc', 'ps'], 'https://cdn.cloudflare.steamstatic.com/steam/apps/1086940/header.jpg', 680000),
  
  ('Cyberpunk 2077', 99.90, ARRAY['pc', 'ps', 'xbox'], 'https://upload.wikimedia.org/wikipedia/pt/f/f7/Cyberpunk_2077_capa.png', 1200000),
  
  ('Hollow Knight: Silksong', 99.90, ARRAY['pc', 'ps', 'xbox'], 'https://d1q3zw97enxzq2.cloudfront.net/images/hollow-knight-silksong-title.width-1500.format-webp.webp', 890000),
  
  ('Dark Souls 3', 99.90, ARRAY['pc', 'ps', 'xbox'], 'https://cdn.dlcompare.com/game_tetiere/upload/gameimage/file/7437.jpeg.webp', 760000),
  
  ('Call of Duty: Modern Warfare', 99.90, ARRAY['pc', 'ps', 'xbox'], 'https://s2.glbimg.com/kyu390xzu8z8_XbM1vSu27JdIjg=/0x0:1000x563/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_bc8228b6673f488aa253bbcb03c80ec5/internal_photos/bs/2019/h/L/9O2uOKROWYxLvkOJPRew/call-of-duty-modern-warfare.jpg', 430000),
  
  ('PEAK', 99.90, ARRAY['pc', 'ps', 'xbox'], 'https://i.ytimg.com/vi/_ce92i38ISY/maxresdefault.jpg', 680000);

-- Produtos Game Pass (inseridos automaticamente pelo backend também)
INSERT INTO jogos (title, price, platforms, image, plays) VALUES
  ('XBOX Gamepass 3 meses', 89.90, ARRAY['xbox'], 'https://images.kabum.com.br/produtos/fotos/267029/gift-card-xbox-game-pass-ultimate-3-mes-codigo-digital_1738875974_gg.jpg', 0),
  
  ('XBOX Gamepass 6 meses', 159.90, ARRAY['xbox'], 'https://images.kabum.com.br/produtos/fotos/267029/gift-card-xbox-game-pass-ultimate-3-mes-codigo-digital_1738875974_gg.jpg', 0),
  
  ('XBOX Gamepass 12 meses', 299.90, ARRAY['xbox'], 'https://images.kabum.com.br/produtos/fotos/267029/gift-card-xbox-game-pass-ultimate-3-mes-codigo-digital_1738875974_gg.jpg', 0),
  
  ('XBOX Gamepass Ultimate 12 meses', 399.90, ARRAY['xbox'], 'https://images.kabum.com.br/produtos/fotos/267029/gift-card-xbox-game-pass-ultimate-3-mes-codigo-digital_1738875974_gg.jpg', 0);

COMMIT;

-- ========================================
-- VERIFICAÇÃO
-- ========================================
-- Comandos para verificar se tudo foi criado corretamente

SELECT 'Tabelas criadas:' AS status;
SELECT table_name FROM information_schema.tables 
WHERE table_schema = 'public' 
ORDER BY table_name;

SELECT 'Total de usuários:' AS status, COUNT(*) AS total FROM usuarios;
SELECT 'Total de jogos:' AS status, COUNT(*) AS total FROM jogos;

-- ========================================
-- FIM DO SCRIPT
-- ========================================
-- ✅ Banco de dados recriado com sucesso!
-- 
-- PRÓXIMOS PASSOS:
-- 1. Execute este script no PostgreSQL Railway
-- 2. Verifique se o JWT_SECRET está configurado no .env
-- 3. Reinicie o servidor backend
-- 4. Teste o registro e login no frontend
-- 
-- CREDENCIAIS DE TESTE:
-- Username: admin
-- Email: admin@gamestore.com
-- Senha: 123456
-- ========================================
