CREATE OR ALTER PROCEDURE usp_Borrado_Logico_Usuario
          @id_Usuario UNIQUEIDENTIFIER

          AS

    BEGIN
    SET NOCOUNT ON;
        update usuarios set activo = 0 where id_usuario = @id_Usuario
        END;