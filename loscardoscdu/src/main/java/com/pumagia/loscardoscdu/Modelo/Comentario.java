package com.pumagia.loscardoscdu.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comentario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentario;

 //   @ManyToOne // Indica una relación de muchos a uno
 //   @JoinColumn(name = "id_usuario") // Define la columna de la clave foránea
 //   private Usuario usuario; // Referencia a la entidad Usuario
    private String id_usuario;
    private String comenta;
}
