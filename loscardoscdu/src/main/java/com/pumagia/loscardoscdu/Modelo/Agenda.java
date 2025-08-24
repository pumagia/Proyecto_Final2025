package com.pumagia.loscardoscdu.Modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Agenda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_agenda;
    private Long id_usuario;
    private String fecha_inicio;
    private String fecha_fin;


}
