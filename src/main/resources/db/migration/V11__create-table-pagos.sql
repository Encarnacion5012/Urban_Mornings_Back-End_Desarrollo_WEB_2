-- 6. Tabla Pagos (Relación 1:1 estricta)

CREATE TABLE pagos (
pago_Id UNIQUEIDENTIFIER NOT NULL ,
pedido_Id UNIQUEIDENTIFIER UNIQUE NOT NULL,
monto_Pagado DECIMAL(10,2) NOT NULL,
metodo_Pago NVARCHAR(50) NOT NULL,
transaccion_Id NVARCHAR(100),
fecha_Pago DATETIME
);

