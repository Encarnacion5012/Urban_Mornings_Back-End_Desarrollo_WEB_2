CREATE OR ALTER PROCEDURE pesp_Actualizar_Estado_Pedidos
    @id_Pedido UNIQUEIDENTIFIER,
    @nuevoStado VARCHAR(30)

AS
BEGIN
    SET NOCOUNT ON
update pedidos set estado_Actual = @nuevoStado
where pedido_Id = @id_Pedido
END;