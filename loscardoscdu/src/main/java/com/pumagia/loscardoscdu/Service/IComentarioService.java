package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.Modelo.Comentario;
import java.util.List;

public interface IComentarioService {
    List<Comentario> buscarTodos();
    Comentario buscarPorId(Long id);
    void guardar(Comentario comentario);
    void eliminarPorId(Long id);
}
