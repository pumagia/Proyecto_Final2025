package com.pumagia.loscardoscdu.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Role {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long role_id;
        private String roles;
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name ="roles_permissions",joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "permission_id"))
        private Set<Permission> permissionsList = new HashSet<>();


  //  public void setPermissionsList(Set<com.pumagia.loscardoscdu.Modelo.Permission> permiList) {
  //  }
}
