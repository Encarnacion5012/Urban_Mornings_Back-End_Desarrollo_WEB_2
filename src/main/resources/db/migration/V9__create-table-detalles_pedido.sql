-- 5. Tabla Detalles_Pedido

CREATE TABLE detalles_pedido (
detalle_Id UNIQUEIDENTIFIER NOT NULL ,
pedido_Id UNIQUEIDENTIFIER NOT NULL,
catalogo_id INT NOT NULL,
cantidad INT NOT NULL ,
precio_Unitario DECIMAL(10,2) NOT NULL,
sub_Total DECIMAL(10,2) NOT NULL
);

