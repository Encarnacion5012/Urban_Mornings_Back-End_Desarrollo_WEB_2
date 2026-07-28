CREATE OR ALTER PROCEDURE usp_RegistroUsuario

    @id_usuario UNIQUEIDENTIFIER ,
    @cedula VARCHAR(25),
    @nombre_Completo NVARCHAR(100),
    @correo NVARCHAR(100),
    @telefono NVARCHAR(20),
    @clave NVARCHAR(255),
    @rol NVARCHAR(20),
    @imagen_url NVARCHAR(500)=NULL ,
    @activo BIT = NULL
    AS

BEGIN
SET NOCOUNT ON;

INSERT INTO usuarios(id_usuario,cedula, nombre_Completo, correo, telefono, clave, rol,imagen_url, activo)
VALUES(@id_usuario, @cedula, @nombre_Completo, @correo, @telefono, @clave, @rol,@imagen_url, @activo );

SELECT usuarios.id_usuario,
       usuarios.cedula,
       usuarios.nombre_Completo,
       usuarios.correo,
       usuarios.telefono,
       usuarios.imagen_url,
       usuarios.rol
FROM usuarios
WHERE usuarios.id_usuario = @id_usuario;

END