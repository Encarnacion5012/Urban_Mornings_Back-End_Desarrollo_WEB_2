package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "cliente_Id")


@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID pago_Id;

    private BigDecimal monto_Pagado;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private Metodo_Pago metodo_Pago;

    private String transaccion_Id;

    private LocalDateTime fecha_Pago;


}
