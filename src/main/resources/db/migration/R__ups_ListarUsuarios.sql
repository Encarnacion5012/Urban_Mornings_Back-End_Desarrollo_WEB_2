CREATE OR ALTER PROCEDURE  usp_ListarUsuarios
          AS
    BEGIN
    SET NOCOUNT ON

        SELECT usuarios.id_usuario,
               usuarios.cedula,
               usuarios.nombre_Completo,
               usuarios.correo,
               usuarios.telefono
               FROM usuarios
        WHERE usuarios.activo = 1;

        END;