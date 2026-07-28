CREATE OR ALTER PROCEDURE casp_ListarTodosCatalogos

    AS

BEGIN
SET NOCOUNT ON

SELECT catalogo.catalogo_id,
       catalogo.descripcion,
       catalogo.precio,
       catalogo.tipo,
       catalogo.categoria_Desayuno,
       catalogo.imagen_Url,
       catalogo.nombre
       FROM catalogo
WHERE catalogo.activo = 1;

END


