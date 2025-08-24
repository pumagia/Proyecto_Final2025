package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.Modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> findALL();
    public Optional<Usuario> findByid(Long id);
    public Optional<Usuario>  findByEmail(String email);
    public Usuario save(Usuario usuario);
    public void  deletedByEmail(Usuario usuario);
    public void update(Usuario usuario);
    public String encriptPassword(String password);
}
