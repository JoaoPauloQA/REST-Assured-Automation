-- Limpa a tabela antes para evitar conflito ao rodar várias vezes
DELETE FROM usuarios;

-- Insere um usuário fixo para testes de login
INSERT INTO usuarios (id, username, email, senha_hash, nome_completo, created_at, update_at)
VALUES
(
    1,
    'qa_user',
    'qa_user@gamestore.com',
    -- senha: 123456789 (use o mesmo hash que seu backend gera)
    '$2a$10$P2f....EXEMPLO_DE_HASH',
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
    '$2a$10$P2f....EXEMPLO_DE_HASH',
    'Usuário Duplicado',
    NOW(),
    NOW()
);