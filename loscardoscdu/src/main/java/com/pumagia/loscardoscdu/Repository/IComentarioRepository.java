package com.pumagia.loscardoscdu.Repository;

import com.pumagia.loscardoscdu.Modelo.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Long> {
}
