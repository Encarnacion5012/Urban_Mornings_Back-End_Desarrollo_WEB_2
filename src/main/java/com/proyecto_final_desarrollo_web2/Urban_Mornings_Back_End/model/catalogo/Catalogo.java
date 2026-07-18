package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "catalogo_id")


@Entity
@Table(name = "catalogo")
public class Catalogo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer catalogo_id;

  private String nombre;

  private String descripcion;

  private BigDecimal precio;

  @Enumerated(EnumType.STRING)
  private Categoria_Desayuno categoria_Desayuno;

  @Enumerated(EnumType.STRING)
  private Tipo_Catalogo tipo;

  private String imagen_Url;

  private Boolean activo;

}
