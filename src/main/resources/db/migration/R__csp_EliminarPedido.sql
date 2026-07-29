CREATE PROCEDURE csp_EliminarPedido
    @pedido_Id UNIQUEIDENTIFIER
AS
BEGIN
    DELETE FROM pedidos WHERE pedido_Id = @pedido_Id;
END;