--COnstraints
ALTER TABLE catalogo ADD CONSTRAINT CHK_catalog_tipo CHECK (tipo IN ('DESAYUNO', 'BEBIDA'));
ALTER TABLE catalogo ADD CONSTRAINT CHK_catalogo_categoria_Desayuno CHECK (categoria_Desayuno IN ('CRIOLLO', 'SALUDABLE', 'GOURMET'));
ALTER TABLE catalogo ADD CONSTRAINT DF_catalogo_activo DEFAULT 1 FOR activo;

