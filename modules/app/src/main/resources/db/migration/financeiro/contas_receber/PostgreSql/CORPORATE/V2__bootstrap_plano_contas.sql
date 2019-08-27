-- Registros de inicialização do plano de contas pré-cadastrado.

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('cf1865a2-a7c4-48af-bca5-bad9b108c0d2','1','ATIVO','RECEITA',NULL,NULL,true)
,('1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe','4','RECEITAS','RECEITA',NULL,NULL,true)
,('a57d351d-cfba-49dd-9c0a-f75317bdf902','1.1','ATIVO CIRCULANTE','RECEITA',NULL,'cf1865a2-a7c4-48af-bca5-bad9b108c0d2',true)
,('71aaea58-638d-4d87-8020-7da200674c43','1.1.1','Caixa','RECEITA',NULL,'a57d351d-cfba-49dd-9c0a-f75317bdf902',true)
,('d634b428-20ef-4e57-b179-21d416facba1','1.1.2','Bancos Conta Movimento','RECEITA',NULL,'a57d351d-cfba-49dd-9c0a-f75317bdf902',true)
,('f6f37b73-50e3-4b04-a709-3c1bc5a6c1be','1.1.3','Aplicações Financeiras','RECEITA',NULL,'a57d351d-cfba-49dd-9c0a-f75317bdf902',true)
,('4933c82e-4e9d-4fb9-b7bf-b17ce28c9213','1.1.4','Contas a Receber','RECEITA',NULL,'a57d351d-cfba-49dd-9c0a-f75317bdf902',true)
,('34b9fab6-5d78-47f0-a265-f6a55d429773','1.1.5','Estoques','RECEITA',NULL,'a57d351d-cfba-49dd-9c0a-f75317bdf902',true)
,('be18541c-86e6-4a56-95ea-6adcaa5002d7','1.2','ATIVO NÃO CIRCULANTES','RECEITA',NULL,'cf1865a2-a7c4-48af-bca5-bad9b108c0d2',true)
,('7d0db045-8675-427d-9682-1a2f1b12a894','1.2.1','Contas a Receber','RECEITA',NULL,'be18541c-86e6-4a56-95ea-6adcaa5002d7',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('38d626c1-baaf-4829-bd4c-2d41d7fa3624','1.2.2','Investimentos','RECEITA',NULL,'be18541c-86e6-4a56-95ea-6adcaa5002d7',true)
,('d5dde6d0-dc35-47ae-b29a-90b05b412fb6','1.2.2.01','Participações Societárias','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('3c63f4b6-406f-44ce-958f-61427928df75','1.2.2.02','Imobilizado','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('9326e522-c83b-4d00-8de2-14f473d59ff8','1.2.2.03','Terrenos','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('1607b4a6-62e0-4ba5-953c-5fd93fe2476a','1.2.2.04','Veículos','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('4c99bae7-0068-4a48-ad20-fe5b2fb876de','1.2.2.05','Móveis','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('950f33b5-b4e3-4cd3-ac2c-8a6a12f9b154','1.2.2.06','Máquinas','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('071a3780-d3e4-4480-948b-c5da21568ae8','1.2.2.07','Ferramentas','RECEITA',NULL,'38d626c1-baaf-4829-bd4c-2d41d7fa3624',true)
,('2e8a6f7d-32b7-416d-8635-9e31a3eca85c','1.2.3','Intangíveis','RECEITA',NULL,'be18541c-86e6-4a56-95ea-6adcaa5002d7',true)
,('1660900f-bbc0-4c6e-8c86-06771f810ed1','1.2.3.01','Softwares','RECEITA',NULL,'2e8a6f7d-32b7-416d-8635-9e31a3eca85c',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('6262b472-fe26-40b9-8af1-9e13b2d7129d','1.2.3.02','Marcas','RECEITA',NULL,'2e8a6f7d-32b7-416d-8635-9e31a3eca85c',true)
,('cd1e6fda-0d80-4198-a961-1c41977879a3','4.1','RECEITAS OPERACIONAIS','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true)
,('26a1c78b-dfff-4464-8725-661a933d844d','4.1.3','Receitas Financeiras','RECEITA',NULL,'cd1e6fda-0d80-4198-a961-1c41977879a3',true)
,('7024bd56-fa22-4f9a-8c45-af38ee5b3546','4.1.1','Vendas de Produtos','RECEITA',NULL,'cd1e6fda-0d80-4198-a961-1c41977879a3',true)
,('850c6667-ef5e-446f-aabb-0355a0da3cd8','4.1.2','Vendas de Serviços','RECEITA',NULL,'cd1e6fda-0d80-4198-a961-1c41977879a3',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;
