-- Produtos (para estoque)
INSERT INTO produto (id, descricao, preco_unitario) VALUES (10, 'Televisor', 2000.0);
INSERT INTO produto (id, descricao, preco_unitario) VALUES (20, 'Geladeira', 3500.0);
INSERT INTO produto (id, descricao, preco_unitario) VALUES (30, 'Fogao', 1200.0);
INSERT INTO produto (id, descricao, preco_unitario) VALUES (40, 'Lava-louça', 1800.0);
INSERT INTO produto (id, descricao, preco_unitario) VALUES (50, 'Lava-roupas', 2870.0);

-- Produtos (para orçamentos)
INSERT INTO produto_model (id, descricao, preco_unitario) VALUES (10, 'Televisor', 2000.0);
INSERT INTO produto_model (id, descricao, preco_unitario) VALUES (20, 'Geladeira', 3500.0);
INSERT INTO produto_model (id, descricao, preco_unitario) VALUES (30, 'Fogao', 1200.0);
INSERT INTO produto_model (id, descricao, preco_unitario) VALUES (40, 'Lava-louça', 1800.0);
INSERT INTO produto_model (id, descricao, preco_unitario) VALUES (50, 'Lava-roupas', 2870.0);

-- Itens de estoque
INSERT INTO item_de_estoque (id, produto_id, quantidade, estoque_min, estoque_max) VALUES (100, 10, 20, 5, 50);
INSERT INTO item_de_estoque (id, produto_id, quantidade, estoque_min, estoque_max) VALUES (200, 20, 10, 5, 30);
INSERT INTO item_de_estoque (id, produto_id, quantidade, estoque_min, estoque_max) VALUES (300, 40, 8, 5, 50);

-- Orçamentos
INSERT INTO orcamento_model (id, custo_consumidor, custo_itens, data_efetivacao, desconto, efetivado, imposto)
VALUES (1, 6000.0, 5500.0, '2024-12-01', 500.0, true, 500.0);

INSERT INTO orcamento_model (id, custo_consumidor, custo_itens, data_efetivacao, desconto, efetivado, imposto)
VALUES (2, 3000.0, 2800.0, null, 200.0, false, 400.0);

-- Itens dos orçamentos
-- Orçamento 1: 1x Televisor + 1x Geladeira
INSERT INTO orcamento_model_itens (orcamento_model_id, produto_id, quantidade) VALUES (1, 10, 1);
INSERT INTO orcamento_model_itens (orcamento_model_id, produto_id, quantidade) VALUES (1, 20, 1);

-- Orçamento 2: 2x Fogão + 1x Lava-louça
INSERT INTO orcamento_model_itens (orcamento_model_id, produto_id, quantidade) VALUES (2, 30, 2);
INSERT INTO orcamento_model_itens (orcamento_model_id, produto_id, quantidade) VALUES (2, 40, 1);
