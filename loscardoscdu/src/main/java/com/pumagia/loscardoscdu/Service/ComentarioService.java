package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.Modelo.Comentario;
import com.pumagia.loscardoscdu.Repository.IComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentarioService implements IComentarioService {
    @Autowired
    private IComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> buscarTodos() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    @Override
    //@Transactional
    public void guardar(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public void eliminarPorId(Long id) {
        comentarioRepository.deleteById(id);
    }
}
