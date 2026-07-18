CREATE OR ALTER PROCEDURE  casp_RegistroCatalogo
    @nombre NVARCHAR(255),
    @descripcion NVARCHAR(255),
    @precio DECIMAL(10,2) ,
    @tipo NVARCHAR(20),
    @categoria_Desayuno NVARCHAR(50),
    @imagen_url NVARCHAR(500) = NULL,
    @activo BIT

    AS

BEGIN

SET NOCOUNT ON

INSERT INTO catalogo (nombre, descripcion, precio, tipo, categoria_Desayuno, imagen_Url, activo )
VALUES ( @nombre, @descripcion, @precio, @tipo, @categoria_Desayuno, @imagen_url,@activo);


SELECT * FROM catalogo
WHERE catalogo.catalogo_id = SCOPE_IDENTITY();

END;





