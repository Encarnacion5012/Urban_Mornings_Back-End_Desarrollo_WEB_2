CREATE OR ALTER PROCEDURE  usp_ListarUsuarios
          AS
    BEGIN
    SET NOCOUNT ON

        SELECT * FROM usuarios
        WHERE usuarios.activo = 1;

        END;