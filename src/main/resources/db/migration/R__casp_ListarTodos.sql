CREATE OR ALTER PROCEDURE casp_ListarTodos

    AS

BEGIN
SET NOCOUNT ON

SELECT * FROM catalogo
WHERE catalogo.activo = 1;

END


