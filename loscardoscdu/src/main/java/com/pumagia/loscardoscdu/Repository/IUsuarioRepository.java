package com.pumagia.loscardoscdu.Repository;

import com.pumagia.loscardoscdu.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findUserEntityByEmail(String email);
    Optional<Usuario> findUserEntityByNombreyapellido(String nombreyapellido);
}
