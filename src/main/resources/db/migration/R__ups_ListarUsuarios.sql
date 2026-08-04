CREATE OR ALTER PROCEDURE  usp_ListarUsuarios
    @usuarioActual UNIQUEIDENTIFIER
          AS
    BEGIN
    SET NOCOUNT ON

        SELECT usuarios.id_usuario,
               usuarios.cedula,
               usuarios.nombre_Completo,
               usuarios.correo,
               usuarios.telefono,
               usuarios.imagen_url,
               usuarios.rol
               FROM usuarios
        WHERE usuarios.activo = 1 AND usuarios.id_usuario != @usuarioActual;

        END;