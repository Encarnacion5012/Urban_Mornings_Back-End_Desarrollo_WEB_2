-- 2. Tabla Usuarios (Administradores y Personal)

CREATE TABLE usuarios (
 id_usuario UNIQUEIDENTIFIER NOT NULL ,
 cedula VARCHAR(25) UNIQUE NOT NULL,
 nombre_Completo NVARCHAR(100) NOT NULL,
 correo NVARCHAR(100) UNIQUE NOT NULL,
 telefono NVARCHAR(20),
 clave NVARCHAR(255) NOT NULL,
 rol NVARCHAR(20) NOT NULL,
 imagen_url NVARCHAR(500) ,
 activo BIT NOT NULL ,


);



