-- Registros de inicialização do plano de contas pré-cadastrado.

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe','2','RECEITAS','RECEITA',NULL,NULL,true,false)
,('2abba5e2-df3f-4b6f-96b9-9d6017c6a94a','2.1','SALÁRIOS','RECEITA','FIXO','1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
,('1f02ca45-babd-4329-8887-b375deaa4100','2.4','INVESTIMENTOS','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
,('45be8f2c-4eed-4c3d-938c-7fee88230504','2.1.1','Salário','RECEITA','FIXO','2abba5e2-df3f-4b6f-96b9-9d6017c6a94a',true,false)
,('52b982d9-b48e-4f71-a431-f759617165d9','2.2','VALES','RECEITA','FIXO','1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
,('b858ee43-fda6-4431-9e71-2aa37ff46706','2.2.1','Vale Mercado','RECEITA','FIXO','52b982d9-b48e-4f71-a431-f759617165d9',true,false)
,('3309a884-a49a-4aa5-8c27-a8c6638e4945','2.2.2','Vale Refeição','RECEITA','FIXO','52b982d9-b48e-4f71-a431-f759617165d9',true,false)
,('180ae4b6-248b-4fc6-8f2a-83c213a06d51','2.2.3','Vale Combustível','RECEITA','FIXO','52b982d9-b48e-4f71-a431-f759617165d9',true,false)
,('e56e6da7-d57e-405f-abc9-724d3a3e58e0','2.2.4','Diversos','RECEITA','FIXO','52b982d9-b48e-4f71-a431-f759617165d9',true,false)
,('e2516f3d-6f29-4933-88ac-c4f697c5222b','2.3','VENDAS','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo,deleted) VALUES 
('9f857938-19f9-46eb-8c2a-ab5f4cd94d61','2.3.1','Diversos','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true,false)
,('5860f84f-0d58-4d80-8866-0d5c8bc8ce5a','2.4.1','Juros','RECEITA',NULL,'1f02ca45-babd-4329-8887-b375deaa4100',true,false)
,('39625cbe-2dd6-493c-a0f4-09a3ff10b36d','2.4.2','Resgate','RECEITA',NULL,'1f02ca45-babd-4329-8887-b375deaa4100',true,false)
,('0dbdbdeb-2780-4789-beee-3a0f034c588b','2.5','EMPRÉSTIMOS','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true,false)
,('248a5435-77b2-4191-a679-3f8fdc586661','2.5.1','Empréstimo','RECEITA',NULL,'0dbdbdeb-2780-4789-beee-3a0f034c588b',true,false)
,('2319973c-a86b-4c3e-a3a5-4b540fbb99da','2.4.3','Diversos','RECEITA',NULL,'1f02ca45-babd-4329-8887-b375deaa4100',true,false)
,('a425a8ba-1012-43b8-a43a-9ea9276a7b45','2.5.2','Diversos','RECEITA',NULL,'0dbdbdeb-2780-4789-beee-3a0f034c588b',true,false)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('a9e6be7f-c79c-46b8-b935-ae0ff4762bfa','2.3.2','Terreno','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('edfab525-ed6e-4d93-9e12-196a9946c6ef','2.3.3','Casa','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('7be58cf0-12f4-487f-9cac-1ea138749a63','2.3.4','Apartamento','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('23dce40f-77b5-4eda-9052-aa79617d076f','2.3.5','Automóvel','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('649dba27-bd83-4d09-a31f-ec52843d2031','2.3.6','Bicicleta','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('f095da7f-5779-4f77-afdf-86e672d35048','2.3.7','Motocicleta','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('45224d52-bc47-4f7d-86cb-8bdec214a701','2.3.8','Eletrodoméstico','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('38ad7042-abbc-4708-abd0-ccaf7b344f41','2.3.9','Imóvel','RECEITA',NULL,'e2516f3d-6f29-4933-88ac-c4f697c5222b',true)
,('23de3c57-1c7f-4ac3-a4ab-6192ddbdd39a','2.6','REEMBOLSOS','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true)
,('68fef6de-142a-42d9-8201-4ccf2facf685','2.6.1','Unimed','RECEITA',NULL,'23de3c57-1c7f-4ac3-a4ab-6192ddbdd39a',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;

INSERT INTO plano_conta (id,codigo,descricao,tipo_financeiro,tipo_receita_despesa,plano_conta_pai,ativo) VALUES 
('c05676c7-5467-4dd0-905a-4318c4432f41','2.7','AJUSTE DE SALDO','RECEITA',NULL,'1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe',true)
,('48526fc3-a9c5-4d16-8631-f7dc826b110c','2.7.1','Ajuste de Saldo','RECEITA',NULL,'c05676c7-5467-4dd0-905a-4318c4432f41',true)
ON CONFLICT ON CONSTRAINT pk_plano_conta_id DO NOTHING;