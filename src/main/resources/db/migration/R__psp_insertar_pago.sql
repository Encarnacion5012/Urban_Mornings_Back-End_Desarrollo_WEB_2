CREATE PROCEDURE sp_insertar_pago
    @pago_id UNIQUEIDENTIFIER,
    @monto_pagado DECIMAL(10,2),
    @pedido_id UNIQUEIDENTIFIER,
    @metodo_pago VARCHAR(50),
    @transaccion_id VARCHAR(100),
    @fecha_pago DATETIME
AS
BEGIN
    INSERT INTO pagos (
        pago_id, 
        monto_pagado, 
        pedido_id, 
        metodo_pago, 
        transaccion_id, 
        fecha_pago
    )
    VALUES (
        @pago_id, 
        @monto_pagado, 
        @pedido_id, 
        @metodo_pago, 
        @transaccion_id, 
        @fecha_pago
    );
END;