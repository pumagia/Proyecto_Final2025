package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.Modelo.Usuario;
import com.pumagia.loscardoscdu.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findALL() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findByid(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findUserEntityByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deletedByEmail(Usuario usuario) {
    usuarioRepository.findUserEntityByEmail(String.valueOf(usuario));
    }

    @Override
    public void update(Usuario usuario) {
     save(usuario);
    }

    @Override
    public String encriptPassword(String clave) {
        return new BCryptPasswordEncoder().encode(clave);
    }



}
