package com.pumagia.loscardoscdu.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id_usuario;
  @Column(unique = true)
  private String nombreyapellido;
  private Long dni;
  private String email;
  private String telefono;
  private String clave;
    @OneToMany
    private List<Agenda> listaAgenda;
    @OneToMany
    private List<Comentario> listaComentario;

  private boolean enabled;
  private boolean accountNotExpired;
  private boolean accountNotLocked;
  private boolean credentialNotExpired;
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name ="user_roles",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> rolesList = new HashSet<>();



}
