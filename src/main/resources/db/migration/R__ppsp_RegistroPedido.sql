CREATE OR ALTER PROCEDURE csp_RegistroPedido
    @pedido_Id UNIQUEIDENTIFIER,
    @cliente_Id UNIQUEIDENTIFIER, 
    @fecha_Hora_Retiro DATETIME,
    @codigo_Retiro BIGINT,
    @estado_Actual VARCHAR(50),
    @total_Pedido DECIMAL(10,2),
    @fecha_Creacion DATETIME
AS
BEGIN
    INSERT INTO pedidos (
        pedido_Id, 
        cliente_Id, 
        fecha_Hora_Retiro, 
        codigo_Retiro, 
        estado_Actual, 
        total_Pedido, 
        fecha_Creacion
    )
    VALUES (
        @pedido_Id, 
        @cliente_Id, 
        @fecha_Hora_Retiro, 
        @codigo_Retiro, 
        @estado_Actual, 
        @total_Pedido, 
        @fecha_Creacion
    );
END;