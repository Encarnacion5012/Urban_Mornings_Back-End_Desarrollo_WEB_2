CREATE OR ALTER PROCEDURE csp_ListarClientes
AS


    BEGIN
    SET NOCOUNT ON

        SELECT * FROM clientes;
    END