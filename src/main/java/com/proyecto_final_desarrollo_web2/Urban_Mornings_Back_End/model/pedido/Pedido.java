package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "pedido_Id")


@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pedido_Id")
    private UUID pedidoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_Id")
    private Cliente cliente;

    private LocalDateTime fecha_Hora_Retiro;

    @Column(name = "codigo_Retiro")
    private Long codigoRetiro; //Recordar meter el metodo que lo genere y sus exepciones al hacer el metodo registro

    @Enumerated(EnumType.STRING)
    private Estado_Actual_Pedido estado_Actual;

    private BigDecimal total_Pedido;

    private LocalDateTime fecha_Creacion;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Detalle_Pedido> detallesPedido = new ArrayList<>();

    private String dedicatoria;


    //Metodo para hacer la aptopcion de detalles

    public void hacerAdopcionPedido(List<Detalle_Pedido> detalles) {
        this.detallesPedido.addAll(detalles);
        detalles.forEach(d -> d.setPedido(this));

    }
}
