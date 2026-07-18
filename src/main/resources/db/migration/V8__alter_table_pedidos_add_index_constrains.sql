
--COnsraints

ALTER TABLE pedidos ADD CONSTRAINT PK_pedidos PRIMARY KEY NONCLUSTERED (pedido_Id);
ALTER TABLE pedidos ADD CONSTRAINT DF_pedidos_pedido_id DEFAULT NEWID() FOR pedido_Id;
ALTER TABLE pedidos ADD CONSTRAINT DF_pedido_estado_Actual  DEFAULT 'PENDIENTE' FOR estado_Actual;
ALTER TABLE pedidos ADD CONSTRAINT CHK_pedido_estado_Actual CHECK (estado_Actual IN ('PENDIENTE', 'EN_PREPARACION', 'LISTO', 'RETIRADO'));
ALTER TABLE pedidos ADD CONSTRAINT DF_pedido_fecha_Creacion DEFAULT GETDATE() FOR fecha_Creacion;

ALTER TABLE pedidos ADD CONSTRAINT FK_Pedidos_Clientes FOREIGN KEY (cliente_Id) REFERENCES clientes(cliente_Id);


--INdices

CREATE UNIQUE INDEX IX_pedido_codigo_Retiro ON pedidos(codigo_Retiro);