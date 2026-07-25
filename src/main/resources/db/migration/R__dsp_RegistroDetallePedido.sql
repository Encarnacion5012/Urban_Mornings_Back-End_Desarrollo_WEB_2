CREATE OR ALTER PROCEDURE csp_RegistroDetallePedido
    @detalle_Id UNIQUEIDENTIFIER,
    @pedido_Id UNIQUEIDENTIFIER,
    @catalogo_id INT,
    @cantidad INT,
    @precio_Unitario DECIMAL(10,2),
    @sub_Total DECIMAL(10,2)
AS
BEGIN
    INSERT INTO detalles_pedido (
        detalle_Id,
        pedido_Id, 
        catalogo_id, 
        cantidad, 
        precio_Unitario, 
        sub_Total
    )
    VALUES (
        @detalle_Id,
        @pedido_Id, 
        @catalogo_id, 
        @cantidad, 
        @precio_Unitario, 
        @sub_Total
    );
END;