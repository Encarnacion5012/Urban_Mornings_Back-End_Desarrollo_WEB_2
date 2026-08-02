CREATE OR ALTER PROCEDURE R__estsp_Top5Productos

          AS
    BEGIN
    SET NOCOUNT ON
        SELECT TOP 5 SUM(dp.cantidad) AS ventas,
               SUM(dp.sub_Total) AS ingresos,
               ca.catalogo_id AS id,
               ca.nombre AS nombre,
               ca.imagen_Url as imagen_Url

            FROM detalles_pedido AS dp
        INNER JOIN catalogo AS ca ON (dp.catalogo_id = ca.catalogo_id)
        where ca.activo =1
        GROUP BY
            ca.catalogo_id, ca.nombre, ca.imagen_Url
        ORDER BY
            SUM(dp.cantidad) DESC







    END;