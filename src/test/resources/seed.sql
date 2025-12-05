-- Limpa a tabela antes para evitar conflito ao rodar várias vezes
DELETE FROM usuarios;

-- Insere um usuário fixo para testes de login
INSERT INTO usuarios (id, username, email, senha_hash, nome_completo, created_at, update_at)
VALUES
(
    1,
    'qa_user',
    'qa_user@gamestore.com',
   '$2b$10$3vBt6CTidoUuM0IsRwVBF.SfgH2dnK8Yhs6pgbIgQ/rBn/XwL5KV6',
    'QA User',
    NOW(),
    NOW()
);

-- Insere um segundo usuário (para testar email duplicado)
INSERT INTO usuarios (id, username, email, senha_hash, nome_completo, created_at, update_at)
VALUES
(
    2,
    'usuario_existente',
    'duplicado@gamestore.com',
    '$2b$10$3vBt6CTidoUuM0IsRwVBF.SfgH2dnK8Yhs6pgbIgQ/rBn/XwL5KV6',
    'Usuário Duplicado',
    NOW(),
    NOW()
);