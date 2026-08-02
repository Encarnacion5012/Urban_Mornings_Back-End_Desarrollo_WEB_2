CREATE OR ALTER PROCEDURE estsp_Estadistica_Numero_Ventas_Mes

          AS
    BEGIN
    SET NOCOUNT ON
SELECT
    YEAR(pedidos.fecha_Creacion) AS Anio,
    MONTH(pedidos.fecha_Creacion) AS Mes,
    SUM(pedidos.total_Pedido) AS TotalVentas

    FROM pedidos
    GROUP BY
    YEAR(pedidos.fecha_Creacion),
     MONTH(pedidos.fecha_Creacion)

ORDER BY
    Anio,
    Mes
    END;