
--Constraints

ALTER TABLE clientes ADD CONSTRAINT DF_clientes_rol DEFAULT 'CLIENTE' FOR rol;

ALTER TABLE clientes ADD CONSTRAINT CHK_clientes_rol CHECK (rol IN ('CLIENTE'));

ALTER TABLE clientes ADD CONSTRAINT PK_clientes PRIMARY KEY NONCLUSTERED (cliente_Id);

ALTER TABLE clientes ADD CONSTRAINT DF_clientes_id DEFAULT NEWID() FOR cliente_Id;


--Indices
CREATE UNIQUE INDEX IX_clienetes_correo ON clientes (correo);

CREATE UNIQUE INDEX IX_clientes_cedula ON clientes (cedula);

