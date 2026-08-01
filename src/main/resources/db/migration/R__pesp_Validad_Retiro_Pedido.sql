CREATE OR ALTER PROCEDURE pesp_Validad_Retiro_Pedido
    @id_Pedido UNIQUEIDENTIFIER,
    @CodigoRetiro BIGINT
          AS
    BEGIN
    SET NOCOUNT ON
        UPDATE pedidos set estado_Actual = 'RETIRADO'
        where pedido_Id = @id_Pedido AND  codigo_Retiro = @CodigoRetiro
        END;