package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "detalle_Id")


@Entity
@Table(name = "detalles_pedido")
public class Detalle_Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "detalle_Id")
    private UUID detalleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_Id")
    private Pedido pedido;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogo_id")
    private Catalogo catalogo;

    private int cantidad;

    private BigDecimal precio_Unitario;

    private BigDecimal sub_Total;








//    -- 5. Tabla DetallesPedido
//
//    CREATE TABLE detallesPedido (
//            detalle_Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
//    pedido_Id UNIQUEIDENTIFIER NOT NULL,
//    catalogo_id INT NOT NULL,
//    cantidad INT NOT NULL CHECK (Cantidad > 0),
//    precio_Unitario DECIMAL(10,2) NOT NULL,
//    sub_Total DECIMAL(10,2) NOT NULL,
//    CONSTRAINT FK_Detalles_Pedidos FOREIGN KEY (pedido_Id) REFERENCES pedidos(pedido_Id),
//    CONSTRAINT FK_Detalles_Catalogo FOREIGN KEY (catalogo_id) REFERENCES catalogo(catalogo_id)

}
