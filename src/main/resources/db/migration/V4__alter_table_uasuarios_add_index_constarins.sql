-- COnstraints

ALTER TABLE usuarios ADD CONSTRAINT PK_usuarios PRIMARY KEY NONCLUSTERED (id_usuario);
ALTER TABLE usuarios ADD CONSTRAINT DF_usuarios_id_usuario DEFAULT NEWID() FOR id_usuario;
ALTER TABLE usuarios ADD CONSTRAINT CHK_usuarios_rol CHECK (rol IN ('ADMIN', 'PERSONAL_COCINA'));
ALTER TABLE usuarios ADD CONSTRAINT DF_usuario_imagen_Url DEFAULT 'SIN IMAGEN' FOR imagen_url;
ALTER TABLE usuarios ADD CONSTRAINT DF_usuario_activo DEFAULT 1 FOR activo;


-- INdices
CREATE UNIQUE INDEX IX_usuarios_correo ON usuarios (correo);

CREATE UNIQUE INDEX IX_usuarios_cedula ON usuarios (cedula);
