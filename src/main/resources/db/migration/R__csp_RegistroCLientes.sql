CREATE OR ALTER PROCEDURE  csp_RegistroCLientes
    @cliente_Id UNIQUEIDENTIFIER,
    @cedula NVARCHAR(20),
    @nombre_Completo NVARCHAR(100),
    @correo NVARCHAR(100),
    @telefono NVARCHAR(20),
    @clave NVARCHAR(255),
    @rol NVARCHAR(20),
    @imagen_url NVARCHAR(500) = NULL,
    @fecha_Registro DATETIME = NULL

    AS

BEGIN

SET NOCOUNT ON




INSERT INTO clientes(cliente_Id,cedula, nombre_Completo, correo, telefono, clave , rol, imagen_url, fecha_Registro)
VALUES(@cliente_Id, @cedula, @nombre_Completo, @correo, @telefono, @clave, @rol, @imagen_url, @fecha_Registro)



END;