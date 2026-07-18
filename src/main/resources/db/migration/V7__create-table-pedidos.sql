-- 4. Tabla Pedidos

CREATE TABLE pedidos (
pedido_Id UNIQUEIDENTIFIER NOT NULL ,
cliente_Id UNIQUEIDENTIFIER NOT NULL,
dedicatoria NVARCHAR(500),
fecha_Hora_Retiro DATETIME NOT NULL,
codigo_Retiro bigint UNIQUE NOT NULL,
estado_Actual NVARCHAR(50),
total_Pedido DECIMAL(10,2) NOT NULL,
fecha_Creacion DATETIME
);

