-- 3. Tabla Catalogo

CREATE TABLE catalogo (
catalogo_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
nombre NVARCHAR(100) NOT NULL,
descripcion NVARCHAR(255),
precio DECIMAL(10,2) NOT NULL,
tipo NVARCHAR(20) NOT NULL,
categoria_Desayuno NVARCHAR(50),
imagen_Url NVARCHAR(500),
activo BIT,


 );

