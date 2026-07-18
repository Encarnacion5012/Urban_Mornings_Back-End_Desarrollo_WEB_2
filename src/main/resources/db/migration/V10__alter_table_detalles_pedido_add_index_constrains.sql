
--Constraints

ALTER TABLE detalles_pedido ADD CONSTRAINT PK_detalles_pedido PRIMARY KEY NONCLUSTERED (detalle_Id);
ALTER TABLE detalles_pedido ADD CONSTRAINT DF_detalles_pedido_detalle_Id DEFAULT NEWID() FOR detalle_Id;
ALTER TABLE detalles_pedido ADD CONSTRAINT CHK_detalles_pedido_cantidad  CHECK (Cantidad > 0);


ALTER TABLE detalles_pedido ADD CONSTRAINT FK_Detalles_Pedidos FOREIGN KEY (pedido_Id) REFERENCES pedidos(pedido_Id);
ALTER TABLE detalles_pedido ADD CONSTRAINT FK_Detalles_Catalogo FOREIGN KEY (catalogo_id) REFERENCES catalogo(catalogo_id);

--Indices

